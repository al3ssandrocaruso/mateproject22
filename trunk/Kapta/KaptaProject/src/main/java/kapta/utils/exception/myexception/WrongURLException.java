package kapta.utils.exception.myexception;

import java.io.Serial;

// Da portare
public class WrongURLException extends MyExceptions {

    @Serial
    private static final long serialVersionUID =11L;

    public WrongURLException(String message) {
        super("L'indirizzo : "+message+" sembra non essere valido");
    }
}
