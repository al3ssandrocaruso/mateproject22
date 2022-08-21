package kapta.utils.bean.J1;

import kapta.utils.utils.EmailValidator;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import java.io.File;

//Bean di supporto per il super register della prima interfaccia
public class JFX1ProfileBean {
    private String username;
    private String password;
    private String email;
    private File image;
    private int type;

    public JFX1ProfileBean(String username, String password, String email, File profileImage , int type) throws EmailValidatorException, InputNullException {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setProfileImg(profileImage);
        setType(type);
    }


    public void setEmail(String email) throws EmailValidatorException, InputNullException {
        if(email.isEmpty()) {
            Trigger.emptyField("email");
        }
        boolean correctFormat = EmailValidator.validate(email);
        if(correctFormat){this.email = email;}
        else{
            throw new EmailValidatorException(email);
        }
    }
    public void setPassword(String password) throws InputNullException {
        if(password.isEmpty()) {
            Trigger.emptyField("password");
        }
        this.password = password;
    }
    public void setUsername(String username) throws InputNullException {
        if(username.isEmpty()) {
            Trigger.emptyField("username");
        }

        this.username = username;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setProfileImg(File profileImg) throws InputNullException {
        if(profileImg == null) {
            Trigger.emptyField("Image");
        }

        this.image = profileImg;
    }
    public String getEmail() {return email;}
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public int getType() {
        return type;
    }
    public File getImage(){return image;}

}
