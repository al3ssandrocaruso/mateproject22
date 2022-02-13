package kapta.utils.exception;

import kapta.utils.utils.GetDialogStage;

public class WarningInt1 implements Warning {
    public WarningInt1(String message) {
        GetDialogStage.startPopUp(null,message,"blue");
    }
}
