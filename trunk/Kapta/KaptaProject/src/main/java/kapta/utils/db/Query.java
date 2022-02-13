package kapta.utils.db;

import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongQueryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//I return null servono solo per poter compilare => sono provvisori

public class Query {

    private Query(){
        //ignored
    }

    public static ResultSet searchUserInLogged(String username , String password, Statement stm, int tipo) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT * FROM Logged where Password = '" + password + "'and Username = '" + username + "'and Tipo= '" + tipo+ "' ;";

        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;

    }

    public static ResultSet searchUsernameInLogged(String username , Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT * FROM Logged where Username = '" + username + "' ;";

        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askIdFromLogged(Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = " SELECT MAX(idLogged) FROM Logged; ";
        try {
            return stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askJoinedbyId(int id, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        // qui anche bisogna fare la discrimanzione di tipo
        String sql = " SELECT joined_id, type FROM JoinedList WHERE idOwner = '" + id + "' ;" ;

        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askParticipantEventPartyById(int partyId, int tipo, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = " SELECT idOwner FROM JoinedList WHERE joined_id = '" + partyId + "'AND type = '"+tipo+ "' ;";
        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askSeguitibyId(int id, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = " SELECT UtenteSeguito FROM ListaFollowing WHERE idOwner = '" + id + "' ;" ;

        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askRequestListByUserId(int idUser, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = " SELECT * FROM Request WHERE idSender = '" + idUser + "' ;" ;

        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }
    public static ResultSet askPendingRequestListByClubId(int idOwner, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = " SELECT * FROM Request WHERE idReceiver = '" + idOwner + "'and status= '" + 0 + "' ;";
        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }
    public static ResultSet askFollowerbyId(int id, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = " SELECT UtenteFollower FROM ListaFollower WHERE idOwner = '" + id + "' ;" ;
        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askPartyInfoById(int idParty , Statement stm ) throws MysqlConnectionFailed, WrongQueryException {
        String sql = " SELECT * FROM Party WHERE idParty = '" + idParty + "' ;";
        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {

            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }


    public static ResultSet infoFromClub(String username, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT * FROM Manager where username = '" + username+ "' ;";
        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet infoFromClubById(int id,  Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT * FROM Manager where id = '" + id+ "' ;";

        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askInfobyUsername(Statement stm, String username) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT * FROM Utente where username = '" + username + "' ;";
        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askPartybyPartyName(Statement stm, String input) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT * FROM Party where name = '" + input + "' ;";

        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;

    }

    public static ResultSet askEventbyEventName(Statement stm, String input) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT * FROM Evento where name = '" + input + "' ;";

        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }
    public static ResultSet askUserByUserId(Statement stm,int id) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT * FROM Utente where idUtente = '" + id + "' ;";

        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askEventbyEventId(int id, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
            String sql = "SELECT * FROM Evento where idEvento = '" + id + "' ;";

        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }
    public static ResultSet askRequestIdByInfo(int idSender,int idReceiver,int idEvent,Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT RequestId FROM Request where idSender = '" + idSender + "'and idReceiver = '" + idReceiver + "'and idEvent= '" + idEvent + "' ;";
        try {
            return stm.executeQuery(sql) ;
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;

    }

    public static ResultSet askStatusByRequestIdentifier(Statement stm, int requestId) throws MysqlConnectionFailed, WrongQueryException {
        String sql = "SELECT status FROM Request WHERE RequestId = '" + requestId + "' ;";

        try {
            return stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public static ResultSet askCreatedEventListByClubId(int clubId, Statement stm) throws MysqlConnectionFailed, WrongQueryException {
        String sql = " SELECT * FROM Evento WHERE Creator = '" + clubId + "' ;" ;
        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }

    public  static ResultSet askIdbyClubName(Statement stm, String eventName) throws MysqlConnectionFailed, WrongQueryException {
        String sql= " SELECT id FROM Manager WHERE username = '" + eventName + "' ;";
        try {
            return  stm.executeQuery(sql);
        } catch (SQLException e) {
            ErrorHandler.getInstance().sqlexceptionqueryhandler(e);
        }
        return null;
    }
}