package kapta.application;

import javafx.event.ActionEvent;
import kapta.control.guicontroller.interfaceone.JFX1FollowerFollowingListGuiController;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.session.ThreadLocalSession;


public class FollowerFollowingListApplicationLayer {

    private UserClubModel owner;
    private JFX1FollowerFollowingListGuiController jfx1FollowerFollowingListGuiController;

    public FollowerFollowingListApplicationLayer(JFX1FollowerFollowingListGuiController jfx1FollowerFollowingListGuiController, int support, UserModel owner){
        jfx1FollowerFollowingListGuiController.setFollowerFollowingListApplication(this);
        setFollowerFollowingListGuiController(jfx1FollowerFollowingListGuiController);
        new FollowingList(owner, FollowingListDao.getFollowing(owner), jfx1FollowerFollowingListGuiController);
        new FollowerList(owner, FollowerListDao.getFollower(owner), jfx1FollowerFollowingListGuiController);
        setOwner(owner);

        switch (support) {
            case 0:{
                navigationFollower();
                break;
            }
            case 1: {
                navigationFollowing();
                break;
            }
            default: break;
        }
    }

    public void setFollowerFollowingListGuiController(JFX1FollowerFollowingListGuiController jfx1FollowerFollowingListGuiController) {
        this.jfx1FollowerFollowingListGuiController = jfx1FollowerFollowingListGuiController;
    }

    public void setOwner(UserClubModel um){
        this.owner = um;
    }

    public UserClubModel getOwner(){
        return owner;
    }

    public void navigationBack(ActionEvent actionEvent) {
        int type= ThreadLocalSession.getUserSession().get().getType();
        ReplaceSceneAndInitializePage rp = new ReplaceSceneAndInitializePage();

        UserClubModel ucm = getOwner();

        //Tutti i casi possibili: sono un utente e sto nel profilo di un altro utente, sto nel mio profilo, sto nel profilo di un club ...
        switch(type){
            case 0:
                if(ucm.getUsername().equals( ThreadLocalSession.getUserSession().get().getUserModel().getUsername())){
                    rp.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1UserProfile.fxml",  ThreadLocalSession.getUserSession().get().getUserModel());}
                else {
                    rp.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1UserProfile.fxml", ucm);
                }
                break;
            case 1:
                if(ucm.getUsername().equals( ThreadLocalSession.getUserSession().get().getClubModel().getUsername())){
                    rp.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubProfile.fxml",  ThreadLocalSession.getUserSession().get().getClubModel());
                } else {
                    rp.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubProfile.fxml", ucm);
                }
                break;
            default:
        }
    }

    public void navigationFollowing() {
        jfx1FollowerFollowingListGuiController.getApFollowing().toFront();
        jfx1FollowerFollowingListGuiController.getApFollower().toBack();
    }

    public void navigationFollower() {
        jfx1FollowerFollowingListGuiController.getApFollowing().toBack();
        jfx1FollowerFollowingListGuiController.getApFollower().toFront();
    }
}
