package kapta.utils.exception.myexception;

public class InputNullException extends DomainException{

    public InputNullException(String field){
        super("ERROR: " + "'" + field + "'" + " can't be empty");
    }
}
