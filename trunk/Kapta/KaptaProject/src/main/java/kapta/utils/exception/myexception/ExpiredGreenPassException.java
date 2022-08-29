package kapta.utils.exception.myexception;

public class ExpiredGreenPassException extends DomainException{

    public ExpiredGreenPassException() {
        super("Green recognised but expired!");
    }
}
