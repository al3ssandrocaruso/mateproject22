package kapta.utils.exception.myexception;

public class BusyForANewPartyException extends DomainException{
    public  BusyForANewPartyException(String date) {
        super("Sorry, you have another party in. "+date);
    }
}
