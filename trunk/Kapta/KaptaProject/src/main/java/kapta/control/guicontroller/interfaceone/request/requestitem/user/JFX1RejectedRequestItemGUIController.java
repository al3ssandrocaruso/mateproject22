package kapta.control.guicontroller.interfaceone.request.requestitem.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.utils.bean.jfx1.JFX1RequestBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.ReplaceSceneAndInitializePage;

public class JFX1RejectedRequestItemGUIController {
    @FXML
    private Label eventName;
    @FXML
    private Label eventDate;
    @FXML
    private ImageView imageView;

    private JFX1RequestBean requestBean;

    public void setRequestBean(JFX1RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public JFX1RequestBean getRequestBean() {
        return requestBean;
    }



    public void setEventImageView(Image eventImageView) {
        this.imageView.setImage(eventImageView);
    }

    public void setEventDate(String eventDate) {
        this.eventDate.setText(eventDate);
    }

    public void setLabelEventName(String labelEventName) {
        this.eventName.setText(labelEventName);
    }

    public void deleteRequest(ActionEvent actionEvent) {
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent, "/JFX1/JFX1UserRequestPage.fxml");
    }
    public void setAll(JFX1RequestBean jfx1RequestBean){

        setRequestBean(jfx1RequestBean);
        setLabelEventName(jfx1RequestBean.getEventNameOut());
        setEventDate(jfx1RequestBean.getEventDateOut());
        try {
            setEventImageView(jfx1RequestBean.getEventImageOut());
        } catch (SystemException e) {
            ///
        }
    }
}
