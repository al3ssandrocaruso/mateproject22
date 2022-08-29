package kapta.utils.exception.myexception;

public class ConfirmPasswordException extends DomainException {

    public ConfirmPasswordException(String psw1, String psw2) {
        super("ERROR: passwords doesn't match. \nPassword: " + "'" + psw1 + "'" + "\nConfirm Password: "  + "'" + psw2+"'");
    }
}
