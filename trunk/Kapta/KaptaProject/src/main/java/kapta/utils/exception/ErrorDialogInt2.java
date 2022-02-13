package kapta.utils.exception;

public class ErrorDialogInt2 implements ErrorDialog {

    @Override
    public Notice createNotice(String message) {
        return new NoticeInt2(message);
    }

    @Override
    public Warning createWarning(String message) {
        return new WarningInt2(message);
    }
}
