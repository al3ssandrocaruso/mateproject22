package kapta.utils.exception.myexception;

public class ConfirmPasswordException extends FinalException {

    private static final long serialVersionUID = 1L;

    public ConfirmPasswordException(String psw1, String psw2) {
        super("ERROR: passwords doesn't match. \nPassword: " + "'" + psw1 + "'" + "\nConfirm Password: "  + "'" + psw2+"'");
    }
}
