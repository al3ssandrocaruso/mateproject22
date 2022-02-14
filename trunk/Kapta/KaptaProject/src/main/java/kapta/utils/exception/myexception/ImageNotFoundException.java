package kapta.utils.exception.myexception;

public class ImageNotFoundException extends FinalException {

    public ImageNotFoundException() {
        super("FAIL: Impossible to set Image");
    }
}
