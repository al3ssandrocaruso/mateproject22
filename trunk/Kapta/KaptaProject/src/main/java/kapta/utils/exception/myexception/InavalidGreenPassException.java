package kapta.utils.exception.myexception;

public class InavalidGreenPassException extends  FinalException{

    public InavalidGreenPassException() {
        super("L'immagine insereita non è un green pass valido");
    }
}
