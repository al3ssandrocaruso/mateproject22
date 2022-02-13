package kapta.utils.bean.beanin.jfx2;

import kapta.utils.bean.beanin.PartyBean;
import kapta.utils.utils.HelperSetDuration;

import java.io.File;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JFX2PartyBean extends PartyBean {

    public JFX2PartyBean(String partyName,Double partyDuration,String partyAddress,String orario, String partyDate, File partyImg) {
        this.setPartyName(partyName);
        this.setPartyDuration(partyDuration);
        this.setPartyAddress(partyAddress);
        this.setOrario(orario);
        this.setDate(partyDate);
        this.setImg(partyImg);
    }

    public void setPartyName(String partyName){this.name=partyName;}
    public void setPartyAddress(String partyAddress){this.address=partyAddress;}
    public void setImg(File partyImg){this.image=partyImg;}
    public void setOrario(String orario) {
        Integer ore=Integer.valueOf(orario.substring(0,2));
        Integer minuti=Integer.valueOf(orario.substring(3,5));
        this.orario = LocalTime.of(Integer.valueOf(ore),Integer.valueOf(minuti));}
    public void setDate(String partyDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(partyDate, formatter);
        this.date = java.sql.Date.valueOf(localDate);
    }
    public void setPartyDuration(Double partyDuration){
        this.duration = new Time(HelperSetDuration.conv(partyDuration));
    }


}
