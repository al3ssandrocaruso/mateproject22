package kapta.utils.dao;
import kapta.model.profiles.UserModel;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.exception.*;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongCrudException;
import kapta.utils.exception.myexception.WrongQueryException;
import kapta.utils.utils.ImageConverter;
import kapta.utils.utils.MysqlConnection;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public  class UserDao {

    private UserDao(){
        //ignored
    }

    public static void saveUser(UserModel userModel) {

        int id = 0;
        Statement stm;

        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.addUserSavetoLogged(userModel.getUsername(), userModel.getPassword(), 0, stm);
            ResultSet rs = Query.askIdFromLogged(stm);
            rs.next();
            id = rs.getInt(1);
            PreparedStatement ps =MysqlConnection.upProfilePhotoPS();
            ps.setInt(1, id);
            ps.setInt(2, -1);
            ps.setInt(3, -1);
            ps.setInt(4, -1);
            ps.setString(5, userModel.getUsername());
            ps.setString(6, "");
            ps.setString(8, userModel.getEmail());
            ps.setString(9, null);
            ps.setInt(10, 0);
            ps.setString(11,userModel.getName()  );
            ps.setString(12, userModel.getGender());
            // green pass code to delete
            ps.setString(13, "");
            ps.setInt(14,0);
            ps.setString(15, userModel.getSecondName());
            ps.setString(16,null);
            InputStream in = new FileInputStream(userModel.getProfileImg());
            ps.setBlob(7,in);
            ps.executeUpdate();

        } catch ( MysqlConnectionFailed | WrongCrudException |  WrongQueryException e ){
                ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException | FileNotFoundException throwables) {
            // non gestita
        }
    }


    public static UserModel getUserById(int index) {
        Statement stm = null;
        UserModel userModel = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs=Query.askUserByUserId(stm,index);
            if(rs.next()){
                rs.first();
                userModel = new UserModel(rs.getString(5));
                userModel.getInfoLogged().setEmail(rs.getString(8));
                userModel.setNumFollower(rs.getInt(10));
                userModel.setName(rs.getString(11));
                userModel.setGender(rs.getString(12));
                userModel.setNumFollowing(rs.getInt(14));
                userModel.setSecondName(rs.getString(15));
                userModel.setId(rs.getInt(1));
                InputStream in = (rs.getBinaryStream(7));
                String filePath= userModel.getUsername()+"pic"+".png";
                File file = new File(filePath);
                ImageConverter.copyInputStreamToFile(in, file);
                userModel.setProfileImg(file);
                }
        }
        catch (MysqlConnectionFailed | WrongQueryException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException | IOException throwables) {
            // non gestita
        }

        return userModel;
    }


    public static UserModel getUserByUsername(String username)  {
        Statement stm = null;
        UserModel um = null ;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = Query.askInfobyUsername(stm, username);
            if (!rs.first()) {
               //ignored
            } else {
                rs.first();
            }
            um = new UserModel(rs.getString(5));
            um.setName(rs.getString(11));
            um.setSecondName(rs.getString(15));
            um.setGender(rs.getString(12));
            um.getInfoLogged().setEmail(rs.getString(8));
            um.setNumFollowing(getNumSeguiti(um));
            um.setNumFollower(getNumFollower(um));
            um.setId(rs.getInt(1));
            InputStream in = (rs.getBinaryStream(7));
            String filePath = username + "pic" + ".png";
            File file = new File(filePath);
            ImageConverter.copyInputStreamToFile(in, file);
            um.setProfileImg(file);
            return um;
        }catch (MysqlConnectionFailed | WrongQueryException e){
                ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException | IOException throwables) {
            // non gestita
        }
            return um;
    }

    public static List<UserModel> usersByUsername(String username) {
        //OK FUNZIONA MA NON CATTURA TUTTE EXCEPTION
        List<UserModel> list = new ArrayList<>();

        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = Query.askInfobyUsername(stm, username);
            assert rs != null ;
            if(!rs.next()){
                return list;
            }

            rs.first();
            UserModel um = new UserModel(rs.getString(5));
            um.setName(rs.getString(11));
            um.setSecondName(rs.getString(15));
            um.setGender(rs.getString(12));
            um.getInfoLogged().setEmail(rs.getString(8));
            um.setNumFollowing(getNumSeguiti(um));
            um.setNumFollower(getNumFollower(um));
            um.setId(rs.getInt(1));
            um.getInfoLogged().setType(0);
            InputStream in = (rs.getBinaryStream(7));
            String filePath= username+"pic"+".png";
            File file = new File(filePath);
            ImageConverter.copyInputStreamToFile(in, file);
            um.setProfileImg(file);
            list.add(um);
        }
        catch (WrongQueryException|MysqlConnectionFailed e){
            ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException | IOException e) {
            // non gestita
        }
        return list;
    }



    //sono da elimianare
    public static Integer getNumSeguiti(UserModel userModel) {
        int output=0;
            if (FollowingListDao.getFollowing(userModel) != null) {
                output = Objects.requireNonNull(FollowingListDao.getFollowing(userModel)).size();
            }
        return output;
    }
    public static Integer getNumFollower(UserModel userModel){
        int output=0;
            if (FollowerListDao.getFollower(userModel) != null) {
                output = Objects.requireNonNull(FollowerListDao.getFollower(userModel)).size();
            }

        return output;
    }
}

