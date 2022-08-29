package kapta.utils.exception.myexception;

// da portare con modifica null pointer exception. Query db .
public class WrongPasswordException extends DomainException {

    public WrongPasswordException( ){
        super("Username o password errati");
    }

}
