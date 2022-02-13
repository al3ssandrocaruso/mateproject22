package kapta.application;

import javafx.event.ActionEvent;
import kapta.control.appcontroller.DeletePartyEventController;
import kapta.control.appcontroller.JoinEventController;
import kapta.model.EventModel;
import kapta.model.profiles.UserClubModel;
import kapta.utils.bean.beanin.RequestBean;
import kapta.utils.dao.RequestDao;
import kapta.utils.exception.myexception.ExpiredGreenPassException;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceSceneAndInitializePage;

import static kapta.utils.session.ThreadLocalSession.userSession;

public class EventApplicationLayer {
    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    private EventModel eventModel;
    private UserClubModel whoIam;

    public EventApplicationLayer(EventModel eventModel) {
        setWhoIam();
        setEventModel(eventModel);
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public UserClubModel getWhoIam() {
        return whoIam;
    }

    public void setWhoIam() {
        int type=userSession.get().getType();
        if(type==1){
            this.whoIam=userSession.get().getClubModel();
        }
        else if(type==0){this.whoIam=userSession.get().getUserModel();}
    }

    public int chooseDecoration(){
        if(getWhoIam().getUsername().equals(getEventModel().getEventCreator().getUsername())){
            return 0;
        }
            return 1;
    }

    public void goToClubProfile(ActionEvent ae, String fxml) {
        ReplaceSceneAndInitializePage risp=new ReplaceSceneAndInitializePage();
        risp.replaceSceneAndInitializePage(ae,fxml,this.eventModel.getEventCreator());
    }

    public void goToDeleteEvent(ActionEvent ae, String fxml){
        DeletePartyEventController.delete(this.eventModel);
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, fxml);
    }

    public void sendRequest(RequestBean requestBean) throws ExpiredGreenPassException {
        JoinEventController.sendRequest(requestBean, this.eventModel);

    }
    public int getStatusRequest() {
        int requestId = 0;
        requestId = RequestDao.getRequestIdentifier(this.eventModel, userSession.get().getUserModel(), this.eventModel.getEventCreator());
        return RequestDao.getRequestStatus(requestId);
    }
    public void goToEventPage(ActionEvent ae, String fxml){
        if(fxml.startsWith("/JFX1")) {
            ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(ae, fxml, this.eventModel);}
        else if(fxml.startsWith("/JFX2")){
            JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(ae, fxml, this.eventModel);}
        }
    }
