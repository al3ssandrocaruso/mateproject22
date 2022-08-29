package kapta.utils.exception;


import COSE.CoseException;
import com.google.iot.cbor.CborParseException;
import kapta.utils.exception.myexception.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.DataFormatException;


public class ErrorHandler {


    private static ErrorHandler instance= null;

    public static ErrorHandler getInstance() {
        if(instance == null){
            instance = new ErrorHandler();
        }
        return instance;
    }



    //Se è da DB -> Alert
    //Se è da altro -> PopUp


    public void handleException(Exception e) throws SystemException {

        if (e instanceof SQLException sql){

            int errorCode = sql.getErrorCode();
            sql.printStackTrace();
            if(errorCode ==0){
                throw new MysqlConnectionFailed();
            }
            else{
                e.printStackTrace();
                throw new SystemException();
            }
        }
        if(e instanceof IOException||  e instanceof  DataFormatException){
            throw new SystemException();
        }
        if(e instanceof CoseException || e instanceof CborParseException ){
            throw new SystemException();
        }

    }


}




