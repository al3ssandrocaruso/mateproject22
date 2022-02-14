package kapta.utils.exception.myexception;

public class MysqlConnectionFailed extends FinalException {
    public MysqlConnectionFailed() {
        super("Connection failed...");
    }
}
