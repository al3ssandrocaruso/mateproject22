package kapta.utils.dao.listdao;

import kapta.model.EventModel;
import kapta.model.lists.JoinedList;
import kapta.model.PartyEventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;
import kapta.utils.exception.*;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongCrudException;
import kapta.utils.exception.myexception.WrongQueryException;
import kapta.utils.Observer;
import kapta.utils.utils.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoinedListDAO {

    private JoinedListDAO(){
        //ignored
    }

    public static void addJoinedParty(UserModel um, PartyModel pm)  {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.addNewPartyInJoinedLIst(um.getId(), pm.getId(), stm);
        } catch (MysqlConnectionFailed  | WrongCrudException e) {
                ErrorHandler.getInstance().reportFinalException(e);
        }
    }

    public static void addJoinedEvent(UserModel um, EventModel pm){
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.addNewEventInJoinedLIst(um.getId(), pm.getId(), stm);
        } catch (MysqlConnectionFailed | WrongCrudException e) {
            if(e instanceof  MysqlConnectionFailed m ){
                ErrorHandler.getInstance().reportFinalException(m);
            }
        }
    }

    public static void removeJoinedParty(UserModel um, PartyModel pm) {
        int partyId= pm.getId();
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.removePartyInJoinedLIst(um.getId(), partyId, stm);
        } catch (MysqlConnectionFailed  | WrongCrudException e) {
            if(e instanceof  MysqlConnectionFailed m ){
                ErrorHandler.getInstance().reportFinalException(m);
            }
        }
    }

    public static JoinedList getJoined(UserModel um, Observer obs) {
//OK EXCEPTION, non è vero
        Statement stm = null;
        List<PartyEventModel> joined= new ArrayList<>();
        JoinedList joinedList = new JoinedList(um, joined, obs);
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = Query.askJoinedbyId(um.getId(), stm);
            PartyEventModel partyEvent;
            if(!rs.next()){
               return joinedList;
            }
            rs.first();
            do {
             int index = rs.getInt(1);
             int type=rs.getInt(2);
             if(type ==0){
                partyEvent = PartyDao.getPartyById(index);
                if(partyEvent != null){
                    joinedList.addPartyEventModel(partyEvent);
                }}
                else {
                    partyEvent = EventDao.getEventbyEventId(index);
                    if (partyEvent != null) {
                        joinedList.addPartyEventModel(partyEvent);
                    }
                }

            } while (rs.next());
        } catch (SQLException |AssertionError e) {
            // non gestite
        } catch (MysqlConnectionFailed | WrongQueryException  e) {
            ErrorHandler.getInstance().reportFinalException(e);
        }
        return joinedList ;
    }
}
