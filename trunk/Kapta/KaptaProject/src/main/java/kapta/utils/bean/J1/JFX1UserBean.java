package kapta.utils.bean.J1;

import javafx.scene.image.Image;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.UserBean;
import kapta.utils.utils.ImageConverter;

import java.io.File;

public class JFX1UserBean extends UserBean {


    // from the view
    public JFX1UserBean(String username, String email, String password, File img, String name, String secondName, int gender){
        setUsernameOut(username);
        setEmailOut(email);
        setPassword(password);
        setImageOut(img);
        setNameOut(name);
        setSecondNameOut(secondName);
        setGenderOut(gender);
        setTypeOut();
    }
    public JFX1UserBean(String username, String email,  String name, String secondName, int id){
        setUsernameOut(username);
        setEmailOut(email);
        setNameOut(name);
        setSecondNameOut(secondName);
        setId(id);
        setTypeOut();
    }

    public JFX1UserBean(UserBean userBean){
        this.setSecondName(userBean.getSecondName());
        this.setUsername(userBean.getUsername());
        this.setGender(userBean.getGender());
        this.setPassword(userBean.getPassword());
        this.setEmail(userBean.getEmail());
        this.setId(userBean.getId());
        this.setType(userBean.getType());
        this.setImg((userBean.getImg()));
    }

    public JFX1UserBean(UserModel userModel){
        super(userModel);
    }

    //set
    public void setUsernameOut(String username) { this.username=username;}
    public void setEmailOut(String email) {this.email = email;}
    public void setPasswordOut(String password) {this.password=password;}
    public void setImageOut(File img){this.img=img;}
    public void setNameOut(String name) {this.name = name;}
    public void setSecondNameOut(String secondName) {this.secondName=secondName;}
    public void setGenderOut(int gender) {
        switch(gender){
            case 0:{
                this.gender = "Male";
                break;
            }
            case 1:{
                this.gender = "Female";
                break;
            }
            case 2: {
                this.gender = "Other";
                break;
            }
            default:
                break;
        }
    }
    public void setTypeOut(){this.type=0;}
    public void setIdOut(int id){
        this.id = id;
    }



    public String getTypeOut() {
        return String.valueOf(type);
    }
    public String getNumFollowingOut() {
        return String.valueOf(numFollowing);
    }
    public String getNumFollowerOut() {
        return String.valueOf(numFollower);
    }
    public String getIdOut() {
        return String.valueOf(id);
    }
    public String getGenderOut() {
        return gender;
    }


    public String getEmailOut() {
        return email;
    }
    public String getUsernameOut() {
        return username;
    }
    public String getSecondNameOut() {
        return secondName;
    }
    public String getNameOut() {
        return name;
    }
    public Image getProfileImgOut(){
        return ImageConverter.convertFileToFxImage(img);
    }
}

