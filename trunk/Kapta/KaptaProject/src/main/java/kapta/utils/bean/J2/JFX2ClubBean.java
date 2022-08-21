package kapta.utils.bean.J2;

import javafx.scene.image.Image;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.ClubBean;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.utils.EmailValidator;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class JFX2ClubBean extends ClubBean {

    public JFX2ClubBean(ClubModel clubModel){
        super (clubModel);
    }

    public JFX2ClubBean(String username, String email, String password, String clubName, String address, String city, String website) throws MalformedURLException, URISyntaxException, InputNullException, EmailValidatorException {
        setUsernameOut(username);
        setEmailOut(email);
        setPasswordOut(password);
        setClubNameOut(clubName);
        setAddressOut(address);
        setCityOut(city);
        setWebsiteOut(website);
        setTypeOut();
    }

    public  JFX2ClubBean(ClubBean clubBean){
        setEmail(clubBean.getEmail());
        setUsername(clubBean.getUsername());
        setName(clubBean.getName());
        setPassword(clubBean.getPassword());
        setType(1);
        setImg(clubBean.getImg());
        setId(clubBean.getId());
        setAddress(clubBean.getAddress());
        setWebsite(clubBean.getWebsite());
        setCity(clubBean.getCity());
    }



    //set
    public void setUsernameOut(String username) { this.username=username;}
    public void setEmailOut(String email) throws InputNullException, EmailValidatorException {
        if(email.isEmpty()) {
            Trigger.emptyField("email");
        }
        boolean correctFormat = EmailValidator.validate(email);
        if(correctFormat){this.email = email;}
        else{
            throw new EmailValidatorException(email);
        }
    }
    public void setPasswordOut(String password) {this.password=password;}
    public void setImageOut(File img){this.img = img;}
    public void setClubNameOut(String name) {this.name = name;}
    public void setAddressOut(String address){this.address = address;}
    public void setCityOut(String city){this.city=city;}
    public void setWebsiteOut(String website) throws URISyntaxException, MalformedURLException {
        URI support = new URI("https://"+website);
        URL url = support.toURL();
        this.website= url;
    }
    public void setTypeOut(){this.type=1;}


    public Image getImgOut (){
        return ImageConverter.convertFileToFxImage(this.img);
    }

    public String getUsernameOut(){
        return username;
    }
}
