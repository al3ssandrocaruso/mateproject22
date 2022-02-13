package kapta.utils.dao.listdao;

import kapta.model.profiles.UserModel;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongCrudException;
import kapta.utils.exception.myexception.WrongQueryException;
import kapta.utils.utils.MysqlConnection;
import kapta.utils.dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FollowingListDao {

    private FollowingListDao(){
        //ignored
    }

    public static void addToFollowingList(UserModel listOwner, UserModel userToAdd)  {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.addToListaFollowing(listOwner.getId(),userToAdd.getId(),stm);
        } catch (MysqlConnectionFailed | WrongCrudException e) {
                ErrorHandler.getInstance().reportFinalException(e);
            }
    }
    public static void removeFromFollowingList(UserModel listOwner, UserModel userToRemove) {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.removeFromListaFollowing(listOwner.getId(),userToRemove.getId(),stm);
        }catch (MysqlConnectionFailed | WrongCrudException e) {
                ErrorHandler.getInstance().reportFinalException(e);
            }
        }

    public static List<UserModel> getFollowing(UserModel um) {
        Statement stm = null;
        List<UserModel> seguiti= new ArrayList<>();
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = Query.askSeguitibyId(um.getId(), stm);
            UserModel userModel;
            assert rs != null;
            if(!rs.next()){
                return seguiti ;
            }
            else{
                rs.first();
                do{
                    int index=rs.getInt(1);
                    userModel =UserDao.getUserById(index);
                    seguiti.add(userModel);
                }while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch ( MysqlConnectionFailed | WrongQueryException e){
            ErrorHandler.getInstance().reportFinalException(e);
        }
        return seguiti ;
    }
}
