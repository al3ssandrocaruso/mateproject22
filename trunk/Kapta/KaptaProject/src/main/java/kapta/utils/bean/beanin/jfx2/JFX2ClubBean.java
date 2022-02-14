package kapta.utils.bean.beanin.jfx2;

import kapta.utils.bean.beanin.GenericUserBean;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.utils.EmailValidator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class JFX2ClubBean extends GenericUserBean {
    public JFX2ClubBean(String username, String email, String password, String clubName, String address, String city, String website) throws MalformedURLException, URISyntaxException, InputNullException, EmailValidatorException {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setClubName(clubName);
        setAddress(address);
        setCity(city);
        setWebsite(website);
        setType();
    }

    //set
    public void setUsername(String username) { this.username=username;}
    public void setEmail(String email) throws InputNullException, EmailValidatorException {
        if(email.isEmpty()) {
            Trigger.emptyField("email");
        }
        boolean correctFormat = EmailValidator.validate(email);
        if(correctFormat){this.email = email;}
        else{
            throw new EmailValidatorException(email);
        }
    }
    public void setPassword(String password) {this.password=password;}
    public void setImage(File img){this.img=img;}
    public void setClubName(String name) {this.name = name;}
    public void setAddress(String address){this.address = address;}
    public void setCity(String city){this.city=city;}
    public void setWebsite(String website) throws URISyntaxException, MalformedURLException {
        URI support = new URI("https://"+website);
        URL url = support.toURL();
        this.website= url;
    }
    public void setType(){this.type=1;}
}
