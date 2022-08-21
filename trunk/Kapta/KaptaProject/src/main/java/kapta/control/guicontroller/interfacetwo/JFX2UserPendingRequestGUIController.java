package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import kapta.model.EventModel;
import kapta.utils.bean.J2.JFX2RequestBean;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;

public class JFX2UserPendingRequestGUIController {
    @FXML
    private ImageView eventPic;

    @FXML
    private Label labelEventName;

    @FXML
    private Label labelEventDate;

    EventModel eventModel;
    JFX2RequestBean jfx2RequestBean;


    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void infoPendingEvent(ActionEvent actionEvent) {
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(actionEvent, "/JFX2/JFX2PartyEventPage.fxml", getEventModel());
    }
    public void setAll(JFX2RequestBean jfx2RequestBean){
        this.jfx2RequestBean=jfx2RequestBean;
        eventPic.setImage(jfx2RequestBean.getRelatedEventImgOut());
        this.labelEventName.setText( jfx2RequestBean.getRelatedEventOut());
        this.labelEventDate.setText(jfx2RequestBean.getDateOut());
    }

    public void cancelRequest(ActionEvent actionEvent) {
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(actionEvent, "/JFX2/JFX2UserRequestPage.fxml");
    }
}
