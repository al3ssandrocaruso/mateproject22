package kapta.utils.bean.beanout.jfx1;

import javafx.scene.image.Image;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.net.URL;

public class JFX1ClubBeanOutSettings {

    private String username;
    private String clubAddress;
    private String city;
    private String password;
    private String email;
    private String website;
    private Image profileImg;
    //PERCHE' ESISTE????????

    public JFX1ClubBeanOutSettings(String username, String clubAddress, String city, String password, String email, URL website, File profileImg){
        setUsername(username);
        setClubAddress(clubAddress);
        setCity(city);
        setPassword(password);
        setEmail(email);
        setWebsite(website);
        setProfileImg(profileImg);
    }


    public void setUsername(String username) {this.username = username;}
    public void setClubAddress(String clubAddress) {this.clubAddress = clubAddress;}
    public void setProfileImg(File profileImg) {
        Image img = ImageConverter.convertFileToFxImage(profileImg);
        this.profileImg = img;
    }
    public void setCity(String city) {this.city = city;}
    public void setPassword(String password) {this.password = password;}
    public void setEmail(String email) {this.email = email;}
    public void setWebsite(URL website) {
        String support = String.valueOf(website);
        this.website = support;
    }

    public String getUsername() {return username;}
    public String getClubAddress() {return clubAddress;}
    public Image getProfileImg() {return profileImg;}
    public String getCity() {return city;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public String getWebsite() {return website;}


}