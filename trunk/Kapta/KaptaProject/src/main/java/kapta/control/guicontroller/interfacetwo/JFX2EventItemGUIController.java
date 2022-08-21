package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import kapta.utils.bean.J2.JFX2ClubBean;
import kapta.utils.bean.J2.JFX2EventBean;
import kapta.utils.bean.J2.JFX2UserBean;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.session.ThreadLocalSession;


public class JFX2EventItemGUIController {
    @FXML
    private ImageView imageViewEventImage;
    @FXML
    private Label labelEventName;
    @FXML
    private Label labelEventData;
    @FXML
    private Label labelEventPrice;


    private JFX2EventBean eventBean;
    private JFX2ClubBean whoIamClub;
    private JFX2UserBean whoIamUser;
    private int type;



    public void setLabelEventName(String eventName) {
        this.labelEventName.setText(eventName);
    }
    public void setImageViewEventImage(Image imageViewEventImage) {
        this.imageViewEventImage.setImage(imageViewEventImage);
    }
    public void setLabelEventData(String eventDate) {
        this.labelEventData.setText(eventDate);
    }
    public void setLabelEventPrice(String eventPrice) {
        this.labelEventPrice.setText(eventPrice+"$");
    }

    public void gotoEventInfo(ActionEvent ae) {
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, "/JFX2/JFX2PartyEventPage.fxml", this.eventBean);
    }

    public void setAll(JFX2EventBean jfx2EventBean){
        setWhoIam();
        setEventBean(jfx2EventBean);
        setImageViewEventImage(jfx2EventBean.getEventImgOut());
        setLabelEventData(jfx2EventBean.getEventDateOut());
        setLabelEventPrice(jfx2EventBean.getEventPriceOut());
        setLabelEventName(jfx2EventBean.getEventNameOut());

    }

    private  void setEventBean(JFX2EventBean eventBean) {
        this.eventBean = eventBean;
    }

    private void setWhoIam() {
        int type= ThreadLocalSession.getUserSession().get().getType();
        this.type= type;
        if(type==1){
            this.whoIamClub= (JFX2ClubBean) ThreadLocalSession.getUserSession().get().getClubBean();
        }
        else if(type==0){this.whoIamUser= new JFX2UserBean (ThreadLocalSession.getUserSession().get().getUserBean());}
    }


}
