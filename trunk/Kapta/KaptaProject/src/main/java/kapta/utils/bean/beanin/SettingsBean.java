package kapta.utils.bean.beanin;

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

    //Getter
    public String getUsername() {return username;}
    public String getName() {return name;}
    public String getSecondName() {return secondName;}
    public String getEmail() {return email;}
    public String getCity() {return city;}
    public String getAddress() {return address;}
    public int getId() {return id;}
}
