package kapta.model;


import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanin.PartyEventSchedule;

import java.io.File;

public class PartyModel extends PartyEventModel {

    private UserModel partyCreator;

    public PartyModel(String eventName,  int status, String eventAddress,File image, UserModel creator, PartyEventSchedule partyEventSchedule){
        super(eventName, eventAddress, status, 0, image, partyEventSchedule);
        this.setPartyCreator(creator);
    }

    public PartyModel(String eventName, int id ,  int status, String eventAddress,File image, UserModel creator, PartyEventSchedule partyEventSchedule){
        super(eventName, eventAddress, status, id,0 , image,partyEventSchedule);
        this.setPartyCreator(creator);
    }
    public PartyModel(int id){
        super(id, 0);
    }


    public UserModel getPartyCreator() {
        return partyCreator;
    }
    public void setPartyCreator(UserModel partyCreator) {
        this.partyCreator = partyCreator;
    }
}
