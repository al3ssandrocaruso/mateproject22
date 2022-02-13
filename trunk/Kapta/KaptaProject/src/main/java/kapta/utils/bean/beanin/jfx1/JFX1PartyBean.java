package kapta.utils.bean.beanin.jfx1;

import kapta.utils.bean.beanin.PartyBean;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class JFX1PartyBean extends PartyBean {

    public JFX1PartyBean() {
    }

    public JFX1PartyBean(String partyName,String partyDuration,String partyAddress,String ore,String minutes, LocalDate partyDate, File partyImg) {
        this.setPartyName(partyName);
        this.setPartyDuration(partyDuration);
        this.setPartyAddress(partyAddress);
        this.setOrario(ore,minutes);
        this.setDate(partyDate);
        this.setImg(partyImg);
    }

    public void setPartyName(String partyName){this.name=partyName;}
    public void setPartyAddress(String partyAddress){this.address=partyAddress;}
    public void setOrario(String ore, String minuti) {this.orario = LocalTime.of(Integer.valueOf(ore),Integer.valueOf(minuti));}
    public void setDate(LocalDate partyDate){this.date = java.sql.Date.valueOf(partyDate);}
    public void setImg(File partyImg){this.image=partyImg;}
    public void setPartyDuration(String partyDuration){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        long ms= 0;
        try {
            ms = sdf.parse(partyDuration).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.duration = new Time(ms);
    }

}
