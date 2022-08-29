package kapta.utils.exception.myexception;

// alla registrazione lo username scelto è già presente
public class UsernameConflictException extends DomainException {

    public UsernameConflictException(String username){
        super("ERROR: username " + "'" + username + "'" + " already exist");
    }
}
