package kapta.utils.bean.beanin;

public class UserSettingsBean extends SettingsBean {

    public UserSettingsBean(String username, String email, String name, String secondName, int id){
        setUsername(username);
        setEmail(email);
        setName(name);
        setSecondName(secondName);
        setId(id);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setId(int id){this.id=id;}

    public void setEmail(String email) {
        this.email = email;
    }
}
