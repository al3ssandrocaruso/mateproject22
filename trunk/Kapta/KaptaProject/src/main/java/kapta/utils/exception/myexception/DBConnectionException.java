package kapta.utils.exception.myexception;

// Da portare
public class DBConnectionException extends SystemException {

    public DBConnectionException() {
        super("Ops....something went wrong: check your connection");
    }
}
