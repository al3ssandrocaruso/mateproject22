package kapta.utils.exception.myexception;

public class ExpiredGreenPassException extends FinalException{

    public ExpiredGreenPassException() {
        super("Green recognised but expired!");
    }
}
