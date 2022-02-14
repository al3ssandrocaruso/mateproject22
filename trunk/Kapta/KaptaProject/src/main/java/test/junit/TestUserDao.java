package test.junit;

import kapta.model.profiles.UserModel;
import kapta.utils.dao.UserDao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/

//Nei test sfruttiamo i dati dell'utente "a" => potrebbe non andare se tale utente venisse cancellato dal db
public class TestUserDao {

    /**
     * Serie di test che permettono di verificare se effittivamente un utente cercato è presente all'interno del db
     */
    //SUCCESS
    @Test
    public void testUserById() {
        int id = 88;
        String username = "a";
        String name = "a";
        String secondName = "a";
        String email = "a@a.com";

        UserModel userModel = UserDao.getUserById(id);

        assertEquals(0, username.compareTo(userModel.getUsername()), 0);
        assertEquals(0, name.compareTo(userModel.getName()), 0);
        assertEquals(0, secondName.compareTo(userModel.getSecondName()), 0);
        assertEquals(0, email.compareTo(userModel.getEmail()), 0);
    }

    //SUCCESS
    @Test
    public void testUserByUsername() {
        int id = 88;
        String username = "a";
        String name = "a";
        String secondName = "a";
        String email = "a@a.com";

        UserModel userModel = null;
        userModel = UserDao.getUserByUsername("a");

        assertEquals(id, userModel.getId(), 0);
        assertEquals(0, name.compareTo(userModel.getName()), 0);
        assertEquals(0, secondName.compareTo(userModel.getSecondName()), 0);
        assertEquals(0, email.compareTo(userModel.getEmail()), 0);
    }

}
