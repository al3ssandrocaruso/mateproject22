package kapta.utils.exception.myexception;

public class EmailValidatorException extends InputException {

    public EmailValidatorException(String email){
        super("ERROR: email " + "'" + email + "'" + " is not valid", "e-mail","yourMail@yourHost.com");
    }
}
