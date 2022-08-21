package kapta.control.guicontroller.interfaceone.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.utils.bean.J1.JFX1PartyBean;
import kapta.utils.init.ReplaceSceneAndInitializePage;

public class JFX1PartyItemGUIController {


    @FXML
    private ImageView imageViewPartyImage;

    @FXML
    private Label labelPartyName;

    @FXML
    private Label labelPartyDate;


    private JFX1PartyBean partyBean;


    public void gotoPartyPage(ActionEvent ae) {
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(ae, "/JFX1/JFX1PartyPage.fxml", this.partyBean);
    }
    private void setImageViewPartyImage(Image image) {
        this.imageViewPartyImage.setImage(image);
    }

    private void setLabelPartyDate(String partyDate) {
        this.labelPartyDate.setText(partyDate);
    }


    private void setLabelPartyName(String partyName) {
        this.labelPartyName.setText(partyName);
    }

    public void setAll(JFX1PartyBean jfx1PartyBean){
        this.partyBean =jfx1PartyBean;
        this.setLabelPartyName(jfx1PartyBean.getPartyNameOut());
        this.setLabelPartyDate(jfx1PartyBean.getPartyDateOut());
        this.setImageViewPartyImage(jfx1PartyBean.getPartyImgOut());

    }


}
