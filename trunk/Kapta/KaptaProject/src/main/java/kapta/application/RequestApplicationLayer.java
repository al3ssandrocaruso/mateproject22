package kapta.application;

import javafx.event.ActionEvent;
import kapta.control.appcontroller.JoinEventController;
import kapta.model.EventModel;
import kapta.model.RequestModel;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceSceneAndInitializePage;

public class RequestApplicationLayer {

    private RequestModel requestModel;
    private EventModel eventModel;


    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }
    public void setRequestModel(RequestModel requestModel) {
        this.requestModel = requestModel;
    }
    public RequestModel getRequestModel() {
        return requestModel;
    }

    public RequestApplicationLayer(RequestModel requestModel) {
        setRequestModel(requestModel);
        setEventModel(requestModel.getEvent());
    }


    public void goToRejectRequest(ActionEvent actionEvent){
        JoinEventController.rejectRequest(this.requestModel);
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubRequestPage.fxml" );
    }
    public void goToAcceptRequest(ActionEvent actionEvent){
        JoinEventController.acceptRequest(this.requestModel);
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubRequestPage.fxml" );   }
    public void goToDeleteRequest(ActionEvent actionEvent, String fxml){
        JoinEventController.userDeleteRequest(this.requestModel);
        if(fxml.startsWith("/JFX1")) {
            ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
            replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent, fxml);
        }else if(fxml.startsWith("/JFX2")){
            JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(actionEvent, fxml);}
        }
    public void navigateToEventPage(ActionEvent actionEvent, String fxml){
        if(fxml.startsWith("/JFX1")) {
            ReplaceSceneAndInitializePage rp = new ReplaceSceneAndInitializePage();
            rp.replaceSceneAndInitializePage(actionEvent, fxml, this.eventModel);
        }else if(fxml.startsWith("/JFX2")){
            JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(actionEvent, fxml, this.eventModel);}
    }
}