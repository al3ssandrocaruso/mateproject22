package kapta.utils.dao;

import kapta.utils.db.CRUD;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.utils.MysqlConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class SettingDao {

    private SettingDao(){
        //ignored
    }

    public static void setUser(String username, String name, String secondName, String email, int id ) throws SystemException {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.setUser(stm, username, name, secondName, email, id);
        } catch (MysqlConnectionFailed | SQLException e) {
            ErrorHandler.getInstance().handleException(e);
        }
    }

    public static void setClub(String username, String city, String address, String email, int id) throws SystemException {

        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.setClub(stm, username, city, address, email, id);
        } catch (MysqlConnectionFailed | SQLException e) {
            ErrorHandler.getInstance().handleException(e);
        }
    }
}
