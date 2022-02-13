package kapta.utils.exception.myexception;

public class TokenException extends FinalException {

    private static final long serialVersionUID = 13L;

    public TokenException(String token){
        super("Incorrect token or expired" + "'" + token + "'");
    }


}
