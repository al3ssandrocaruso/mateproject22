package kapta.utils.dao.listdao;

import kapta.model.EventModel;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.model.RequestModel;
import kapta.utils.db.Query;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.EventDao;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongQueryException;
import kapta.utils.utils.MysqlConnection;
import kapta.utils.dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RequestListDao {

    private RequestListDao(){
        //ignored
    }

    public static List<RequestModel> getAllTypeRequests(UserModel userModel) {
        Statement stm = null;
        List<RequestModel> output=new ArrayList<>();
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs=Query.askRequestListByUserId(userModel.getId(),stm);
            RequestModel requestModel;
            UserDao.getUserById(userModel.getId());
            assert rs != null;
            if(!rs.next()){
                return output;
            }
            else {
                rs.first();
                do{
                    int requestId=rs.getInt(1);
                    int idSender=rs.getInt(2);
                    boolean gp=rs.getBoolean(4);
                    int status=rs.getInt(3);
                    int idEvent=rs.getInt(6);
                    EventModel eventModel = EventDao.getEventbyEventId(idEvent);
                    if(gp){
                        String vaccinationDate = rs.getString(8);
                        int numDoses = rs.getInt(7);
                        //Nell'app controller andrebbe fatta l'istanziazione della model
                        requestModel=new RequestModel(eventModel,UserDao.getUserById(idSender), eventModel.getEventCreator(),requestId,gp, vaccinationDate, numDoses);
                        requestModel.setStatus(status);
                        output.add(requestModel);
                    } else {
                        requestModel=new RequestModel(eventModel,UserDao.getUserById(idSender),eventModel.getEventCreator(),requestId,gp,null, 0);
                        output.add(requestModel);
                    }
                }while(rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MysqlConnectionFailed | WrongQueryException f) {
            ErrorHandler.getInstance().reportFinalException(f);
        }
        return  output;

    }
    public static List<RequestModel> getPendingRequestsByClubId(int idOwner) {
        Statement stm = null;
        List<RequestModel> output=new ArrayList<>();
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs= Query.askPendingRequestListByClubId(idOwner,stm);
             RequestModel requestModel;
             ClubModel receiver= ClubDao.clubModelByID(idOwner);
            if(!rs.next()){
               return output;
             }
            else {
              rs.first();
            do{
                int requestId=rs.getInt(1);
                int idSender=rs.getInt(2);
                int idEvent=rs.getInt(6);
                boolean gp=rs.getBoolean(4);
                if(gp){
                    String vaccinationDate = rs.getString(8);
                    int numDoses = rs.getInt(7);
                    requestModel=new RequestModel(EventDao.getEventbyEventId(idEvent), UserDao.getUserById(idSender),receiver,requestId,gp, vaccinationDate, numDoses);
                    output.add(requestModel); //qua request è ok!
                } else {
                    requestModel=new RequestModel(EventDao.getEventbyEventId(idEvent),UserDao.getUserById(idSender),receiver,requestId,gp,null, 0);
                    output.add(requestModel);
                }

            }while(rs.next());
            }
        }catch (MysqlConnectionFailed | WrongQueryException e){
            ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException throwables) {
            // non gestita
        }
        return  output;
    }
}
