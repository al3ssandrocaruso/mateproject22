package kapta.utils.exception.myexception;

public class EmailValidatorException extends FinalException {

    private static final long serialVersionUID = 5L;

    public EmailValidatorException(String email){
        super("ERROR: email " + "'" + email + "'" + " is not valid");
    }
}
