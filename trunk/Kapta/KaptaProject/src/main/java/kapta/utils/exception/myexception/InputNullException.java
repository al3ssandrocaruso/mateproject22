package kapta.utils.exception.myexception;

public class InputNullException extends FinalException{

    private static final long serialVersionUID = 7L;

    public InputNullException(String field){
        super("ERROR: " + "'" + field + "'" + " can't be empty");
    }
}
