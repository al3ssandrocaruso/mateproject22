package kapta.utils.bean.beanout.jfx2;

import javafx.scene.image.Image;
import kapta.model.profiles.UserModel;
import kapta.utils.utils.ImageConverter;


import java.io.File;

public class JFX2UserBeanOut {
    private String username;
    private Image image;
    private String email;
    private String name;
    private String surname;

    public JFX2UserBeanOut(UserModel userModel) {
        setUsername(userModel.getUsername());
        setImage(userModel.getProfileImg());
        setEmail(userModel.getEmail());
        setName(userModel.getName());
        setSurname(userModel.getSecondName());

    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setImage(File image) {
        this.image = ImageConverter.convertFileToFxImage(image);
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getUsername() {
        return username;
    }
    public Image getImage() {
        return image;
    }


}
