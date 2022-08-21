package kapta.utils.session;

import kapta.utils.bean.ClubBean;
import kapta.utils.bean.UserBean;

public class Session {

    // 0 per user
    // 1 per Club
    private int type;
    private int id;

    private UserBean userBean;
    private ClubBean clubBean;

    public void setClubBean(ClubBean clubBean) {
        this.clubBean = clubBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public ClubBean getClubBean() {
        return clubBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }







    public Session(UserBean userBean) {
        this.setType(0);
        this.setUserBean(userBean);
        this.setId(userBean.getId());

    }

    public Session(ClubBean clubBean) {
        this.setType(1);
        this.setClubBean(clubBean);
        this.setId(clubBean.getId());
    }




    //Set e Get
    public int getType() {
        return type;
    }
    public void setType(int type){this.type = type;}


    public void setId(int  id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
