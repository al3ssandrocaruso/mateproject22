package kapta.utils.exception.myexception;

public  class InputException extends Exception {

    protected InputException(String inputUser, String field,String correctInput) {

        super("Invalid input"+ inputUser +" , "+"for field "+field+".\n"+"Correct Format:"+ correctInput);
    }


}
