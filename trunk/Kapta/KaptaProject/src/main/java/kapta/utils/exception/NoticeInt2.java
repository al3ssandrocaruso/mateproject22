package kapta.utils.exception;

import kapta.utils.utils.GetDialogStage;

public class NoticeInt2 implements Notice {
    public NoticeInt2(String message) {
        GetDialogStage.startPopUp(null,message,"white");
    }
}
