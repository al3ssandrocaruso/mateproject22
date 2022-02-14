package kapta.utils.bean.beanin;

import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class PartyEventSchedule {

    public PartyEventSchedule(Date date, Time duration, LocalTime orario) {
        this.date = date;
        this.duration = duration;
        this.orario = orario;
    }

    private Date date;
    private Time duration;
    private LocalTime orario;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public LocalTime getOrario() {
        return orario;
    }

    public void setOrario(LocalTime orario) {
        this.orario = orario;
    }

}
