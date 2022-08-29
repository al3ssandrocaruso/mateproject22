package kapta.utils.exception.myexception;

public class PartyExpiredException extends DomainException{
    public PartyExpiredException() {
        super("Sorry, the Party selected has expired");
    }

}
