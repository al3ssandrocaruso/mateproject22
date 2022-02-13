package kapta.utils.bean.beanin.jfx1;

import kapta.utils.bean.beanin.GenericUserBean;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class JFX1ClubBean extends GenericUserBean {

    public JFX1ClubBean(String username, String email, String password, File img, String clubName, String address, String city, String website) throws MalformedURLException, URISyntaxException {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setImage(img);
        setClubName(clubName);
        setAddress(address);
        setCity(city);
        setWebsite(website);
        setType();
    }

    //set
    public void setUsername(String username) { this.username=username;}
    public void setEmail(String email) {this.email = email;}
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
