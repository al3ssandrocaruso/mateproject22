package kapta.utils.bean;

public abstract class LoginBean {
    protected String username;
    protected String password;
    protected int type;
    public int getType() {
        return type;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
