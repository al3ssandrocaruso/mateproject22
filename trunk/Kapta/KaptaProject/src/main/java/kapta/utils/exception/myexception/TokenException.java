package kapta.utils.exception.myexception;

public class TokenException extends FinalException {

    public TokenException(String token){
        super("Incorrect token or expired" + "'" + token + "'");
    }


}
