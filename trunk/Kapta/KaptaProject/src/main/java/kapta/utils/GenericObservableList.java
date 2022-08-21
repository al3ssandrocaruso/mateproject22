package kapta.utils;

import kapta.model.EventModel;
import kapta.model.GenericModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public  class GenericObservableList extends Subject {
    List<GenericModel> list = new ArrayList<>();

    public GenericObservableList(Observer obs) {
        super(obs);
    }

    public void addAllUsersToList(List<UserModel> userModelList ){
        if (userModelList != null && !userModelList.isEmpty()){
            for (UserModel elem : userModelList) {
                UserBean ub = new UserBean(elem);
                this.list.add(0, elem);
                notifyObservers(ub);
            }
        }
    }
    public void addAllEventsToList(List<EventModel> eventModelList ){
        if(eventModelList != null && !eventModelList.isEmpty()) {
            for (EventModel elem : eventModelList) {
                this.list.add(0, elem);
                EventBean eb = new  EventBean(elem);
                notifyObservers(eb);
            }
        }
    }

    public void addAllPartyToList(List<PartyModel> partyModelList){
        if (partyModelList != null && !partyModelList.isEmpty()) {
            for (PartyModel elem : partyModelList) {
                this.list.add(0, elem);
                PartyBean pb = new PartyBean(elem);
                notifyObservers(pb);
            }
        }

    }

}
