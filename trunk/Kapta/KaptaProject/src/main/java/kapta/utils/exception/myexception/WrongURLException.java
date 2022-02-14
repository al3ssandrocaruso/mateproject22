package kapta.utils.exception.myexception;

public class WrongURLException extends MyExceptions {

    public WrongURLException(String message) {
        super("L'indirizzo : "+message+" sembra non essere valido");
    }
}
