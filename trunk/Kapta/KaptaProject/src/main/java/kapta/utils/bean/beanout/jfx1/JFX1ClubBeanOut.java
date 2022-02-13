package kapta.utils.bean.beanout.jfx1;

import javafx.scene.image.Image;
import kapta.model.lists.CreatedEventList;
import kapta.model.profiles.ClubModel;
import kapta.utils.utils.ImageConverter;

import java.io.File;

public class JFX1ClubBeanOut {

    private String username;
    private String email;
    private String numFollower;
    private String clubAddress;
    private String website;
    private String city;
    private String numCreatedEvents;
    private Image image;


    public JFX1ClubBeanOut(ClubModel clubModel, CreatedEventList createdEventList) {
        this.setUsername(clubModel.getUsername());
        this.setEmail(clubModel.getEmail());
        this.setNumFollower(clubModel.getNumFollower());
        this.setClubAddress(clubModel.getAddress());
        this.setWebsite(clubModel.getWebsite().toString());
        this.setCity(clubModel.getCity());
        this.setNumCreatedEvents(createdEventList.getSize().toString());
        this.setImage(clubModel.getProfileImg());
    }

    public Image getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = ImageConverter.convertFileToFxImage(image);
    }

    public String getNumCreatedEvents() {
        return numCreatedEvents;
    }

    public void setNumCreatedEvents(String numCreatedEvents) {
        this.numCreatedEvents = numCreatedEvents;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNumFollower() {
        return numFollower;
    }

    public void setNumFollower(int numFollower) {
        this.numFollower = String.valueOf(numFollower);
    }

    public String getClubAddress() {
        return clubAddress;
    }

    public void setClubAddress(String clubAddress) {
        this.clubAddress = clubAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
