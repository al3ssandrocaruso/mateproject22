package kapta.utils.exception.myexception;

public class WrongCrudException extends  FinalException{
    public WrongCrudException() {
        super("ops .. something went wrong");
    }
}
