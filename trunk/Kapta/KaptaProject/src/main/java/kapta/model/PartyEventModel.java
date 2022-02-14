package kapta.model;

import kapta.utils.bean.beanin.PartyEventSchedule;
import java.io.File;
import java.sql.Time;
import java.time.*;
import java.util.Date;

public abstract class PartyEventModel implements GenericModel {

    protected String name;
    protected String address;
    protected int status;
    protected int id;
    protected int type; //0 ->PARTY ; 1-> EVENT
    PartyEventSchedule partyEventSchedule;

    protected File img;

    protected PartyEventModel (String name, String address, int status, int type,  File img, PartyEventSchedule partyEventSchedule){
        setName(name);
        setAddress(address);
        setStatus(status);
        setType(type);
        setImg(img );
        setPartyEventSchedule(partyEventSchedule);
    }

    protected PartyEventModel (String name, String address, int status, int id, int type, File img, PartyEventSchedule partyEventSchedule ){
        setName(name);
        setAddress(address);
        setStatus(status);
        setId(id);
        setType(type);
        setImg(img );
        setPartyEventSchedule(partyEventSchedule);
    }
    protected PartyEventModel(int id, int t){

        setId(id);
        setType(t);
    }
    public void setPartyEventSchedule(PartyEventSchedule partyEventSchedule){
        this.partyEventSchedule = partyEventSchedule;
    }
    public void setType(int type) {this.type = type;}
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setImg(File img){this.img = img;}
    public void setId(int id) {
        this.id = id;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {return type;}
    public String getName() {
        return name;
    }
    public Date getDate() {
        return partyEventSchedule.getDate();
    }
    public String getAddress() {
        return address;
    }
    public Time getDuration() {
        return partyEventSchedule.getDuration();
    }
    public File getImg() {return img;}
    public int getId() {
        return id;
    }
    public int getStatus() {
        return status;
    }
    public LocalTime getOrario() {
        return partyEventSchedule.getOrario();
    }
    public PartyEventSchedule getPartyEventSchedule() {
        return partyEventSchedule;
    }
}

