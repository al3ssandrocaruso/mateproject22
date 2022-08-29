package kapta.utils.dao.listdao;

import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.utils.MysqlConnection;
import kapta.utils.dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FollowerListDao {

    private FollowerListDao(){
        //ignored
    }

    public static void addToFollowerList(UserModel listOwner, UserModel userToAdd) throws SystemException {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.addToListaFollower(listOwner.getId(),userToAdd.getId(),stm);
        } catch ( SQLException e) {
                ErrorHandler.getInstance().handleException(e);
        }
    }

    public static void removeFromFollowerList(UserModel listOwner, UserModel userToRemove) throws SystemException {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.removeFromListaFollower(listOwner.getId(),userToRemove.getId(),stm);
        } catch (SQLException e) {
                ErrorHandler.getInstance().handleException(e);
            }
    }


    public static List<UserModel> getFollower(UserClubModel userClubModel) throws SystemException {
        Statement stm = null;
        List<UserModel> followers = new ArrayList<>();
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rs = Query.askFollowerbyId(userClubModel.getId(), stm);
            UserModel eventModel;
            assert rs != null;
            if (rs.next()) {
                rs.first();
                do {
                    int index = rs.getInt(1);
                    eventModel = UserDao.getUserById(index);
                    followers.add(eventModel);
                } while (rs.next());

        } }
            catch ( SQLException e) {
           ErrorHandler.getInstance().handleException(e);
        }
        return  followers;
    }
}
