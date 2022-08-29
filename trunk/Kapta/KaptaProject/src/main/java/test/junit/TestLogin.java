package test.junit;

import kapta.control.appcontroller.LoginController;
import kapta.model.profiles.UserClubModel;
import kapta.utils.bean.LoginBean;
import kapta.utils.bean.jfx1.JFX1LoginBean;
import kapta.utils.bean.jfx2.JFX2LoginBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.exception.myexception.WrongPasswordException;
import org.junit.Test;


import static org.junit.Assert.assertEquals;


/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/

//Nei test ci logghiamo come utenti "a" e "b" => potrebbe non andare se tali utenti vengono cancellati dal db
public class TestLogin {

    /**
     *Si effettuano test sul login di utenti passanso username, password e tipo di utente (0=>user, 1=>club manager)
     */
    //SUCCESS
    @Test
    public void testLogin() throws WrongPasswordException, SystemException {

        LoginBean loginBean1 = new JFX1LoginBean("nathan", "nat", 0);
        LoginBean loginBean2 = new JFX2LoginBean("andrea", "andrea", 1);

        UserClubModel userModel = LoginController.login(loginBean1);
        UserClubModel clubModel = LoginController.login(loginBean2);

        int ret = 0;

        if(userModel != null){
            ret = 1;
        }

        assertEquals(1, ret, 0);

        ret = 0;

        if(clubModel != null){
            ret = 1;
        }

        assertEquals(1, ret, 0);
    }
}
