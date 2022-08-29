package kapta.utils.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//I return null servono solo per poter compilare => sono provvisori

public class Query {

    private Query(){
        //ignored
    }

    public static ResultSet searchUserInLogged(String username , String password, Statement stm, int tipo) throws  SQLException {
        String sql = "SELECT * FROM Logged where Password = '" + password + "'and Username = '" + username + "'and Tipo= '" + tipo+ "' ;";
        return stm.executeQuery(sql) ;
    }

    public static ResultSet searchUsernameInLogged(String username , Statement stm) throws   SQLException{
        String sql = "SELECT * FROM Logged where Username = '" + username + "' ;";


            return stm.executeQuery(sql) ;


    }

    public static ResultSet askIdFromLogged(Statement stm) throws SQLException{
        String sql = " SELECT MAX(idLogged) FROM Logged; ";

            return stm.executeQuery(sql);

    }

    public static ResultSet askJoinedbyId(int id, Statement stm) throws SQLException {
        // qui anche bisogna fare la discrimanzione di tipo
        String sql = " SELECT joined_id, type FROM JoinedList WHERE idOwner = '" + id + "' ;" ;


            return  stm.executeQuery(sql);

    }

    public static ResultSet askParticipantEventPartyById(int partyId, int tipo, Statement stm) throws SQLException   {
        String sql = " SELECT idOwner FROM JoinedList WHERE joined_id = '" + partyId + "'AND type = '"+tipo+ "' ;";

            return  stm.executeQuery(sql);

    }

    public static ResultSet askSeguitibyId(int id, Statement stm) throws SQLException {
        String sql = " SELECT UtenteSeguito FROM ListaFollowing WHERE idOwner = '" + id + "' ;" ;


            return  stm.executeQuery(sql);

    }

    public static ResultSet askRequestListByUserId(int idUser, Statement stm) throws SQLException  {
        String sql = " SELECT * FROM Request WHERE idSender = '" + idUser + "' ;" ;


            return  stm.executeQuery(sql);

    }
    public static ResultSet askPendingRequestListByClubId(int idOwner, Statement stm) throws  SQLException {
        String sql = " SELECT * FROM Request WHERE idReceiver = '" + idOwner + "'and status= '" + 0 + "' ;";

            return  stm.executeQuery(sql);

    }
    public static ResultSet askFollowerbyId(int id, Statement stm) throws SQLException {
        String sql = " SELECT UtenteFollower FROM ListaFollower WHERE idOwner = '" + id + "' ;" ;

            return  stm.executeQuery(sql);

    }

    public static ResultSet askPartyInfoById(int idParty , Statement stm ) throws SQLException {
        String sql = " SELECT * FROM Party WHERE idParty = '" + idParty + "' ;";

            return  stm.executeQuery(sql);

    }


    public static ResultSet infoFromClub(String username, Statement stm) throws  SQLException{
        String sql = "SELECT * FROM Manager where username = '" + username + "' ;";

        return stm.executeQuery(sql);
    }


    public static ResultSet infoFromClubById(int id,  Statement stm) throws SQLException {
        String sql = "SELECT * FROM Manager where id = '" + id+ "' ;";


            return stm.executeQuery(sql) ;

    }

    public static ResultSet askInfobyUsername(Statement stm, String username) throws SQLException{
        String sql = "SELECT * FROM Utente where username = '" + username + "' ;";

            return stm.executeQuery(sql) ;

    }

    public static ResultSet askPartybyPartyName(Statement stm, String input) throws SQLException  {
        String sql = "SELECT * FROM Party where name = '" + input + "' ;";


            return stm.executeQuery(sql) ;


    }

    public static ResultSet askEventbyEventName(Statement stm, String input) throws SQLException {
        String sql = "SELECT * FROM Evento where name = '" + input + "' ;";

            return stm.executeQuery(sql) ;

    }
    public static ResultSet askUserByUserId(Statement stm,int id) throws SQLException{
        String sql = "SELECT * FROM Utente where idUtente = '" + id + "' ;";


            return stm.executeQuery(sql) ;

    }

    public static ResultSet askEventbyEventId(int id, Statement stm) throws SQLException {
            String sql = "SELECT * FROM Evento where idEvento = '" + id + "' ;";


            return stm.executeQuery(sql) ;

    }
    public static ResultSet askRequestIdByInfo(int idSender,int idReceiver,int idEvent,Statement stm) throws SQLException {
        String sql = "SELECT RequestId FROM Request where idSender = '" + idSender + "'and idReceiver = '" + idReceiver + "'and idEvent= '" + idEvent + "' ;";

            return stm.executeQuery(sql) ;


    }

    public static ResultSet askStatusByRequestIdentifier(Statement stm, int requestId) throws  SQLException{
        String sql = "SELECT status FROM Request WHERE RequestId = '" + requestId + "' ;";

            return stm.executeQuery(sql);

    }

    public static ResultSet askCreatedEventListByClubId(int clubId, Statement stm) throws  SQLException{
        String sql = " SELECT * FROM Evento WHERE Creator = '" + clubId + "' ;" ;

            return  stm.executeQuery(sql);

    }

    public  static ResultSet askIdbyClubName(Statement stm, String eventName) throws  SQLException{
        String sql = " SELECT id FROM Manager WHERE username = '" + eventName + "' ;";

        return stm.executeQuery(sql);
    }
}