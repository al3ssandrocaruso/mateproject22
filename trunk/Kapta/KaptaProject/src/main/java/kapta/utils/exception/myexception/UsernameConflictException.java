package kapta.utils.exception.myexception;

import java.io.Serial;

// alla registrazione lo username scelto è già presente
public class UsernameConflictException extends FinalException {

    @Serial
    private static final long serialVersionUID = 9L;

    public UsernameConflictException(String username){
        super("ERROR: username " + "'" + username + "'" + " already exist");
    }
}
