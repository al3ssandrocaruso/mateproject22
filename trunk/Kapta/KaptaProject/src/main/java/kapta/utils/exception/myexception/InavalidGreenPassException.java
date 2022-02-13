package kapta.utils.exception.myexception;

public class InavalidGreenPassException extends  FinalException{

    public InavalidGreenPassException() {
        super("The image isn't a QR code!");
    }
}
