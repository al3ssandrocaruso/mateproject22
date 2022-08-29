package test.junit;

import kapta.control.appcontroller.LoginController;
import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.jfx1.JFX1LoginBean;
import kapta.utils.bean.jfx2.JFX2LoginBean;
import kapta.utils.bean.LoginBean;
import kapta.utils.bean.UserBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.exception.myexception.WrongPasswordException;
import kapta.utils.utils.FollowUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/

//Nei test ci logghiamo come utenti "a" e "c" => potrebbe non andare se tali utenti vengono cancellati dal db
public class TestFollow {

    /**
     *Si vuole testare l'efficacia della funzione follow presente all'interno del nostro progetto
     */
    //Pass


    @Test
    public void testFollow() throws WrongPasswordException, SystemException {
        LoginBean loginBean1 = new JFX2LoginBean("ann", "ann", 0);
        UserClubModel a = LoginController.login(loginBean1);
        UserBean beanA = new UserBean((UserModel) a);


        LoginBean loginBean2 = new JFX1LoginBean("nathan", "nat", 0);
        UserClubModel c = LoginController.login(loginBean2);
        UserBean beanC = new UserBean((UserModel) c);

        int ret=0;
        if(FollowUtils.doAFollowB(beanA, beanC)){
            ret = 1;
        }

        assertEquals(1, ret, 0);    //SUCCESS

        ret = 0;
        if(FollowUtils.doAFollowB(beanA, beanC)){
            ret = 1;
        }

        assertEquals(1, ret, 0);    //SUCCESS
    }


}

