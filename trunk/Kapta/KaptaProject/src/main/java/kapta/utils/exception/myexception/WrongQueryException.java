package kapta.utils.exception.myexception;

public class WrongQueryException extends FinalException{

    public WrongQueryException() {
        super("ops .. something went wrong ");
    }
}
