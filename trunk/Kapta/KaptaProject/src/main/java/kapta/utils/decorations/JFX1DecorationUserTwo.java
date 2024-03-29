package kapta.utils.decorations;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kapta.control.appcontroller.FollowUserController;
import kapta.control.guicontroller.interfaceone.JFX1AlertCreator;
import kapta.control.guicontroller.interfaceone.JFX1UserProfileGuiController;
import kapta.engineering.ManageFollowerFollowingList;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.mysession.ThreadLocalSession;
import kapta.utils.utils.FollowUtils;
import kapta.utils.VisualComponent;


public class JFX1DecorationUserTwo extends Decorator {
    private String toWrite;
    private JFX1UserProfileGuiController upgc;
    Button button = new Button();
    private  JFX1UserBean userBean;


    private JFX1UserBean whoIamUser;



    private String radius = "-fx-background-radius: 28;";
    private String white = "-fx-text-fill: white;";


    public JFX1DecorationUserTwo(VisualComponent component, JFX1UserProfileGuiController jfx1UserProfileGuiController, JFX1UserBean userBean){
        super(component);
        this.whoIamUser = new JFX1UserBean (ThreadLocalSession.getUserSession().get().getUserBean());
        this.userBean = userBean;
        if(FollowUtils.doAFollowB( this.whoIamUser,this.userBean)) {
            button.setStyle("-fx-background-color: #d00000;" + radius + white);
            this.setToWrite("Unfollow");
        }else {
            button.setStyle("-fx-background-color: #54e589;" + radius + white);
            this.setToWrite("Follow");
        }
        this.setUpgc(jfx1UserProfileGuiController);
        this.addUserPanel();
    }

    public void setToWrite(String toWrite) {
        this.toWrite = toWrite;
    }

    protected VBox applyDecorationTwo(VBox input) {
        VBox output = input;
        output.getChildren().clear();
        button.setText(this.toWrite);
        button.setMinHeight(45);
        button.setMinWidth(223);
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        button.setFont(font);



        //PULSANTE PREMUTO
        button.setOnAction((ActionEvent ae) -> {
            if (!FollowUtils.doAFollowB( whoIamUser,this.userBean)) {
                try {
                    FollowUserController.follow(this.userBean, whoIamUser);
                    refreshFollowerList();
                    button.setStyle("-fx-background-color: #54e589;" + radius + white);
                    this.setToWrite("Unfollow");
                    this.addUserPanel();
                }
                catch (SystemException systemException) {
                    JFX1AlertCreator.createAlert(systemException);
                }


            } else {
                try {

                    FollowUserController.unfollow(this.userBean, whoIamUser);
                    refreshFollowerList();
                    button.setStyle("-fx-background-color: #d00000;" + radius + white);
                    this.setToWrite("Follow");
                    this.addUserPanel();
                } catch (SystemException systemException) {
                    JFX1AlertCreator.createAlert(systemException);
                }


            }
        });
        button.setStyle("-fx-background-color: #200f54;" + radius + white);
        output.getChildren().add(button);
        return output;
    }

    public void setUpgc(JFX1UserProfileGuiController upgc) {
        this.upgc = upgc;
    }


    public void refreshFollowerList(){
        try {
            ManageFollowerFollowingList.refreshList(userBean,upgc);
        } catch (SystemException e) {
            //ee
            e.printStackTrace();
        }
    }

    //Serve per non avere code smells questa funzione
    public JFX1UserProfileGuiController getUserProfileGUIController(){return upgc;}

    @Override
    public VBox addUserPanel(){
        VBox preliminaryResult=super.addUserPanel();
        preliminaryResult=this.applyDecorationTwo(preliminaryResult);
        return preliminaryResult;
    }



}
