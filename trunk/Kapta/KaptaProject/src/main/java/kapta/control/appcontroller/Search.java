package kapta.control.appcontroller;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.UserDao;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Search {

    private Search(){
        //ignored
    }

    public static List<UserModel> searchUserByUsername(String input) {
        return UserDao.usersByUsername(input);
    }

    public static List<PartyModel> searchByPartyName(String input) {
        try {
            return PartyDao.getPartiesByPartyName(input);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  Collections.emptyList();
    }

    public static List<EventModel> searchByEventName(String input ) {
        return  EventDao.getEventsbyEventName(input);
    }


}
