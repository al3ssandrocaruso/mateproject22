package test.junit;

import kapta.utils.db.Query;
import kapta.utils.exception.myexception.MysqlConnectionFailed;

import kapta.utils.utils.MysqlConnection;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;


/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/

//Nel test cerchiamo nel db il "party22" che ha id = 43 => se il party cambia id o viene cancellato bisogna cmbiare il test
public class TestQueryParty {

    /**
     * Questo test ci permette di chiedere informazioni al db riguardo i party inserendo il nome del party
     */

    //SUCCESS
    @Test
    public void testAskParty() throws SQLException, MysqlConnectionFailed {
        Statement stm = MysqlConnection.mysqlConnection();
        ResultSet rst = Query.askPartybyPartyName(stm, "party1");

        if(rst.next()){
            rst.first();
        }

        int id = rst.getInt(1);

        assertEquals(32, id, 0);
    }

}
