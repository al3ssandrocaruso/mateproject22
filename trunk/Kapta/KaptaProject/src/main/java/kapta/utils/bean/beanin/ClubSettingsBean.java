package kapta.utils.bean.beanin;

public class ClubSettingsBean extends SettingsBean {

    public ClubSettingsBean(String username, String email, String city, String address, int id){
        setUsername(username);
        setEmail(email);
        setCity(city);
        setAddress(address);
        setId(id);
    }

    private void setAddress(String address) {this.address=address;}
    private void setCity(String city) {this.city=city;}
    private void setEmail(String email) {this.email=email;}
    private void setUsername(String username) {this.username = username;}
    private void setId(int id) {this.id=id;}
}
