package kapta.utils.dao;


import kapta.model.PartyModel;
import kapta.utils.bean.beanin.PartyEventSchedule;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.exception.*;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongCrudException;
import kapta.utils.exception.myexception.WrongQueryException;
import kapta.utils.session.ThreadLocalSession;
import kapta.utils.utils.ImageConverter;
import kapta.utils.utils.MysqlConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartyDao {

    private PartyDao(){
        //ignored
    }

    public static void saveNewParty(PartyModel party) {
        String nameParty = party.getName();
        LocalTime time = party.getOrario();
        Time duration = party.getDuration();
        String address = party.getAddress();
        Date partyDate = party.getDate();

        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            String creatorName =  ThreadLocalSession.getUserSession().get().getUserModel().getUsername();
            Time sqlTime = Time.valueOf(time);
            java.sql.Date sqlPartyDate = new java.sql.Date( partyDate.getTime());
            java.sql.Date cDate = new java.sql.Date(System.currentTimeMillis());

            CRUD.saveNewParty(stm, nameParty, sqlTime, duration, address, creatorName, cDate, 0, sqlPartyDate, party.getImg());

        } catch (MysqlConnectionFailed | WrongCrudException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        }

    }
    public static List<PartyModel> getPartiesByPartyName(String partyName) throws SQLException{
        // in questa versone in realtà mi ritorna solo uno e soltanto un party id
        List<PartyModel> list = new ArrayList<>();
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rst = Query.askPartybyPartyName(stm, partyName );
            assert rst != null;
            rst.first();
            new SimpleDateFormat("MM/dd/yyyy");
            PartyModel pm = new PartyModel(rst.getInt(1));
            PartyEventSchedule partyEventSchedule = new PartyEventSchedule(rst.getDate(4),rst.getTime(6),rst.getObject(10, LocalTime.class) );
            pm.setPartyEventSchedule(partyEventSchedule);
            pm.setName(rst.getString(3));
            pm.setPartyCreator(UserDao.getUserByUsername(rst.getString(2)));
            pm.setAddress(rst.getString(5));
            pm.setStatus(rst.getInt(9));
            InputStream in = (rst.getBinaryStream(8));
            String filePath= pm.getName()+"pic"+".png";
            File file = new File(filePath);
            ImageConverter.copyInputStreamToFile(in, file);
            pm.setImg(file);
        }catch (MysqlConnectionFailed | WrongQueryException e){
            ErrorHandler.getInstance().reportFinalException(e);
        }
        catch (IOException e) {
           // not  getstita
        }catch (AssertionError assertionError){
            // empty list
            return list;
        }
        return list;
    }


    public static PartyModel getPartyById(int partyId) {
        PartyModel partyModel=null;
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rst = Query.askPartyInfoById(partyId, stm);
            rst.first();
            partyModel= new PartyModel(rst.getInt(1)) ;
            PartyEventSchedule partyEventSchedule = new PartyEventSchedule(rst.getDate(4),rst.getTime(6),rst.getObject(10, LocalTime.class) );
            partyModel.setName(rst.getString(3));
            partyModel.setPartyCreator(UserDao.getUserByUsername(rst.getString(2)));
            partyModel.setStatus(rst.getInt(9));
            partyModel.setPartyCreator(UserDao.getUserByUsername(rst.getString(2)));
            InputStream in = (rst.getBinaryStream(8));
            partyModel.setPartyEventSchedule(partyEventSchedule);
            String filePath= partyModel.getName()+"pic"+".png";
            File file = new File(filePath);
            ImageConverter.copyInputStreamToFile(in, file);
            partyModel.setImg(file);
            return partyModel;

        }
        catch ( IOException e) {
            // non gestit
        }
        catch (MysqlConnectionFailed | WrongQueryException  mysqlConnectionFailed) {
            mysqlConnectionFailed.printStackTrace();
        }
        catch (SQLException throwables) {
           return null;
        }
        return partyModel;
    }



    public static void removeParty(PartyModel partyModel)  {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.deleteParty(stm, partyModel.getId());
        } catch (MysqlConnectionFailed | WrongCrudException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        }
    }
    public static int getIdByPartyName(String partyName)  {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rst = Query.askPartybyPartyName(stm, partyName);
            rst.first();
            return rst.getInt(1);
        } catch ( MysqlConnectionFailed | WrongQueryException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException throwables) {
           // ingnorata[ricerca effettuata impossibile da soddisfare ]
        }
        return -1;
    }
}
