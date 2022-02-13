package kapta.model.profiles;
import java.io.File;

public class UserModel extends UserClubModel {
    private String name;
    private String secondName="";
    private String gender="";
    private int numFollowing;

    public UserModel(String username){super(username);}
    public UserModel(String username,String password , String email, String name ,   int type, int numFollowing,File profileImg ) {
        super(username,password,  email,type, profileImg, numFollowing ) ;
        setName(name);
    }


    public void setNumFollowing(int numFollowing) {
        this.numFollowing = numFollowing;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getNumFollowing() {
        return numFollowing;
    }
    public String getName() {return name;}
    public String getSecondName() {
        return secondName;
    }
    public String getGender() {
        return gender;
    }


}