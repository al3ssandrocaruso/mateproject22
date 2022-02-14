package kapta.application;

import javafx.event.ActionEvent;
import kapta.control.appcontroller.DeletePartyEventController;
import kapta.control.appcontroller.JoinPartyController;
import kapta.control.guicontroller.interfaceone.item.JFX1PartyItemGUIController;
import kapta.model.lists.ParticipantsList;
import kapta.model.PartyModel;
import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.ParticipantListDao;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.session.ThreadLocalSession;

import java.util.List;



public class PartyApplicationLayer {
    private PartyModel partyModel;
    private UserClubModel whoIam;
    private ParticipantsList participantsList;

    private JFX1PartyItemGUIController jfx1PartyItemGUIController;



    public PartyApplicationLayer(PartyModel partyModel, JFX1PartyItemGUIController jfx1PartyItemGUIController){
        setPartyModel(partyModel);
        setPartyItemGUIController(jfx1PartyItemGUIController);
        setWhoIam();
    }


    public PartyApplicationLayer(PartyModel partyModel, ParticipantsList participantsList){
        setParticipantsList(participantsList);
        setPartyModel(partyModel);
        setWhoIam();
    }

    public PartyApplicationLayer(PartyModel partyModel){
        setPartyModel(partyModel);
        setWhoIam();
    }



    public PartyModel getPartyModel() {
        return partyModel;
    }


    public JFX1PartyItemGUIController getPartyItemGUIController() {
        return jfx1PartyItemGUIController;
    }

    public void setPartyItemGUIController(JFX1PartyItemGUIController jfx1PartyItemGUIController) {
        this.jfx1PartyItemGUIController = jfx1PartyItemGUIController;
    }
    public void setPartyModel(PartyModel partyModel) {
        this.partyModel  = partyModel;
    }
    public ParticipantsList getParticipantsList() {
        return participantsList;
    }
    public void setParticipantsList(ParticipantsList participantsList) {this.participantsList = participantsList;}public UserClubModel getWhoIam() {
        return whoIam;
    }

    public void setWhoIam() {
        int type= ThreadLocalSession.getUserSession().get().getType();
        if(type==0){
            this.whoIam =  ThreadLocalSession.getUserSession().get().getUserModel();
            this.whoIam.getInfoLogged().setType(0);
        }else if(type==1){
            this.whoIam =  ThreadLocalSession.getUserSession().get().getClubModel();
            this.whoIam.getInfoLogged().setType(1);
        }
    }

    public boolean doIjoinedYet() { //fatta funzione in utils, sostituire con quella
        boolean state = false;
        List<UserModel> list = ParticipantListDao.getParticipantList(getPartyModel(), null).getParticipants();

        for(UserModel um : list) {
            if ( ThreadLocalSession.getUserSession().get().getUserModel().getId() == um.getId()) {
                state = true;
            }
        }
        return state;
    }
    public int goToJoinParty(){
        participantsList.addParticipant(ThreadLocalSession.getUserSession().get().getUserModel());
        return JoinPartyController.joinParty( ThreadLocalSession.getUserSession().get().getUserModel(), partyModel);
    }
    public void goToLeaveParty(){
        JoinPartyController.leaveParty( ThreadLocalSession.getUserSession().get().getUserModel(), partyModel);
    }
    public void goToPartyPage(ActionEvent ae, String fxml){
        if(fxml.startsWith("/JFX1")) {
            ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
            replaceSceneAndInitializePage.replaceSceneAndInitializePage(ae, fxml, this.partyModel);
        } else if(fxml.startsWith("/JFX2")){
            JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(ae, fxml, this.partyModel);}
    }
    public void goToDeleteParty(ActionEvent ae,String fxml){
        DeletePartyEventController.delete(this.partyModel);
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, fxml);
    }

    public int chooseDecoration(){
        if( getWhoIam().getUsername().equals(partyModel.getPartyCreator().getUsername())){return 1;}
        else if( getWhoIam().getType()==0){return 2;}
        return -1;
    }


}
