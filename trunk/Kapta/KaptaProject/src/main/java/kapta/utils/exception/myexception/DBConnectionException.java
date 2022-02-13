package kapta.utils.exception.myexception;

// Da portare
public class DBConnectionException extends FinalException {

    private static final long serialVersionUID = 2L;

    public DBConnectionException() {
        super("Ops....something went wrong: check your connection");
    }
}
