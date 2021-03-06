package kapta.utils.db;

import kapta.utils.bean.InfoEvent;
import kapta.utils.bean.PartyEventSchedule;
import kapta.utils.dao.EventDao;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongCrudException;
import kapta.utils.utils.MysqlConnection;
import kapta.utils.dao.PartyDao;

import java.io.*;
import java.sql.*;


public class CRUD {

    private CRUD(){
        //ignored
    }

    public static void addUserSavetoLogged(String username, String password, int type, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("INSERT INTO `Logged` (`Username`, `Password`, `Tipo`) VALUES ('%s', '%s', '%d');", username, password ,type);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void saveNewParty(Statement stm, String nameParty, PartyEventSchedule partyEventSchedule, String address, String usernameCreator, int status, File image) throws MysqlConnectionFailed, WrongCrudException {
        String timeToString = partyEventSchedule.getOrario().toString();
        String durationToString = partyEventSchedule.getDuration().toString();
        java.sql.Date cDate = new java.sql.Date(System.currentTimeMillis());
        String creationDateToString = cDate.toString();
        String saveStm = String.format("INSERT INTO `Party` (`Creator`, `name`, `date` ,`address`, `duration` , `creationDate`, `status` ,`orario` ) VALUES ( '%s', '%s','%s', '%s', '%s', '%s','%s', '%s' );", usernameCreator,nameParty,  partyEventSchedule.getDate().toString(), address,durationToString,  creationDateToString, status, timeToString );

        try {
            stm.executeUpdate(saveStm);
            PreparedStatement preparedStatement = MysqlConnection.upProfilePartyPhotoPS();
            InputStream in = new FileInputStream(image);
            preparedStatement.setBlob(1, in);
            preparedStatement.setInt(2, PartyDao.getIdByPartyName(nameParty));
            preparedStatement.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {
             if (e instanceof SQLException s){
                ErrorHandler.getInstance().sqlexceptioncrudhandler(s);
            }
        }
    }

    public static void saveNewEvent(Statement stm, PartyEventSchedule partyEventSchedule, String eventName, InfoEvent infoEvent, int idCreator, File img) throws  MysqlConnectionFailed, WrongCrudException {
        String stringOrarioEvento = partyEventSchedule.getOrario().toString();
        Time sqlOrarioEvento = Time.valueOf(partyEventSchedule.getOrario());
        java.sql.Date sqlEventDate = new  java.sql.Date(partyEventSchedule.getDate().getTime());
        String  durationToString= sqlOrarioEvento.toString();
        String sqlEventDateString= sqlEventDate.toString();
        String priceS = infoEvent.getPrice() + "";
        java.sql.Date cdate = new java.sql.Date(System.currentTimeMillis());
        String creationDate = cdate.toString();

        String saveStm = String.format("INSERT INTO `Evento` ( `Creator`, `numRequest`, `obbGreenPass`, `price`, `name`, `date`, `address`, `duration`, `creationDate`, `orario`, `status` ) " +
                "VALUES ('%d', '%d', '%d','%s', '%s'  , '%s', '%s','%s', '%s','%s' ,'%d');", idCreator , 0, infoEvent.getObb() , priceS,eventName ,  sqlEventDateString, infoEvent.getAddress(), durationToString, creationDate,  stringOrarioEvento,0);

        try {
            stm.executeUpdate(saveStm);
            PreparedStatement preparedStatement = MysqlConnection.upProfileEventPhotoPS();
            InputStream in = new FileInputStream(img);
            preparedStatement.setBlob(1, in);
            preparedStatement.setInt(2, EventDao.getIdByEventName(eventName));
            preparedStatement.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {
            if(e instanceof  SQLException sql ){
                ErrorHandler.getInstance().sqlexceptioncrudhandler(sql);
            }
        }
    }

    public static void addNewPartyInJoinedLIst(int userId, int partyId, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
         try {
             String addL = String.format("INSERT INTO JoinedList (`idOwner`,`joined_id` ,`type`) VALUES ('%d', '%d', 0);",userId, partyId );
             stm.executeUpdate(addL);
        } catch (SQLException e) {
             ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }
    public static void addNewEventInJoinedLIst(int userId, int eventId, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
        try {
            String addL = String.format("INSERT INTO JoinedList (`idOwner`,`joined_id` ,`type`) VALUES ('%d', '%d', 1);",userId, eventId );
            stm.executeUpdate(addL);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void addClubSavetoLogged(String username, String password, int tipo, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("INSERT INTO `Logged` (`Username`, `Password`, `Tipo`) VALUES ('%s', '%s', '%d');", username, password ,tipo);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void addToListaFollower(int id, int id1, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("INSERT INTO ListaFollower (`idOwner`,`UtenteFollower` ) VALUES ('%d', '%d' );",id,id1);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }

    }

    public static void addToListaFollowing(int id, int id1, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("INSERT INTO ListaFollowing (`idOwner`,`UtenteSeguito` ) VALUES ('%d', '%d' );",id,id1);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }

    }



    public static void removeFromListaFollowing(int id, int id1, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("DELETE FROM ListaFollowing WHERE `idOwner`= '%d' AND `UtenteSeguito` = '%d';",id,id1);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void removeFromListaFollower(int id, int id1, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("DELETE FROM ListaFollower WHERE `idOwner`= '%d' AND `UtenteFollower` = '%d';",id,id1);

        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void sendRequest(Statement stm, int idReceiver, int idSender, int idEvent, boolean greenPass, int numDoses, String vaccinationDate) throws MysqlConnectionFailed, WrongCrudException {
        int gp=0;
        if(greenPass){
            gp=1;
        }
        String saveStm = String.format("INSERT INTO `Request` (`idSender`, `idReceiver`, `idEvent`,`status`, `greenPass`, `numDoses`, `VaccinationDate`) VALUES ('%d', '%d', '%d','%d', '%d', '%d', '%s');", idSender, idReceiver,idEvent,0, gp, numDoses, vaccinationDate);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void acceptRequest(Statement stm,int requestIdentifier) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("UPDATE `Request` SET `status` = '1' WHERE (`RequestId` = '%d');", requestIdentifier);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void rejectRequest(Statement stm, int requestIdentifier) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("UPDATE `Request` SET `status` = '2' WHERE (`RequestId` = '%d');", requestIdentifier);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void deleteRequest(Statement stm, int requestIdentifier) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("DELETE from Request WHERE `RequestId` = '%d'", requestIdentifier);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void removePartyInJoinedLIst(int userId, int partyId, Statement stm) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("DELETE FROM JoinedList WHERE `idOwner`= '%d' AND `joined_id` = '%d' AND `type` = '%d';",userId,partyId, 0);
        try {
            stm.executeUpdate(saveStm);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }


    public static void deleteParty(Statement stm, int id) throws MysqlConnectionFailed, WrongCrudException {
        //Elimino il party nel db
        try {
            String saveStm = String.format("DELETE FROM Party WHERE `idParty` = '%d' ", id);
            stm.executeUpdate(saveStm);
            String saveStm1 = String.format("DELETE FROM joinedlist WHERE `joined_id` = '%d' AND `type` = '%d'", id, 0);
            stm.executeUpdate(saveStm1);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void deleteEvent(Statement stm, int id) throws MysqlConnectionFailed, WrongCrudException {
        //Elimino l'evento nel db
        try{
            String saveStm = String.format("DELETE FROM Evento WHERE `idEvento` = '%d' ", id);
            stm.executeUpdate(saveStm);

        //Elimino tutte le request associate all'evento eliminato
             String saveStm1 = String.format("DELETE FROM Request WHERE `idEvent` = '%d' ", id);
             stm.executeUpdate(saveStm1);

        //Elimino nella joined list
             String saveStm2 = String.format("DELETE FROM joinedlist WHERE `joined_id` = '%d' AND `type` = '%d'", id, 1);
             stm.executeUpdate(saveStm2);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }

    }


    public static void setUser(Statement stm, String username, String name, String secondName, String email, int id) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("UPDATE `Utente` SET `username` = '%s', `name` = '%s', `secondName` = '%s',`email` = '%s' WHERE (`idUtente` = '%d');", username, name, secondName, email, id);
        String saveStm1 = String.format("UPDATE `Logged` SET `username`= '%s' WHERE (`idLogged` = '%d');", username, id);

        try {
            stm.executeUpdate(saveStm);
            stm.executeUpdate(saveStm1);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }

    public static void setClub(Statement stm, String username, String city, String address, String email, int id) throws MysqlConnectionFailed, WrongCrudException {
        String saveStm = String.format("UPDATE `Manager` SET `username` = '%s', `city` = '%s', `address` = '%s',`email` = '%s' WHERE (`id` = '%d');", username, city, address, email, id);
        String saveStm1 = String.format("UPDATE `Logged` SET `username`= '%s' WHERE (`idLogged` = '%d');", username, id);

        try {
            stm.executeUpdate(saveStm);
            stm.executeUpdate(saveStm1);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptioncrudhandler(e);
        }
    }
}