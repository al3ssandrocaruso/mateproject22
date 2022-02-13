package kapta.utils.exception;

public class ErrorDialogInt1 implements ErrorDialog {

    @Override
    public Notice createNotice(String message) {
        return new NoticeInt1(message);
    }

    @Override
    public Warning createWarning(String message) {
        return new WarningInt1(message);
    }
}
