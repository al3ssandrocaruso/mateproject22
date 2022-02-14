package kapta.utils.bean;

public class InfoLogged {
    private String username;
    private String password;
    private String email;
    private int type;

    public InfoLogged(String username, String email, String password, int type){
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setType(type);
    }

    public InfoLogged(String username){
        this.username = username;
    }

    public  void setPassword(String password){this.password =password; }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setType(int type) {this.type = type;}

    public String getUsername() {
        return username;
    }
    public  String getPassword(){return this.password;}
    public String getEmail() {return email;}
    public int getType() {
        return type;
    }
}
