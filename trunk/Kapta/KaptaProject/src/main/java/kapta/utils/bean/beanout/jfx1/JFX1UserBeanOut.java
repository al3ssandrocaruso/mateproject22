package kapta.utils.bean.beanout.jfx1;

import javafx.scene.image.Image;
import kapta.model.profiles.UserModel;
import kapta.utils.utils.ImageConverter;

import java.io.File;

public class JFX1UserBeanOut {
    private String name;
    private String secondName;
    private String username;
    private String gender;
    private String password;
    private String email;
    private String id;
    private String numFollower;
    private String numFollowing;
    private String type;
    private Image profileImg;


    public JFX1UserBeanOut(UserModel userModel){
        this.setName(userModel.getName());
        this.setSecondName(userModel.getSecondName());
        this.setUsername(userModel.getUsername());
        this.setGender(userModel.getGender());
        this.setPassword(userModel.getPassword());
        this.setEmail(userModel.getEmail());
        this.setId(userModel.getId());
        this.setNumFollower(userModel.getNumFollower());
        this.setNumFollowing(userModel.getNumFollowing());
        this.setType(userModel.getType());
        this.setProfileImg((userModel.getProfileImg()));
    }

    public Image getProfileImg(){
        return this.profileImg;
    }
    public void setProfileImg(File img){
        this.profileImg =ImageConverter.convertFileToFxImage(img);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getNumFollower() {
        return numFollower;
    }

    public void setNumFollower(int numFollower) {
        this.numFollower = String.valueOf(numFollower);
    }

    public String getNumFollowing() {
        return numFollowing;
    }

    public void setNumFollowing(int numFollowing) {
        this.numFollowing = String.valueOf(numFollowing);
    }

    public String getType() {
        return type;
    }

    public void setType(int type) {
        this.type = String.valueOf(type);
    }

}
