package kapta.utils.exception.myexception;

import java.io.Serial;

// da portare con modifica null pointer exception. Query db .
public class WrongPasswordException extends FinalException {

    @Serial
    private static final long serialVersionUID = 10L;

    public WrongPasswordException( ){
        super(  "Username o password errati");
    }

}
