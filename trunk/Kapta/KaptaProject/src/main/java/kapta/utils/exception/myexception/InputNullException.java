package kapta.utils.exception.myexception;

public class InputNullException extends FinalException{

    public InputNullException(String field){
        super("ERROR: " + "'" + field + "'" + " can't be empty");
    }
}
