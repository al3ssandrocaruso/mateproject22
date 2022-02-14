package test.junit;

import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.utils.MysqlConnection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/
public class TestMySqlConnection {

    /**
     *Test banale ma che mi permette di testare l'efficacia della singleton sulla connessione al db
     */
    //SUCCESS
    @Test
    public void testConnection() throws MysqlConnectionFailed {
        int ret = 0;

        if(MysqlConnection.mysqlConnection() != null){
            ret = 1;
        }
        assertEquals(1, ret, 0);
    }
}
