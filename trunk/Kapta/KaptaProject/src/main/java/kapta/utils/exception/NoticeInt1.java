package kapta.utils.exception;
import kapta.utils.utils.GetDialogStage;

public class NoticeInt1 implements Notice {

    public NoticeInt1(String message) {
        GetDialogStage.startPopUp(null,message,"green");
    }
}
