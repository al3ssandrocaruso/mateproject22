package kapta.utils.exception.myexception;

public class MysqlConnectionFailed extends FinalException {

    private static final long serialVersionUID = 11L;

    public MysqlConnectionFailed() {
        super("Connection failed...");
    }
}
