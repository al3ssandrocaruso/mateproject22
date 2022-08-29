package kapta.control.guicontroller.interfaceone.request.requestitem.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.utils.bean.jfx1.JFX1RequestBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.ReplaceSceneAndInitializePage;


public class JFX1AcceptedRequestItemGUIController {
    @FXML
    private Label labelEventName;
    @FXML
    private Label labelEventDate;
    @FXML
    private ImageView eventImageView;
    private JFX1RequestBean requestBean;

    public void setRequestBean(JFX1RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public JFX1RequestBean getRequestBean() {
        return requestBean;
    }

    public void setEventImageView(Image eventImageView) {
        this.eventImageView.setImage(eventImageView);
    }
    public void setLabelEventDate(String labelEventDate) {
        this.labelEventDate.setText(labelEventDate);
    }
    public void setLabelEventName(String labelEventName) {
        this.labelEventName.setText(labelEventName);
    }

    public void goToEventPage(ActionEvent actionEvent) {
        ReplaceSceneAndInitializePage rp = new ReplaceSceneAndInitializePage();
        rp.replaceSceneAndInitializePage(actionEvent, "/JFX1/JFX1EventPage.fxml", getRequestBean().getEventBean());
    }

    public void setAll(JFX1RequestBean jfx1RequestBean ){
        setRequestBean(jfx1RequestBean);
        setLabelEventName(jfx1RequestBean.getEventNameOut());
        setLabelEventDate(jfx1RequestBean.getEventDateOut());
        try {
            setEventImageView(jfx1RequestBean.getEventImageOut());
        } catch (SystemException e) {
            ///
        }
    }

}
