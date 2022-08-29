package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.utils.bean.jfx2.JFX2PartyBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;


public class JFX2PartyItemGUIController {
    @FXML
    private ImageView imageViewPartyImage;

    @FXML
    private Label labelPartyName;

    @FXML
    private Label labelPartyDate;

    JFX2PartyBean partyBean;

    public void setPartyBean(JFX2PartyBean partyBean) {
        this.partyBean = partyBean;
    }

    public void setImageViewPartyImage(Image imageViewPartyImage) {
        this.imageViewPartyImage.setImage(imageViewPartyImage);
    }

    public void setLabelPartyName(String labelPartyName) {
        this.labelPartyName.setText(labelPartyName);
    }

    public void setLabelPartyDate(String labelPartyDate) {
        this.labelPartyDate.setText(labelPartyDate);
    }

    public void gotoPartyInfo(ActionEvent ae)  {
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, "/JFX2/JFX2PartyEventPage.fxml", this.partyBean);
    }
    public void setAll(JFX2PartyBean jfx2PartyBean){
        setPartyBean(jfx2PartyBean);
        try {
            setImageViewPartyImage(jfx2PartyBean.getPartyImgOut2());
        } catch (SystemException e) {
            //
        }
        setLabelPartyName(jfx2PartyBean.getPartyNameOut2());
        setLabelPartyDate(jfx2PartyBean.getPartyDateOut2());
    }

}
