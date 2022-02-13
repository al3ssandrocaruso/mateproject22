package kapta.utils.exception;


import kapta.utils.exception.myexception.*;
import kapta.utils.session.ThreadLocalSession;

import java.sql.SQLException;


public class ErrorHandler {

//
    private static ErrorHandler instance= null;

    public static ErrorHandler getInstance() {
        if(instance == null){
            instance = new ErrorHandler();
        }
        return instance;
    }



    //Se è da DB -> Alert
    //Se è da altro -> PopUp

    public void reportFinalException(FinalException e)  {
        String message = e.getMessage();
        ErrorDialog errorDialog = createErrorDialog();

        if(e instanceof ConfirmPasswordException || e instanceof MysqlConnectionFailed ||e instanceof WrongPasswordException || e instanceof InputNullException || e instanceof EmailValidatorException || e instanceof GenericException || e instanceof TokenException ){
            errorDialog.createNotice(message);
        }
        if(e instanceof WrongDBOperationException || e instanceof WrongQueryException || e instanceof WrongCrudException || e instanceof  UsernameConflictException || e instanceof InavalidGreenPassException || e instanceof ExpiredGreenPassException){
            errorDialog.createWarning(message);
        }
    }
    public void reportInvelidGreenPass(Exception e ) throws InavalidGreenPassException {
        Trigger.invalidGreenPass(e);

    }

    public  void reportGeneric(Exception e ) throws GenericException {
        Trigger.triggerGenericException(e);
    }



    public void sqlexceptionqueryhandler(SQLException sql ) throws MysqlConnectionFailed,  WrongQueryException {
        int errorCode = sql.getErrorCode();
        if(errorCode ==0){
            throw new MysqlConnectionFailed();
        }
        else{
            throw new WrongQueryException();
            }
    }

    public void sqlexceptioncrudhandler(SQLException sql ) throws MysqlConnectionFailed, WrongCrudException {
        int errorCode = sql.getErrorCode();
        if(errorCode ==0){
            throw new MysqlConnectionFailed();
        }
        else{
            throw new WrongCrudException();
        }
    }

    public void tokenException(String tokenString) throws TokenException {
        Trigger.wrongToken(tokenString);
    }

    private ErrorDialog createErrorDialog() {
        ErrorDialog errorDialog;
        if(ThreadLocalSession.getIntrfc()==1){
            errorDialog = new ErrorDialogInt1();
        }
        else{
            errorDialog = new ErrorDialogInt2();
        }
        return errorDialog;
    }
}


