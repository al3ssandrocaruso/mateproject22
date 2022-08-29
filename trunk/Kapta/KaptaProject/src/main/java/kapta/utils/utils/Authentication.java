package kapta.utils.utils;

import kapta.utils.bean.jfx1.JFX1ProfileBean;
import kapta.utils.db.Query;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.SystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentication {

    private Authentication(){
        //ignore
    }

    public static int checkIsRegistered(int tipo, String password, String username) throws SystemException {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = Query.searchUserInLogged(username, password, stm, tipo);
            if (rs.next()) {
                return 1;
            }
        } catch (MysqlConnectionFailed  m) {
            ErrorHandler.getInstance().handleException(m);
        } catch (SQLException e) {
            // non gestita.
        }
        return 0;
    }

    public static boolean checkRegistered(JFX1ProfileBean profileBean) throws SystemException {
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            String username = profileBean.getUsername();
            rs = Query.searchUsernameInLogged(username, stm);
            return !rs.first();
        } catch (MysqlConnectionFailed  m) {
            ErrorHandler.getInstance().handleException(m);
        } catch (SQLException throwables) {
            // non gestito
        }
        return false;

    }
}