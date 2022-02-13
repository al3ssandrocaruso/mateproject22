package kapta.model;


import kapta.model.profiles.UserModel;
import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class PartyModel extends PartyEventModel {

    private UserModel partyCreator;

    public PartyModel(String eventName,  int status, String eventAddress, Time eventDuration, LocalTime eventOrario, Date eventDate,File image, UserModel creator){
        super(eventName, eventAddress, status, 0,  eventDate, eventDuration, eventOrario, image);
        this.setPartyCreator(creator);
    }

    public PartyModel(String eventName, int id ,  int status, String eventAddress, Time eventDuration, LocalTime eventOrario, Date eventDate,File image, UserModel creator){
        super(eventName, eventAddress, status, id,0 ,eventDate, eventDuration, eventOrario, image);
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
