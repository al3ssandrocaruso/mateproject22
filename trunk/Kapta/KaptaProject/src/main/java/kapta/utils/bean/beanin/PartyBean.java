package kapta.utils.bean.beanin;


import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public abstract class PartyBean {
    protected String name;
    protected String address;
    protected LocalTime orario;
    protected Date date;
    protected Time duration;
    protected int id;
    protected File image;

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
}
