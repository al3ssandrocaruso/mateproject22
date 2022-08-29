package kapta.utils.exception.myexception;

public class MysqlConnectionFailed extends SystemException {
    public MysqlConnectionFailed() {
        super("Connection failed..."+"\n"+"Check your internet connection");
    }
}
