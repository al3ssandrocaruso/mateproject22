package kapta.utils.exception.myexception;

import java.io.Serial;

public class ImageNotFoundException extends FinalException {

    @Serial
    private static final long serialVersionUID =16L;

    public ImageNotFoundException() {
        super("FAIL: Impossible to set Image");
    }
}
