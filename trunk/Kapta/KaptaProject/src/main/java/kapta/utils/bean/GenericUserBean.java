package kapta.utils.bean;

import kapta.model.lists.CreatedEventList;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import java.io.File;
import java.net.URL;


public abstract class GenericUserBean {
    //Attributi comuni
    protected String username;
    protected String name;  //per l'utente è il nome normale mentre per il club è il clubname
    protected String password;
    protected String email;
    protected int type;
    protected File img;
    protected int id;

    //Attributi User
    protected String secondName;
    protected String gender;
    protected int numFollower;
    protected int numFollowing;


    //Attributi ClubManager
    protected String address;
    protected URL website;
    protected String city;
    protected  int numCreatedEvents;


    public GenericUserBean(){

    }

    protected GenericUserBean (UserModel userModel){
        this.setSecondName(userModel.getSecondName());
        this.setUsername(userModel.getUsername());
        this.setGender(userModel.getGender());
        this.setPassword(userModel.getPassword());
        this.setEmail(userModel.getEmail());
        this.setId(userModel.getId());
        this.setNumFollower(userModel.getNumFollower());
        this.setNumFollowing(userModel.getNumFollowing());
        this.setType(userModel.getType());
        this.setImg((userModel.getProfileImg()));
    }

    protected GenericUserBean(ClubModel clubModel){
        setEmail(clubModel.getEmail());
        setUsername(clubModel.getUsername());
        setName(clubModel.getClubName());
        setPassword(clubModel.getPassword());
        setType(clubModel.getType());
        setImg(clubModel.getProfileImg());
        setId(clubModel.getId());
        setAddress(clubModel.getAddress());
        setWebsite(clubModel.getWebsite());
        setCity(clubModel.getCity());


    }
    protected GenericUserBean(ClubModel clubModel, CreatedEventList cr){

        setEmail(clubModel.getEmail());
        setUsername(clubModel.getUsername());
        setName(clubModel.getClubName());
        setPassword(clubModel.getPassword());
        setType(clubModel.getType());
        setImg(clubModel.getProfileImg());

        setAddress(clubModel.getAddress());
        setWebsite(clubModel.getWebsite());
        setCity(clubModel.getCity());

        setNumCreatedEvents(cr.getSize());


    }

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

    public int getId() {
        return id;
    }

    public void setUsernameOut(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setImg(File img) {
        this.img = img;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setWebsite(URL website) {
        this.website = website;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNumCreatedEvents(int numCreatedEvents) {
        this.numCreatedEvents = numCreatedEvents;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumFollower(int numFollower) {
        this.numFollower = numFollower;
    }

    public void setNumFollowing(int numFollowing) {
        this.numFollowing = numFollowing;
    }
}
