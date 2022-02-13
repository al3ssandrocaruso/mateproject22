package kapta.utils.dao;

import kapta.model.EventModel;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.model.RequestModel;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongCrudException;
import kapta.utils.exception.myexception.WrongQueryException;
import kapta.utils.utils.MysqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestDao {

    private RequestDao(){
        //ignored
    }


    public static void rejectRequest(EventModel event, UserModel sender, ClubModel owner) {
    int requestIdentifier=getRequestIdentifier(event, sender, owner);
    Statement stm = null;
    try {
        stm = MysqlConnection.mysqlConnection();
        CRUD.rejectRequest(stm,requestIdentifier);
    } catch (MysqlConnectionFailed | WrongCrudException e) {
        ErrorHandler.getInstance().reportFinalException(e);
    }

}
    public static void sendNewRequest( RequestModel requestModel) {

        EventModel eventModel = requestModel.getEvent();
        UserModel sender= requestModel.getSender();
        int numDoses= requestModel.getDoses();
        String vaccinationDate = requestModel.getVaccinationDate();

        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.sendRequest(stm,eventModel.getEventCreator().getId(),sender.getId(),eventModel.getId(), eventModel.isGreenPass(), numDoses, vaccinationDate);

        } catch (MysqlConnectionFailed | WrongCrudException e) {
            if(e instanceof  MysqlConnectionFailed m){
                ErrorHandler.getInstance().reportFinalException(m);
            }
        }
}



    // queste azioni non devono essere fatte nel caso d'uso
    public static void acceptRequest(EventModel event, UserModel sender, ClubModel owner) {
        int requestIdentifier=getRequestIdentifier(event, sender, owner);
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.acceptRequest(stm,requestIdentifier);
        } catch (MysqlConnectionFailed | WrongCrudException e) {
                ErrorHandler.getInstance().reportFinalException(e);
            }
    }
    public static void userDeleteRequest(EventModel eventModel, UserModel userModel, ClubModel owner) {
        int requestIdentifier = getRequestIdentifier(eventModel, userModel, owner);
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.deleteRequest(stm, requestIdentifier);
        } catch (MysqlConnectionFailed | WrongCrudException e){
                ErrorHandler.getInstance().reportFinalException(e);
            }
    }

    public static int getRequestIdentifier(EventModel event, UserModel sender, ClubModel owner) {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet resultSet= Query.askRequestIdByInfo(sender.getId(),owner.getId(),event.getId(),stm);
            if(!resultSet.next()){
                return -1;
            }else{
                resultSet.first();
                return resultSet.getInt(1);
            }
        }
        catch ( MysqlConnectionFailed | WrongQueryException e) {
                ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException throwables) {
           // non gestita
        }
        return -1;
    }

    public static int getRequestStatus(int requestId)  {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet resultSet = Query.askStatusByRequestIdentifier(stm, requestId);
            if(!resultSet.next()){
                return -1;
            }
            resultSet.first();
            return resultSet.getInt(1);

        }catch (MysqlConnectionFailed| WrongQueryException e) {
            ErrorHandler.getInstance().reportFinalException(e);

        }catch (SQLException throwables) {
            // non gestita
        }
        return -1;

    }
}
