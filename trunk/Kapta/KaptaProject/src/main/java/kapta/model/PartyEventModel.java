package kapta.model;

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
    protected Date date;
    protected Time duration;
    protected LocalTime orario;
    protected File img;

    protected PartyEventModel (String name, String address, int status, int type, Date date, Time duration, LocalTime orario, File img){
        setName(name);
        setAddress(address);
        setOrario(orario);
        setStatus(status);

        setType(type);
        setDate(date);
        setDuration(duration);
        setImg(img );
    }

    protected PartyEventModel (String name, String address, int status, int id, int type, Date date, Time duration, LocalTime orario, File img ){
        setName(name);
        setAddress(address);
        setOrario(orario);
        setStatus(status);
        setId(id);
        setType(type);
        setDate(date);
        setDuration(duration);
        setImg(img );
    }
    protected PartyEventModel(int id, int t){

        setId(id);
        setType(t);
    }
    public void setType(int type) {this.type = type;}
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDuration(Time  duration) {
        this.duration=duration;
    }
    public void setImg(File img){this.img = img;}
    public void setId(int id) {
        this.id = id;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setOrario(LocalTime orario) {
        this.orario = orario;
    }

    public int getType() {return type;}
    public String getName() {
        return name;
    }
    public Date getDate() {
        return date;
    }
    public String getAddress() {
        return address;
    }
    public Time getDuration() {
        return duration;
    }
    public File getImg() {return img;}
    public int getId() {
        return id;
    }
    public int getStatus() {
        return status;
    }
    public LocalTime getOrario() {
        return orario;
    }
}

