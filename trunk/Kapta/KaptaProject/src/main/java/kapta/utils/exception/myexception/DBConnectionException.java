package kapta.utils.exception.myexception;

// Da portare
public class DBConnectionException extends FinalException {



    public DBConnectionException() {
        super("Ops....something went wrong: check your connection");
    }
}
