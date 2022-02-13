package kapta.utils.bean.beanout.jfx2;

import javafx.scene.image.Image;
import kapta.model.profiles.ClubModel;
import kapta.utils.utils.ImageConverter;

import java.io.File;

public class JFX2ClubBeanOut {
    String clubName;
    Image img;


    public JFX2ClubBeanOut(ClubModel clubModel){
        setClubName(clubModel.getClubName());
        setImg(clubModel.getProfileImg());
    }

    public String getClubName() {
        return clubName;
    }
    public Image getImg() {
        return img;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public void setImg(File imageFile) {
        this.img = ImageConverter.convertFileToFxImage(imageFile);
    }

}
