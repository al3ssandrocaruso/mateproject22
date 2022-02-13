package kapta.utils.bean.beanin.jfx1;

import kapta.utils.bean.beanin.GenericUserBean;

import java.io.File;

public class JFX1UserBean extends GenericUserBean {

    public JFX1UserBean(String username, String email, String password, File img, String name, String secondName, int gender){
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setImage(img);
        setName(name);
        setSecondName(secondName);
        setGender(gender);
        setType();
    }

    //set
    public void setUsername(String username) { this.username=username;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password=password;}
    public void setImage(File img){this.img=img;}
    public void setName(String name) {this.name = name;}
    public void setSecondName(String secondName) {this.secondName=secondName;}
    public void setGender(int gender) {
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
    public void setType(){this.type=0;}
}
