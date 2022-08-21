package kapta.utils.bean.jfx2;

import javafx.scene.image.Image;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.UserBean;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.utils.EmailValidator;
import kapta.utils.utils.ImageConverter;

import java.io.File;

public class JFX2UserBean extends UserBean {
    public JFX2UserBean(UserModel userModel){
        super (userModel);
    }

    public JFX2UserBean(String username, String email, String password, String name, String secondName, String gender) throws InputNullException, EmailValidatorException {
        setUsernameOut(username);
        setEmailOut(email);
        setPasswordOut(password);
        setNameOut(name);
        setSecondNameOut(secondName);
        setGenderOut(gender);
        setTypeOut();
    }

    public JFX2UserBean(String username, String email, String name, String secondName, int id ) throws InputNullException, EmailValidatorException {
        setUsernameOut(username);
        setNameOut(name);
        setSecondNameOut(secondName);
        setEmailOut(email);
        setTypeOut();
        setIdOut(id);
    }

    public JFX2UserBean(UserBean userBean){
        this.setSecondName(userBean.getSecondName());
        this.setUsername(userBean.getUsername());
        this.setGender(userBean.getGender());
        this.setPassword(userBean.getPassword());
        this.setEmail(userBean.getEmail());
        this.setId(userBean.getId());
        this.setType(userBean.getType());
        this.setImg((userBean.getImg()));

    }

    public void setUsernameOut(String username) { this.username=username+"";}
    public void setEmailOut(String email) throws InputNullException, EmailValidatorException {
        if(email.isEmpty()) {
            Trigger.emptyField("email.");
        }
        boolean correctFormat = EmailValidator.validate(email);
        if(correctFormat){this.email = email;}
        else{
            throw new EmailValidatorException(email);
        }
    }
    public void setTypeOut(){this.type=0;}
    public void setPasswordOut(String password) {this.password=password;}
    public void setImageOut(File img){this.img=img;}
    public void setNameOut(String name) {this.name = name;}
    public void setSecondNameOut(String secondName) {this.secondName=secondName;}
    public void setGenderOut(String gender){this.gender=gender;}
    public void setIdOut(int id){this.id = id; }


    public String getEmailOut() {
        return email;
    }
    public String getNameOut() {
        return name;
    }
    public String getSurnameOut() {
        return secondName;
    }
    public String getUsernameOut() {
        return username;
    }
    public Image getImageOut() {
        return ImageConverter.convertFileToFxImage(img);
    }

}
