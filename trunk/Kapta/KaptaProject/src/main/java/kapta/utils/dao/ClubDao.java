package kapta.utils.dao;

import kapta.model.EventModel;
import kapta.model.lists.CreatedEventList;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.InfoLogged;
import kapta.utils.bean.PartyEventSchedule;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.exception.*;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.Observer;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.utils.ImageConverter;
import kapta.utils.utils.MysqlConnection;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClubDao {

    private ClubDao(){
        //ignored
    }

    public static void saveClub(  ClubModel clubModel) throws SystemException {

        try {
            Statement stm= MysqlConnection.mysqlConnection();
            CRUD.addClubSavetoLogged( clubModel.getUsername(), clubModel.getPassword(), 1, stm);
            ResultSet rs = Query.askIdFromLogged(stm);
            rs.next();
        int id = 0;
            id = rs.getInt(1);
            PreparedStatement ps =MysqlConnection.upProfileClubPhotoPS();
            InputStream in = new FileInputStream(clubModel.getProfileImage());

            ps.setInt(1, id);
            ps.setString(2,clubModel.getUsername() );
            ps.setBlob(3,in);
            ps.setString(4, clubModel.getEmail());
            ps.setInt(5, 0);
            ps.setString(6 , clubModel.getCity());
            ps.setString(7, clubModel.getAddress());
            ps.setString(8, clubModel.getClubName());
            ps.setString(9, clubModel.getWebsite().toString());

            ps.executeUpdate();

        } catch (MysqlConnectionFailed | SQLException | FileNotFoundException m) {
               ErrorHandler.getInstance().handleException(m);
        }
    }



    // sintomo di una cattiva proggettazione==> da spostare nel event dao e getsire nel caso d'uso (Controller Applicativo l'allaccio)
    public static CreatedEventList getCreatedEventsList(ClubModel clubModel, Observer obs) throws  SystemException {
        List<EventModel> list=new ArrayList<>();
        CreatedEventList createdEventList = new CreatedEventList(clubModel, list , obs);
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();

            ResultSet rs = Query.askCreatedEventListByClubId(clubModel.getId(), stm);
            if (!rs.next()) {
                return createdEventList;
            }
            rs.first();
            do {
                EventModel eventModel = new EventModel(rs.getInt(1));

                eventModel.setEventPrice(rs.getDouble(5));
                eventModel.setName(rs.getString(6));
                eventModel.setEventCreator(ClubDao.clubModelByID(rs.getInt(2)));

                eventModel.setAddress(rs.getString(8));
                PartyEventSchedule partyEventSchedule = new PartyEventSchedule(rs.getDate(7), rs.getTime(9) , rs.getTime(12).toLocalTime());
                InputStream in = (rs.getBinaryStream(11));
                String filePath = eventModel.getName() + "pic" + ".png";
                File file = new File(filePath);
                ImageConverter.copyInputStreamToFile(in, file);
                eventModel.setImg(file);
                eventModel.setPartyEventSchedule(partyEventSchedule);
                createdEventList.addEvent(eventModel);
            } while (rs.next());
        }catch (MysqlConnectionFailed | SQLException e){
            ErrorHandler.getInstance().handleException(e);
        }
        return createdEventList;
    }

    public  static  int clubIdbyClub(ClubModel cl ) throws SystemException {
        int id =-1;
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = null;
            rs = Query.askIdbyClubName(stm, cl.getUsername());
            rs.first();
            id=  rs.getInt(1);
        } catch (SQLException | MysqlConnectionFailed  e) {

            ErrorHandler.getInstance().handleException(e);
        }
        return id;
    }

    public static ClubModel clubModelByID(int id) throws SystemException {
        Statement stm = null;
        ClubModel clubModel = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = Query.infoFromClubById(id, stm);
             if(!rs.next()){
             return null;
            }

            rs.first();
             clubModel = new ClubModel(rs.getString(2));

            setInfo(rs, clubModel);
             clubModel.setId(id);
            InputStream in = (rs.getBinaryStream(3));
             String filePath= clubModel.getClubName()+"pic"+".png";
            File file = new File(filePath);
            clubModel.setProfileImg(file);
            ImageConverter.copyInputStreamToFile(in, file);

        } catch (MysqlConnectionFailed | SQLException e ) {
            ErrorHandler.getInstance().handleException(e);
        }
        return clubModel;
    }

    public static ClubModel getClubByUserName(String username) throws SystemException {
        Statement stm = null;
        ClubModel clubModel = null;
        try {
            stm = MysqlConnection.mysqlConnection();

        ResultSet rs = Query.infoFromClub(username, stm);
        rs.first();
         clubModel = new ClubModel(username);

        //Serve per diminuire la duplicazione del codice
        setInfo(rs, clubModel);

        InputStream in = (rs.getBinaryStream(3));
        String filePath= username+"pic"+".png";
        File file = new File(filePath);
        ImageConverter.copyInputStreamToFile(in, file);
        clubModel.setProfileImg(file);
        } catch (MysqlConnectionFailed | SQLException e) {
                ErrorHandler.getInstance().handleException(e);
            }

        return clubModel;
    }

    private static void setInfo(ResultSet rs, ClubModel clubModel) {
        try {
            InfoLogged infoLogged = new InfoLogged(rs.getString(2), rs.getString(4), null, 1);

            clubModel.setId(rs.getInt(1));
            clubModel.setInfoLogged(infoLogged);
            clubModel.setNumFollower(rs.getInt(5));
            clubModel.setCity(rs.getString(6));
            clubModel.setAddress(rs.getString(7));
            clubModel.setClubName(rs.getString(8));
            clubModel.setWebsite(rs.getURL(9));
        } catch (SQLException throwables) {
            //ignored
        }
    }

}

