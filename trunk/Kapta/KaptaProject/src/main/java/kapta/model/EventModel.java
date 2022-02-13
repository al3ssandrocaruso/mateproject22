package kapta.model;

import kapta.model.profiles.ClubModel;


import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class EventModel extends PartyEventModel {

    private Double eventPrice;
    private boolean greenPass;
    private ClubModel eventCreator;

    public EventModel(String eventName, Double eventPrice, int status, String eventAddress, Time eventDuration, LocalTime eventOrario, Date eventDate, boolean greenPass, File image, ClubModel clubModel){
        super(eventName, eventAddress, status, 1, eventDate, eventDuration, eventOrario, image);
        this.setEventPrice(eventPrice);
        this.setGreenPass(greenPass);
        this.setEventCreator(clubModel);
    }

    public  EventModel (int id ){
        super(id, 1) ;
    }


    // Set e Get
    public void setEventPrice(Double eventPrice) {
        this.eventPrice = eventPrice;
    }
    public void setGreenPass(boolean obbGreenPass) {
        this.greenPass = obbGreenPass;
    }
    public void setEventCreator(ClubModel eventCreator) {
        this.eventCreator = eventCreator;
    }

    public ClubModel getEventCreator() {
        return eventCreator;
    }
    public Double getEventPrice() {return eventPrice;}
    public boolean isGreenPass() {return greenPass;}
}
