package kapta.utils;

import kapta.model.EventModel;
import kapta.model.GenericModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;

import java.util.ArrayList;
import java.util.List;

public  class GenericObservableList extends Subject {
    List<GenericModel> list = new ArrayList<>();

    public GenericObservableList(Observer obs) {
        super(obs);
    }

    public void addAllUsersToList(List<UserModel> userModelList ){
        if (userModelList != null){
            for (UserModel elem : userModelList) {
                this.list.add(0, elem);
                notifyObservers(elem);
            }
        }
    }
    public void addAllEventsToList(List<EventModel> eventModelList ){
        if(eventModelList != null) {
            for (EventModel elem : eventModelList) {
                this.list.add(0, elem);
                notifyObservers(elem);
            }
        }
    }

    public void addAllPartyToList(List<PartyModel> partyModelList){
        if (partyModelList != null) {
            for (PartyModel elem : partyModelList) {
                this.list.add(0, elem);
                notifyObservers(elem);
            }
        }

    }

}
