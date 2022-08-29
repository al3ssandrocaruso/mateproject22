package kapta.control.guicontroller.interfaceone;

import kapta.utils.exception.myexception.DomainException;
import kapta.utils.exception.myexception.InputException;
import kapta.utils.exception.myexception.SystemException;
import org.controlsfx.control.Notifications;


public class JFX1AlertCreator {

    private JFX1AlertCreator(){

    }

    public static void createAlertSystem(SystemException systemException){
        Notifications notifications = Notifications.create();
        setNotificationUtils(notifications,"OPS",systemException.getMessage());
    }

    public static void createAlertDomain(DomainException domainException){
        Notifications notifications = Notifications.create();
        setNotificationUtils(notifications,"OPS",domainException.getMessage());
    }

    public static void createAlertInput(InputException inputException){
        Notifications notifications = Notifications.create();
        setNotificationUtils(notifications,"OPS",inputException.getMessage());
    }


    public static void createAlert(Exception e){
        if (e instanceof  SystemException s){
            createAlertSystem(s);
        }
        else if( e instanceof DomainException d ){
            createAlertDomain(d);
        }
        else if(e instanceof InputException i){
            createAlertInput(i);
        }

    }

    private static void setNotificationUtils(Notifications popup, String title, String message  ){
        popup.title(title);
        popup.text(message);
        popup.show();
    }



}
