package kapta.control.appcontroller;

import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.ClubBean;
import kapta.utils.bean.LoginBean;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.UserDao;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.exception.myexception.WrongPasswordException;
import kapta.utils.exception.Trigger;
import kapta.utils.mysession.Session;
import kapta.utils.mysession.ThreadLocalSession;
import kapta.utils.utils.Authentication;

public class LoginController {

    private LoginController(){
        //ignored
    }

    public static UserClubModel login(LoginBean loginBean) throws WrongPasswordException, SystemException {
        ClubModel clubModel = null;
        if (loginBean.getType() == 1) {
            // login MANAGER
            if (Authentication.checkIsRegistered(1, loginBean.getPassword(), loginBean.getUsername()) == 1) {
                clubModel = ClubDao.getClubByUserName(loginBean.getUsername());
                ClubBean clubBean = new ClubBean(clubModel);
                ThreadLocalSession.getUserSession().set(new Session(clubBean));
                ThreadLocalSession.getUserSession().get().getClubBean().setId(ClubDao.clubIdbyClub(clubModel));
            }
            else{
                Trigger.throwWrongPassword();
            }
            return clubModel;
        }
        else{
            // login USER
            UserModel userModel = null;
            if (Authentication.checkIsRegistered(0, loginBean.getPassword(), loginBean.getUsername()) ==1){
                ThreadLocalSession.setUsername(loginBean.getUsername());
                userModel = UserDao.getUserByUsername(loginBean.getUsername());
                UserBean userBean = new UserBean(userModel);
                ThreadLocalSession.getUserSession().set(new Session(userBean));
            }

            else{
                Trigger.throwWrongPassword();
            }
            return userModel;
        }
    }
}
