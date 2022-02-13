package kapta.model.profiles;
import kapta.model.GenericModel;
import java.io.File;

public abstract class UserClubModel implements GenericModel {

    private String username;
    private  String password;
    private String email;
    private int type;
    private int id ;
    private File profileImg;
    private int numFollower;


    protected UserClubModel(String username){
        setUsername(username);
    }
    protected UserClubModel (String username , String email, int type, File profileImg , int numFollower){
        setUsername(username);
        setEmail(email);
        setNumFollower(numFollower);
        setType(type);
        setProfileImg(profileImg);
    }
    protected UserClubModel (int id, String username , String email, int type, File profileImg , int numFollower){
        setId(id);
        setUsername(username);
        setEmail(email);
        setNumFollower(numFollower);
        setType(type);
        setProfileImg(profileImg);
    }
    protected UserClubModel(String username ,String password, String email, int type, File profileImg , int numFollower){
        setId(id);
        setUsername(username);
        setEmail(email);
        setNumFollower(numFollower);
        setType(type);
        setProfileImg(profileImg);
        setPassword(password);
    }

    public  void setPassword(String password){this.password =password; }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNumFollower(int numFollower) {
        this.numFollower = numFollower;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProfileImg(File profileImg) {
        this.profileImg= profileImg ;
    }


    public String getUsername() {
        return username;
    }
    public  String getPassword(){return this.password;}
    public String getEmail() {
        return email;
    }
    public Integer getNumFollower() {
        return numFollower;
    }
    public int getType() {return type;}
    public int getId() {
        return id;
    }
    public File getProfileImg() {
        return profileImg;
    }

}
