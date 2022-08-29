package kapta.utils.exception.myexception;

public class InavalidGreenPassException extends  DomainException{

    public InavalidGreenPassException() {
        super("The image isn't a QR code!");
    }
}
