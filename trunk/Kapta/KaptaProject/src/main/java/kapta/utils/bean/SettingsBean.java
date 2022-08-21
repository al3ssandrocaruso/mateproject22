package kapta.utils.bean;

public abstract class SettingsBean {

    //User e Club
    protected String username;
    protected String email;
    protected int id;

    //User
    protected String name;
    protected String secondName;

    //Club
    protected String city;
    protected String address;

    //Getter IN
    public String getUsername() {return username;}
    public String getName() {return name;}
    public String getSecondName() {return secondName;}
    public String getEmail() {return email;}
    public String getCity() {return city;}
    public String getAddress() {return address;}
    public int getId() {return id;}

    // setter In
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}
