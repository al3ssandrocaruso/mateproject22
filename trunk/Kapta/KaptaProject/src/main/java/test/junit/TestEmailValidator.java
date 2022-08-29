package test.junit;

import kapta.utils.utils.EmailValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/

public class TestEmailValidator {

    /**
     * Si vuole testare l'efficacia della funzione "validate" che nel nostro progetto controlla se la mail
     * inserita da uno user sia sintatticamente corretta
     */
    //Success
    @Test
    public void testValidator(){
        //Non metto apposta il formato giusto della mail per vedere se fallisce il test
        String email1 = "testmail";
        String email2 = "test@gmail.com";

        int case1 = 0;
        int case2 = 0;


        if(EmailValidator.validate(email2)){
            case2 = 1;
        }

        assertEquals(1, case2, 0); //Success


        if(EmailValidator.validate(email1)){
            case1 = 1;
        }

        assertEquals(0, case1, 0); //Fail




    }
}
