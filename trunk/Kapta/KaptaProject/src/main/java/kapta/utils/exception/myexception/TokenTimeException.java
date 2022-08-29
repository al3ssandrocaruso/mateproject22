package kapta.utils.exception.myexception;

public class TokenTimeException extends DomainException{

    public TokenTimeException() {
        super("Sorry, time expired." + "\n" + "Check your e-mail and insert  new Token");
    }
}
