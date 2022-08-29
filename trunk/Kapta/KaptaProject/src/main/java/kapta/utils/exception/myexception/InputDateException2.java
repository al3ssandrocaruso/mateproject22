package kapta.utils.exception.myexception;

public class InputDateException2 extends InputException{
    public InputDateException2(String inputUser  ,String field,  String correctInput) {
        super(inputUser,field ,correctInput);
    }
}
