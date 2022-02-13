package kapta.utils.bean.beanin;

import java.io.File;
import java.net.URL;

/*
Questo è il bean che il controller applicato registerUser prende in input e a seconda se gli arriva un
UserBean o un clubBean (ne esistono due per ogni interfaccia) lui registra uno user o un clubModel
 */
public abstract class GenericUserBean {
    //Attributi comuni
    protected String username;
    protected String name;  //per l'utente è il nome normale mentre per il club è il clubname
    protected String password;
    protected String email;
    protected int type;
    protected File img;

    //Attributi User
    protected String secondName;
    protected String gender;

    //Attributi ClubManager
    protected String address;
    protected URL website;
    protected String city;

    //get
    public String getUsername() {return username;}
    public String getName() {return name;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public int getType() {return type;}
    public File getImg() {return img;}
    public String getSecondName() {return secondName;}
    public String getGender() {return gender;}
    public String getAddress() {return address;}
    public URL getWebsite() {return website;}
    public String getCity() {return city;}
}
