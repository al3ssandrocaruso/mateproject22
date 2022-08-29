package kapta.utils.exception.myexception;

public  class SystemException  extends Exception {
    public SystemException(){
        super("Ops... Something went wrong ");

    }
    protected SystemException(String message){
        super ("Ops... Something went wrong "+ "\n"+ message);
    }

}
