package kapta.application;

import javafx.event.ActionEvent;
import kapta.control.appcontroller.CreateEventController;
import kapta.model.EventModel;
import kapta.model.lists.CreatedEventList;
import kapta.model.profiles.ClubModel;
import kapta.model.TokenModel;
import kapta.utils.bean.beanin.jfx2.JFX2EventBean;
import kapta.utils.bean.beanin.TokenBeanIn;
import kapta.utils.exception.myexception.TokenException;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import java.io.File;
import static kapta.utils.session.ThreadLocalSession.userSession;

public class ClubProfileApplicationLayer {

    private TokenModel token1; //the right token
    private ClubModel clubModel;
    private File imgEvent;
    private CreatedEventList createdEventList ;
    private EventModel eventModel;

    public void setToken1(TokenModel token1) {this.token1 = token1;}
    public void setEventModel(EventModel eventModel) {this.eventModel = eventModel;}
    public void setClubModel(ClubModel clubModel) {this.clubModel = clubModel;}
    public void setImgEvent(File imgEvent) {this.imgEvent = imgEvent;}
    private void setJFX2CreatedEventList(CreatedEventList createdEventList) {this.createdEventList = createdEventList;}

    public File getImgEvent() {return imgEvent;}
    public TokenModel getToken1() {return token1;}
    public ClubModel getClubModel() {return clubModel;}

    public ClubProfileApplicationLayer(ClubModel clubModel) {
        setClubModel(clubModel);
    }


    public ClubProfileApplicationLayer( CreatedEventList createdEventList) {
        setJFX2CreatedEventList(createdEventList);
    }

    public void goToCreateEvent(JFX2EventBean eventBean){
        eventBean.setEventImg(imgEvent);
        setEventModel(CreateEventController.createEvent(eventBean));
        this.createdEventList.addEvent(eventModel);
    }

    public void navigateToClubCreateEvent(ActionEvent actionEvent) {
        ReplaceSceneAndInitializePage rsip =  new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(actionEvent, "/JFX1/JFX1ClubCreateEvent.fxml", this.clubModel);
    }

    public boolean hideBtnCreateEvent() {
        return userSession.get().getUserModel() != null;
    }

    public void goToGenerateToken() {
        setToken1(CreateEventController.generateToken());
    }
    //per entrambe le interfacce
    public void goToCompareToken(TokenBeanIn tokenBeanIn) throws TokenException {
        TokenModel token2= new TokenModel(tokenBeanIn.getTokenString());
        CreateEventController.checkToken(this.getToken1(),token2);
    }
}
