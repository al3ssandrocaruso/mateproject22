package kapta.utils.bean.beanin.jfx2;

import kapta.utils.bean.beanin.GenericUserBean;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.utils.EmailValidator;

import java.io.File;

public class JFX2UserBean extends GenericUserBean {

    public JFX2UserBean(String username, String email, String password, String name, String secondName, String gender) throws InputNullException, EmailValidatorException {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setName(name);
        setSecondName(secondName);
        setGender(gender);
        setType();
    }

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
    public void setName(String name) {this.name = name;}
    public void setSecondName(String secondName) {this.secondName=secondName;}
    public void setGender(String gender){this.gender=gender;}
    public void setType(){this.type=0;}
}
