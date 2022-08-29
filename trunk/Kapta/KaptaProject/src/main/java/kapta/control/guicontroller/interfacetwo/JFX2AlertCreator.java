package kapta.control.guicontroller.interfacetwo;

import javafx.scene.control.Alert;
import kapta.utils.exception.myexception.DomainException;
import kapta.utils.exception.myexception.InputException;
import kapta.utils.exception.myexception.SystemException;

public class JFX2AlertCreator {

    private JFX2AlertCreator(){

    }

    public static void createAlertSystem(SystemException systemException){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        setAlertUtils(alert,"OPS",systemException.getMessage(), true);
    }

    public static void createAlertDomain(DomainException domainException){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        setAlertUtils(alert,"OPS",domainException.getMessage(), true);
    }
    public static void createAlertInput(InputException e){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        setAlertUtils(alert,"OPS",e.getMessage(), true);
    }

    public static void createAlert(Exception e){
        if (e instanceof  SystemException s){
            createAlertSystem(s);
        }
        else if( e instanceof DomainException d ){
            createAlertDomain(d);
        }else if( e instanceof InputException i ){
            createAlertInput(i);
        }

    }
    private static void setAlertUtils(Alert alert, String title, String message,boolean t  ){
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setResizable(t);
        alert.showAndWait();
    }
}
