package kapta.utils.exception;

import kapta.utils.exception.myexception.*;

public class Trigger {

    private Trigger(){
        //ignored
    }

    public static void throwWrongPassword() throws WrongPasswordException {
        throw new WrongPasswordException();
    }

    public static void confirmPassword(String psw1, String psw2) throws ConfirmPasswordException {
        if(!psw1.equals(psw2)){
            throw new ConfirmPasswordException(psw1, psw2);
        }
    }

    public static void usernameAlreadyExist(String username) throws UsernameConflictException {
        throw new UsernameConflictException(username);
    }

    public static void emptyField(String field) throws InputNullException {
        throw new InputNullException(field);
    }
    public static void urlWrong(String site) throws WrongURLException {
        throw new WrongURLException(site);
    }

    public static void wrongToken(String token) throws TokenException {
        throw new TokenException(token);
    }

    public static void connectionFailed() throws DBConnectionException {
        throw  new DBConnectionException();
    }
    public static void triggerGenericException(Exception e) throws GenericException {
        System.out.println(e.getCause());
        GenericException g = new GenericException();
        g.initCause(e.getCause());
        throw g;
    }
    public  static void mySqlConnectionException(Exception e ) throws MysqlConnectionFailed {
        MysqlConnectionFailed m = new MysqlConnectionFailed();
        m.initCause(e);
        throw m;
    }

    public  static void imageNotFound() throws ImageNotFoundException {
        throw  new ImageNotFoundException();
    }
    public  static void invalidGreenPass(Exception e ) throws InavalidGreenPassException {
        InavalidGreenPassException i = new InavalidGreenPassException();
        i.initCause(e.getCause());
        throw  i;

    }
    public static void expiredGreenPass() throws ExpiredGreenPassException {
        throw new ExpiredGreenPassException();
    }

}
