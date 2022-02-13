package kapta.control.appcontroller;

import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanin.LoginBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.UserDao;
import kapta.utils.exception.myexception.WrongPasswordException;
import kapta.utils.exception.Trigger;
import kapta.utils.session.Session;
import kapta.utils.utils.Authentication;

import static kapta.utils.session.ThreadLocalSession.userSession;
import static kapta.utils.session.ThreadLocalSession.usernameSession;

public class LoginController {

    private LoginController(){
        //ignored
    }

    public static UserClubModel login(LoginBean loginBean) throws WrongPasswordException{
        ClubModel clubModel = null;
        if (loginBean.getType() == 1) {
            // login MANAGER
            if (Authentication.checkIsRegistered(1, loginBean.getPassword(), loginBean.getUsername()) == 1) {
                clubModel = ClubDao.getClubByUserName(loginBean.getUsername());
                userSession.set(new Session(clubModel, 1));
                userSession.get().setClubModel(clubModel); //qua email è giusta
                //userSession è settata bene
                userSession.get().getClubModel().setId(ClubDao.clubIdbyClub(clubModel));
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
                usernameSession = loginBean.getUsername();
                userModel = UserDao.getUserByUsername(loginBean.getUsername());
                userSession.set(new Session(userModel, 0));
                userSession.get().setUserModel(userModel);
                userSession.get().getUserModel().setNumFollowing(UserDao.getNumSeguiti(userModel));
                userSession.get().getUserModel().setNumFollower(UserDao.getNumFollower(userModel));
                userSession.get().getUserModel().setId(UserDao.getUserByUsername(userModel.getUsername()).getId());
            }
            else{
                Trigger.throwWrongPassword();
            }
            return userModel;
        }

    }

}
