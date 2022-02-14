package kapta.application;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import kapta.control.appcontroller.FollowUserController;
import kapta.control.guicontroller.interfaceone.JFX1CreatePartyGUIController;
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.control.guicontroller.interfaceone.JFX1UserProfileGuiController;
import kapta.control.guicontroller.interfacetwo.JFX2UserProfileGUIController;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.JoinedListDAO;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.session.ThreadLocalSession;
import kapta.utils.utils.FollowUtils;
import java.io.File;



public class UserProfileApplicationLayer {
    // queste le devo spostare nel Gui controller
    private UserClubModel whoIam;
    private UserModel userModel;
    private FollowingList followingList;
    private FollowerList followerList;
    private JFX1UserProfileGuiController jfx1UserProfileGuiController;
    private JFX1CreatePartyGUIController jfx1CreatePartyGUIController;
    private JFX1UserItemGUIController jfx1UserItemGUIController;
    private JFX2UserProfileGUIController userProfileGUIController;
    private File loadedImg;

    //Setter e getter
    public File getLoadedImg() {
        return loadedImg;
    }

    public void setLoadedImg(File loadedImg) {
        this.loadedImg = loadedImg;
    }

    public void setUserItemGUIController(JFX1UserItemGUIController jfx1UserItemGUIController) {
        this.jfx1UserItemGUIController = jfx1UserItemGUIController;
    }

    public JFX1UserItemGUIController getUserItemGUIController() {
        return jfx1UserItemGUIController;
    }

    public JFX1UserProfileGuiController getUserProfileGuiController() {
        return jfx1UserProfileGuiController;
    }

    public void setUserProfileGuiController(JFX1UserProfileGuiController jfx1UserProfileGuiController) {
        this.jfx1UserProfileGuiController = jfx1UserProfileGuiController;
    }

    public JFX1CreatePartyGUIController getCreatePartyGUIController() {
        return jfx1CreatePartyGUIController;
    }

    public void setCreatePartyGUIController(JFX1CreatePartyGUIController jfx1CreatePartyGUIController) {
        this.jfx1CreatePartyGUIController = jfx1CreatePartyGUIController;
    }
    public FollowingList getFollowingList() {
        return followingList;
    }

    public void setFollowingList(FollowingList followingList) {
        this.followingList = followingList;
    }

    public FollowerList getFollowerList() {
        return followerList;
    }

    public void setFollowerList(FollowerList followerList) {
        this.followerList = followerList;
    }

    public UserModel getUserModel() {
        return userModel;
    }
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setWhoIam() {
        int type= ThreadLocalSession.getUserSession().get().getType();
        if(type==1){
            this.whoIam= ThreadLocalSession.getUserSession().get().getClubModel();
            this.whoIam.getInfoLogged().setType(1);
        }
        else if(type==0){
            this.whoIam= ThreadLocalSession.getUserSession().get().getUserModel();
            this.whoIam.getInfoLogged().setType(0);
        }
    }

    public UserClubModel getWhoIam() {
        return whoIam;
    }

    public void setJFX2UserProfileGUIController(JFX2UserProfileGUIController userProfileGUIController) {
        this.userProfileGUIController = userProfileGUIController;
    }

    public JFX2UserProfileGUIController getJFX2UserProfileGUIController() {
        return userProfileGUIController;
    }

    public UserProfileApplicationLayer(JFX1UserProfileGuiController jfx1UserProfileGuiController, UserModel userModel){
        setUserProfileGuiController(jfx1UserProfileGuiController);
        setUserModel(userModel);
        setWhoIam();
    }

    public UserProfileApplicationLayer(JFX1CreatePartyGUIController jfx1CreatePartyGUIController, UserModel userModel){
        setCreatePartyGUIController(jfx1CreatePartyGUIController);
        setUserModel(userModel);
    }
    public UserProfileApplicationLayer(JFX1UserItemGUIController jfx1UserItemGUIController, UserModel userModel){
        setUserModel(userModel);
        setUserItemGUIController(jfx1UserItemGUIController);
    }

    public UserProfileApplicationLayer(UserModel userModel) {
        setUserModel(userModel);
    }

    public UserProfileApplicationLayer(JFX2UserProfileGUIController userProfileGUIController, UserModel userModel) {
        setUserModel(userModel); //this is the visited user model
        setJFX2UserProfileGUIController(userProfileGUIController);
        setWhoIam();
    }

    public int chooseDecoration(){
        if(( ThreadLocalSession.getUserSession().get().getUserModel() != null)){
            if((getUserModel().getUsername()).equals( ThreadLocalSession.getUserSession().get().getUserModel().getUsername())){
                return 0;
            }
            else if(getUserModel().getType()==0){
                return 1;
            }
        }
        return 2;
    }

    public void navigationFollowerPage(ActionEvent ae) {
        ReplaceSceneAndInitializePage risp=new ReplaceSceneAndInitializePage();
        risp.replaceSceneAndInitializePage(ae,"/JFX1/JFX1FollowerFollowingListPage.fxml",0,this.userModel);
    }

    public void navigationFollowingPage(ActionEvent ae) {
            ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(ae,"/JFX1/JFX1FollowerFollowingListPage.fxml", 1, this.userModel);
    }
    public void navigationUserProfile(ActionEvent actionEvent,String fxml){
        if(fxml.startsWith("/JFX1")) {
            ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
            replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent, fxml, getUserModel());
        }else if(fxml.startsWith("/JFX2")){
            JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(actionEvent, fxml, getUserModel());}
    }
    public void navigationUserProfile(Stage stage){
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage("/JFX2/JFX2UserProfile.fxml", getUserModel(),stage);
    }
    public boolean doVisitingFollowVisited(){
        return FollowUtils.doAFollowB((UserModel)getWhoIam(), getUserModel());
    }
    public void getJoinedVisitedUserList(){
        JoinedListDAO.getJoined(getUserModel(), getJFX2UserProfileGUIController());
    }
    public void followVisitedUser(){
        FollowUserController.follow(getUserModel(), (UserModel) getWhoIam(), getFollowerList());
    }
    public void unfollowVisitedUser(){
        FollowUserController.unfollow(getUserModel(), (UserModel) getWhoIam(),getFollowerList());
    }

    public void goToLogin(ActionEvent ae) {
        JFX2ReplaceSceneAndInitializePage jfx2ReplaceSceneAndInitializePage = new JFX2ReplaceSceneAndInitializePage();
        jfx2ReplaceSceneAndInitializePage.replaceSceneAndInitializePage(ae, "/JFX2/JFX2Welcome.fxml");
    }
}
