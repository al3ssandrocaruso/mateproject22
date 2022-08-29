package kapta.utils.exception.myexception;

public class TokenIncorrectException extends DomainException{
    public TokenIncorrectException() {
        super("Incorrect token , retry!");
    }
}
