package kapta.utils.exception.myexception;

public class WrongURLException extends InputException {

    public WrongURLException(String message) {
        super("L'indirizzo : "+message+" sembra non essere valido","Website","www.yourSite.com");
    }
}
