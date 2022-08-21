package kapta.engineering;

import kapta.control.appcontroller.Search;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.GenericObservableList;
import kapta.utils.Observer;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.UserBean;

import java.util.ArrayList;
import java.util.List;



public  class ManageSearch {
    private  ManageSearch(){

    }

    public  static void search(Observer ob, String input){
        GenericObservableList list = new GenericObservableList(ob);
        list.addAllUsersToList(Search.searchUserByUsername(input));
        list.addAllEventsToList(Search.searchByEventName(input));
        list.addAllPartyToList(Search.searchByPartyName(input));
    }
    public static List<UserBean> searchUser(String input){
        List<UserModel> list= Search.searchUserByUsername(input);
        List<UserBean > out = new ArrayList<>();
        for(UserModel um : list){
            UserBean bean = new UserBean(um);
            out.add(bean);
        }
        return  out;
    }


    public static List<EventBean> searchEvent(String input){
        List<EventModel> list= Search.searchByEventName(input);
        List<EventBean> out= new ArrayList<>();
        for(EventModel um : list){
            EventBean bean = new EventBean(um);
            out.add(bean);
        }
        return  out;
    }


    public static List<PartyBean> searchParty(String input){
        List<PartyModel> list = Search.searchByPartyName(input) ;
        List<PartyBean> out= new ArrayList<>();
        for(PartyModel pm : list){
            PartyBean pb = new PartyBean(pm);
            out.add(pb);
        }
        return out;
    }


}
