package kapta.control.appcontroller;

import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.InfoLogged;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.bean.jfx1.JFX1ProfileBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.UserDao;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.UsernameConflictException;
import kapta.utils.utils.Authentication;

public class RegisterController {



    private RegisterController(){
        //ignored
    }

    public static void startRegister(JFX1ProfileBean jfx1ProfileBean) throws UsernameConflictException {

        if (!Authentication.checkRegistered(jfx1ProfileBean)) {
            Trigger.usernameAlreadyExist(jfx1ProfileBean.getUsername());
        }
    }

    public static void register(GenericUserBean genericUserBean) {
        if (genericUserBean.getType()==0) {
            InfoLogged infoLogged = new InfoLogged(genericUserBean.getUsername(), genericUserBean.getEmail(),genericUserBean.getPassword(), 0);
            UserModel userModel = new UserModel( infoLogged, genericUserBean.getName(), 0, genericUserBean.getImg());
            userModel.setNumFollower(0);
            userModel.setGender(userModel.getGender()) ;
            userModel.setSecondName(genericUserBean.getSecondName());
            UserDao.saveUser(userModel);


        }
        else if (genericUserBean.getType()==1) {
            InfoLogged infoLogged1 = new InfoLogged(genericUserBean.getUsername(), genericUserBean.getEmail(), genericUserBean.getPassword(), 0);
            ClubModel clubModel = new ClubModel(infoLogged1, genericUserBean.getName(), genericUserBean.getCity(), genericUserBean.getWebsite(), genericUserBean.getAddress(), 0, genericUserBean.getImg());
            ClubDao.saveClub(clubModel);
        }
    }
}
