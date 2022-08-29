package test.junit;

import kapta.model.TokenModel;
import kapta.utils.utils.GenerateNewToken;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;


/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/
// Failed
public class TestToken {

    /**
     * Si vuole testare che il token generato abbia lunghezza pari a 4 caratteri.
     */
    //SUCCESS
    @Test
    public void testGenerateToken(){
        String token = GenerateNewToken.generateToken();
        int length = token.length();
        assertEquals(4, length, 0);

    }
    /**
     * Si vuole testare che l'orario di creazione del token sia settato.
     */
    //SUCCESS
    @Test
    public void testTokenGenerationTime() throws InterruptedException {
        TokenModel test = new TokenModel("testing");

        Thread.sleep(2000);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime after = LocalDateTime.now();
        dtf.format(after);

        LocalDateTime testDateCreation = test.getGenerationTime();
        int ret = 0;
        if(testDateCreation.isBefore(after)){
            ret = 1;
        }

        assertEquals(1, ret, 0);
    }

    /**
     * Si vuole testare che la stringa TokenModel venga settata correttamnte.
     */
    //FAILED -> Pessimistc Coverage
    @Test
    public void testTokenString(){
        TokenModel tokenModel = new TokenModel("test");
        tokenModel.setToken("testProva2");

        String token = tokenModel.getToken();

        assertEquals(0, token.compareTo("test"), 0);
    }
}
