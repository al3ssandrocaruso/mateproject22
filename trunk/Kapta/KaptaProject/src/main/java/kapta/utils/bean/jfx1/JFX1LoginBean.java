package kapta.utils.bean.jfx1;

import kapta.utils.bean.LoginBean;

public class JFX1LoginBean extends LoginBean {
    public JFX1LoginBean(String username,String password, int type) {
        this.setUsername(username);
        this.setPassword(password);
        this.setType(type);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setType(int type) {
        this.type = type;
    }
}
