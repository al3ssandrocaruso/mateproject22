package kapta.utils.exception;

//AbstractFactory
public interface ErrorDialog {

    public abstract Notice createNotice(String message);
    public abstract Warning createWarning(String message);

}
