package kapta.model.profiles;
import kapta.model.GenericModel;
import kapta.utils.bean.InfoLogged;

import java.io.File;

public abstract class UserClubModel implements GenericModel {


    private int id ;
    private File profileImg;
    private int numFollower;

    private InfoLogged infoLogged;

    public InfoLogged getInfoLogged() {
        return infoLogged;
    }

    protected UserClubModel(String username){
        this.infoLogged = new InfoLogged(username);
    }

    protected UserClubModel (InfoLogged infoLogged, File profileImg , int numFollower){
        setNumFollower(numFollower);
        setProfileImg(profileImg);
        setInfoLogged(infoLogged);
    }
    protected UserClubModel (int id, InfoLogged infoLogged, File profileImg , int numFollower){
        setId(id);
        setInfoLogged(infoLogged);
        setNumFollower(numFollower);
        setProfileImg(profileImg);
    }

    public void setInfoLogged(InfoLogged infoLogged) {this.infoLogged = infoLogged;}
    public void setNumFollower(int numFollower) {this.numFollower = numFollower;}
    public void setId(int id) {this.id = id;}
    public void setProfileImg(File profileImg) {this.profileImg= profileImg ;}


    public String getUsername() {
        return this.infoLogged.getUsername();
    }
    public  String getPassword(){return this.infoLogged.getPassword();}
    public String getEmail() {return this.infoLogged.getEmail();}
    public int getType() {return this.infoLogged.getType();}
    public Integer getNumFollower() {
        return numFollower;
    }

    public int getId() {
        return id;
    }
    public File getProfileImg() {
        return profileImg;
    }

}
