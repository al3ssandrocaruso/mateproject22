package kapta.utils.bean;

public class ClubSettingsBean extends SettingsBean {

    public ClubSettingsBean(String username, String email, String city, String address, int id){
        setUsername(username);
        setEmail(email);
        setCity(city);
        setAddress(address);
        setId(id);
    }

}
