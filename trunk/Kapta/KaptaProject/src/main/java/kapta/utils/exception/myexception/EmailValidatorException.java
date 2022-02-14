package kapta.utils.exception.myexception;

public class EmailValidatorException extends FinalException {

    public EmailValidatorException(String email){
        super("ERROR: email " + "'" + email + "'" + " is not valid");
    }
}
