package kapta.utils.exception.myexception;

public abstract class DomainException extends Exception{
    protected DomainException(String message){
        super(message);

    }
}
