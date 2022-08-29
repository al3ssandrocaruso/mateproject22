package kapta.utils.exception.myexception;


public class ImageNotFoundException extends DomainException {

    public ImageNotFoundException() {
        super("FAIL: Impossible to set Image, please load  an Image !");
    }
}
