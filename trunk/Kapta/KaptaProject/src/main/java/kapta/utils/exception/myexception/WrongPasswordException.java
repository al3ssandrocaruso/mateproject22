package kapta.utils.exception.myexception;

// da portare con modifica null pointer exception. Query db .
public class WrongPasswordException extends FinalException {

    public WrongPasswordException( ){
        super("Username o password errati");
    }

}
