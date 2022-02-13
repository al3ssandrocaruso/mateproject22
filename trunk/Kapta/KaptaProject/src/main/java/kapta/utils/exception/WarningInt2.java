package kapta.utils.exception;

import kapta.utils.utils.GetDialogStage;

public class WarningInt2 implements Warning {

    public WarningInt2(String message) {
        GetDialogStage.startPopUp(null,message,"red");
    }

}
