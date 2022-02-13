package kapta.model.profiles;
import java.io.File;
import java.net.URL;

public class ClubModel extends UserClubModel {

    private String clubName ;
    private String clubAddress;
    private URL website;
    private String city;

    //ricorda che gli eventi creati nel db sono numfollowers => cambiamento durante la progettazione
    public ClubModel(String username, String password, String email, String clubName, String city, URL website , String clubAddress, int createdEvent, int type, File profileImg){
        super(username, password, email,type, profileImg, createdEvent ) ;
        setClubName(clubName);
        setAddress(clubAddress);
        setWebsite(website);
        setCity(city);
        setProfileImg(profileImg);
    }

    public ClubModel(String username){
        super(username);}

    public void setAddress(String address) {this.clubAddress = address;}
    public void setWebsite(URL website) {this.website = website;}
    public void setCity(String city) {this.city = city;}
    public void setClubName(String clubName) {this.clubName = clubName;}

    public String getCity() {return city;}
    public String getAddress() {return clubAddress;}
    public URL getWebsite() {return website;}
    public String getClubName() {return clubName;}

    public File getProfileImage() {return getProfileImg();}
}

