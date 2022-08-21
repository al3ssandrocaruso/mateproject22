package kapta.utils.dao;


import kapta.model.PartyModel;
import kapta.utils.bean.PartyEventSchedule;
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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PartyDao {

    private PartyDao(){
        //ignored
    }

    public static void saveNewParty(PartyModel party) {
        String nameParty = party.getName();
        String address = party.getAddress();

        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            String creatorName =  ThreadLocalSession.getUserSession().get().getUserBean().getUsername();

            CRUD.saveNewParty(stm, nameParty, party.getPartyEventSchedule(), address, creatorName, 0, party.getImg());

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
            boolean ok = rst.first();
            new SimpleDateFormat("MM/dd/yyyy");
            if(ok){
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
            list.add(pm);
            }
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
