package test.junit;

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
    //FAILED

    /*
    @Test
    public void testFollow() throws  WrongPasswordException {
        LoginBean loginBean1 = new JFX2LoginBean("a", "a", 0);
        UserClubModel a = LoginController.login(loginBean1);

        LoginBean loginBean2 = new JFX1LoginBean("c", "c", 0);
        UserClubModel c = LoginController.login(loginBean2);

        int ret=0;
        if(FollowUtils.doAFollowB((UserModel) a, (UserModel) c)){
            ret = 1;
        }

        assertEquals(1, ret, 0);    //SUCCESS (a meno che a non smetti di seguire a)

        ret = 0;
        if(FollowUtils.doAFollowB((UserModel) c, (UserModel) a)){
            ret = 1;
        }

        assertEquals(1, ret, 0);    //FAILED (a meno che c non inizi a seguire a)
    }

     */
}

