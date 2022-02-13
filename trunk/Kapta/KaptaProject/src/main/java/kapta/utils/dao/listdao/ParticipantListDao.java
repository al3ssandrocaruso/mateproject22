package kapta.utils.dao.listdao;


import kapta.model.lists.ParticipantsList;
import kapta.model.PartyEventModel;
import kapta.model.profiles.UserModel;
import kapta.utils.db.Query;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongQueryException;
import kapta.utils.Observer;
import kapta.utils.utils.MysqlConnection;
import kapta.utils.dao.UserDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParticipantListDao {

    private ParticipantListDao(){
        //ignored
    }

    public static ParticipantsList getParticipantList(PartyEventModel partyEventModel, Observer ob) {
        Statement stm = null;
        ParticipantsList participantsList = new ParticipantsList(partyEventModel, ob);
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rst = Query.askParticipantEventPartyById(partyEventModel.getId(),partyEventModel.getType(),stm);
            if(!rst.next()){
                return participantsList ;
            }
            else{
                int userId;
                rst.last();
                int size = rst.getRow();
                rst.first();
                for(int i=0; i<size; i++){
                    userId = rst.getInt(1);
                    UserModel participant = UserDao.getUserById(userId);
                    participantsList.addParticipant(participant);
                    rst.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MysqlConnectionFailed |WrongQueryException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        }
        return participantsList ;
    }


}
