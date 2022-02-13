package kapta.utils.utils;

public class EmailValidator {

    private EmailValidator(){
        //ignore
    }

    public static boolean validate(String email) {
        return email != null && !email.isEmpty() &&
                    email.replaceAll("[^@]", "").length() == 1
                    && !email.contains(".@");
        }
}
