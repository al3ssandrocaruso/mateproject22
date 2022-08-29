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
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.Observer;
import kapta.utils.utils.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoinedListDAO {

    private JoinedListDAO(){
        //ignored
    }

    public static void addJoinedParty(UserModel um, PartyModel pm) throws SystemException {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.addNewPartyInJoinedLIst(um.getId(), pm.getId(), stm);
        } catch (MysqlConnectionFailed  | SQLException e) {
                ErrorHandler.getInstance().handleException(e);
        }
    }

    public static void addJoinedEvent(UserModel um, EventModel pm) throws SystemException {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.addNewEventInJoinedLIst(um.getId(), pm.getId(), stm);
        } catch (MysqlConnectionFailed | SQLException e) {
            if(e instanceof  MysqlConnectionFailed m ){
                ErrorHandler.getInstance().handleException(m);
            }
        }
    }

    public static void removeJoinedParty(UserModel um, PartyModel pm) throws SystemException {
        int partyId= pm.getId();
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.removePartyInJoinedLIst(um.getId(), partyId, stm);
        } catch (MysqlConnectionFailed  | SQLException e) {
            if(e instanceof  MysqlConnectionFailed m ){
                ErrorHandler.getInstance().handleException(m);
            }
        }
    }

    public static JoinedList getJoined(UserModel um, Observer obs) throws SystemException {
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
        } catch (MysqlConnectionFailed | SQLException  e) {
            ErrorHandler.getInstance().handleException(e);
        }
        return joinedList ;
    }
}
