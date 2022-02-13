package kapta.utils.exception.myexception;


import java.io.Serial;

public class WrongDBOperationException extends FinalException {

    @Serial
    private static final long serialVersionUID = 11L;

    public WrongDBOperationException() {
        super("OPS ... something went wrong ");
    }
}
