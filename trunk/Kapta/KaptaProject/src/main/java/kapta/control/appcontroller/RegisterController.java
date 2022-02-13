package kapta.control.appcontroller;

import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanin.GenericUserBean;
import kapta.utils.bean.beanin.jfx1.JFX1ProfileBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.UserDao;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.UsernameConflictException;
import kapta.utils.utils.Authentication;

public class RegisterController {
    /*
    Controlli semantici
     */

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
            UserModel userModel = new UserModel(genericUserBean.getUsername(), genericUserBean.getPassword(), genericUserBean.getEmail(), genericUserBean.getName(), genericUserBean.getSecondName(),genericUserBean.getGender(),0, 0, 0,genericUserBean.getImg());
            UserDao.saveUser(userModel);

        }
        else if (genericUserBean.getType()==1) {
            ClubModel clubModel = new ClubModel(genericUserBean.getUsername(), genericUserBean.getPassword(), genericUserBean.getEmail(), genericUserBean.getName(), genericUserBean.getCity(), genericUserBean.getWebsite(), genericUserBean.getAddress(), 0, 1, genericUserBean.getImg());
            ClubDao.saveClub(clubModel);
        }
    }
}
