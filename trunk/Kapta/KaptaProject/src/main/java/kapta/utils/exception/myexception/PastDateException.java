package kapta.utils.exception.myexception;


public class PastDateException extends MyExceptions {

    private static final long serialVersionUID = 8L;

    public PastDateException(String message) {
        super(message);
    }
}
