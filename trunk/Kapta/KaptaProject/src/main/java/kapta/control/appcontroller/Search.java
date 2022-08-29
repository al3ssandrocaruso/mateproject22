package kapta.control.appcontroller;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.UserDao;
import kapta.utils.exception.myexception.SystemException;
import java.util.Collections;
import java.util.List;

public class Search {

    private Search(){
        //ignored
    }

    public static List<UserModel> searchUserByUsername(String input) {
        try {
            return UserDao.usersByUsername(input);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return  Collections.emptyList();
    }

    public static List<PartyModel> searchByPartyName(String input)  {

        try {
            return PartyDao.getPartiesByPartyName(input);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return  Collections.emptyList();
    }

    public static List<EventModel> searchByEventName(String input ) {
        try {
            return  EventDao.getEventsbyEventName(input);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return  Collections.emptyList();
    }


}
