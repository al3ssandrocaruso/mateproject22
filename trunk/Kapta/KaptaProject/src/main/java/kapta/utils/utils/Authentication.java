package kapta.utils.utils;

import kapta.utils.bean.J1.JFX1ProfileBean;
import kapta.utils.db.Query;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongQueryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentication {

    private Authentication(){
        //ignore
    }

    public static int checkIsRegistered(int tipo, String password, String username) {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = Query.searchUserInLogged(username, password, stm, tipo);
            if (rs.next()) {
                return 1;
            }
        } catch (MysqlConnectionFailed | WrongQueryException m) {
            ErrorHandler.getInstance().reportFinalException(m);
        } catch (SQLException e) {
            // non gestita.
        }
        return 0;
    }

    public static boolean checkRegistered(JFX1ProfileBean profileBean)  {
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            String username = profileBean.getUsername();
            rs = Query.searchUsernameInLogged(username, stm);
            return !rs.first();
        } catch (MysqlConnectionFailed | WrongQueryException m) {
            ErrorHandler.getInstance().reportFinalException(m);
        } catch (SQLException throwables) {
            // non gestito
        }
        return false;

    }
}