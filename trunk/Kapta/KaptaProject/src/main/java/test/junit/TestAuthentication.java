package test.junit;

import kapta.utils.exception.myexception.SystemException;
import kapta.utils.utils.Authentication;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/

//Nei test ci logghiamo come utenti "a" e "b" => potrebbe non andare se tali utenti vengono cancellati dal db
public class TestAuthentication {

    /**
     * Si vuole testare l'effettivita autenticazione di un utente passandogli username, password e tipo effettivo di utente
     * (ovvero se si tratta di uno user oppure di un clubmanager)
     */
    @Test
    public void testAuthentication() throws SystemException {
        int ret = Authentication.checkIsRegistered(0, "nathan", "nat");    //SUCCESS
        int ret1 = Authentication.checkIsRegistered(1, "a", "a");   //FAILED
        int ret2 = Authentication.checkIsRegistered(0, "b", "b");   //FAILED
        int ret3 = Authentication.checkIsRegistered(1, "andrea", "andrea");   //SUCCESS


        assertEquals(1, ret, 0);
       // assertEquals(1, ret1, 0);
       // assertEquals(1, ret2, 0);
        assertEquals(1, ret3, 0);

    }
}
