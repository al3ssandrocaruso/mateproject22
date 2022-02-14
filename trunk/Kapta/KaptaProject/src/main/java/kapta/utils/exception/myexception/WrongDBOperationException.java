package kapta.utils.exception.myexception;

public class WrongDBOperationException extends FinalException {

    public WrongDBOperationException() {
        super("OPS ... something went wrong ");
    }
}
