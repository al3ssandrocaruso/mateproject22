package kapta.utils.bean.beanin.jfx2;

import kapta.utils.bean.beanin.PartyBean;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String durationStr="0"+partyDuration.toString().charAt(0)+":";
        String support=""+partyDuration.toString().charAt(2);
        if(support.equals("0")){durationStr=durationStr.concat("00");}
        else{durationStr=durationStr.concat("30");}

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm"); //e.g 12:15

        long ms= 0;
        try {
            ms = sdf.parse(durationStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.duration = new Time(ms);
    }


}
