package kapta.utils.bean.jfx1;

import javafx.scene.image.Image;
import kapta.model.lists.CreatedEventList;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.ClubBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.exception.myexception.WrongURLException;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class JFX1ClubBean extends ClubBean {

    public JFX1ClubBean(String username, String email, String password, String clubName, String address, String city, String website) throws WrongURLException {
        setBasic(username,email,city,address);
        setPasswordOut(password);
        setClubNameOut(clubName);
        setWebsiteOut(website);
    }

    public JFX1ClubBean(String username, String email, String city, String address, int id)  {
        setBasic(username,email,city,address);
        setId(id);
    }


    private void setBasic(String username, String email, String city , String address){
        setUsernameOut(username);
        setEmailOut(email);
        setAddressOut(address);
        setCityOut(city);
        setTypeOut();
    }



    public  JFX1ClubBean(ClubBean clubBean){
        super(clubBean);
    }



    public JFX1ClubBean(ClubModel clubModel){
        super(clubModel);
    }
    public JFX1ClubBean(ClubModel clubModel, CreatedEventList cr){
        super(clubModel, cr);
    }

    //set
    public void setUsernameOut(String username) { this.username=username+"";}
    public void setEmailOut(String email) {this.email = email;}
    public void setPasswordOut(String password) {this.password=password;}
    public void setImageOut(File img){this.img=img;}
    public void setClubNameOut(String name) {this.name = name+"";}
    public void setAddressOut(String address){this.address = address+"";}
    public void setCityOut(String city){this.city=city+"";}
    public void setWebsiteOut(String website) throws WrongURLException {
        try{
        URI support = new URI("https://"+website+"");
        URL url = support.toURL();
        this.website= url;}
        catch (URISyntaxException |MalformedURLException e ){
throw new WrongURLException("https://"+website+"");
        }
    }
    public void setTypeOut(){this.type=1;}


    public Image getImageOut() throws SystemException {
            return ImageConverter.convertFileToFxImage(img);
    }
    public String getUsernameOut() {
        return username;
    }
    public String getEmailOut() {
        return email;
    }
    public String getClubAddressOut() {
        return address;
    }
    public String getWebsiteOut() {
        return website+"";
    }
    public String getCityOut() {
        return city;
    }

    public String getNumCreatedEventsOut(){
        return String.valueOf(numCreatedEvents);
    }





}
