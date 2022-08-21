package kapta.utils.bean;


import kapta.model.PartyModel;
import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public  class PartyBean implements PartyEventBean {

    protected String partyCreator;
    protected String name;
    protected String address;
    protected LocalTime orario;
    protected Date date;
    protected Time duration;
    protected int id;
    protected File image;


    public PartyBean(PartyModel partyModel) {
        this.setPartyCreator(partyModel.getPartyCreator().getUsername());
        this.setName(partyModel.getName());
        this.setAddress(partyModel.getAddress());
        this.setOrario(partyModel.getOrario());
        this.setDate(partyModel.getDate());
        this.setDuration(partyModel.getDuration());
        this.setId(partyModel.getId());
        this.setImage(partyModel.getImg());
    }

    public  PartyBean(){}

    protected PartyBean(PartyBean partyBean){
        this.setPartyCreator(partyBean.getPartyCreator());
        this.setName(partyBean.getName());
        this.setAddress(partyBean.getAddress());
        this.setOrario(partyBean.getOrario());
        this.setDate(partyBean.getDate());
        this.setDuration(partyBean.getDuration());
        this.setId(partyBean.getId());
        this.setImage(partyBean.getImage());

    }

    public void setPartyCreator(String partyCreator) {
        this.partyCreator = partyCreator;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setOrario(LocalTime orario) {
        this.orario = orario;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDuration(Time duration) {
        this.duration = duration;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setImage(File image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public LocalTime getOrario() {
        return orario;
    }
    public Date getDate() {
        return date;
    }
    public Time getDuration() {
        return duration;
    }
    public int getId() {
        return id;
    }
    public File getImage() {
        return image;
    }
    public String getPartyCreator() {
        return partyCreator;}
}
