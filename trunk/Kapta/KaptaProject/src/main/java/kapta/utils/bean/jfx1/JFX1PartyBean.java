package kapta.utils.bean.jfx1;

import javafx.scene.image.Image;
import kapta.model.PartyModel;
import kapta.utils.bean.PartyBean;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class JFX1PartyBean extends PartyBean {

    public JFX1PartyBean(PartyModel partyModel){
        super(partyModel);
    }

    // this is used by the view
    public JFX1PartyBean(String partyName, String partyDuration, String partyAddress, String ore, String minutes, LocalDate partyDate, File partyImg) {
        this.setPartyNameOut(partyName);
        this.setPartyDurationOut(partyDuration);
        this.setPartyAddressOut(partyAddress);
        this.setOrarioOut(ore,minutes);
        this.setDateOut(partyDate);
        this.setImgOut(partyImg);
    }

    public JFX1PartyBean(PartyBean partyBean){
        this.setPartyCreator(partyBean.getPartyCreator());
        this.setName(partyBean.getName());
        this.setAddress(partyBean.getAddress());
        this.setOrario(partyBean.getOrario());
        this.setDate(partyBean.getDate());
        this.setDuration(partyBean.getDuration());
        this.setId(partyBean.getId());
        this.setImage(partyBean.getImage());

    }

    public void setPartyNameOut(String partyName){this.name=partyName;}
    public void setPartyAddressOut(String partyAddress){this.address=partyAddress;}
    public void setOrarioOut(String ore, String minuti) {this.orario = LocalTime.of(Integer.valueOf(ore),Integer.valueOf(minuti));}
    public void setDateOut(LocalDate partyDate){this.date = java.sql.Date.valueOf(partyDate);}
    public void setImgOut(File partyImg){this.image=partyImg;}
    public void setPartyDurationOut(String partyDuration){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        long ms= 0;
        try {
            ms = sdf.parse(partyDuration).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.duration = new Time(ms);
    }


    public String getPartyCreatorOut() {return partyCreator;}
    public String getPartyNameOut() {
        return name;
    }
    public String getPartyAddressOut() {return address;}
    public String getPartyTimeOut() {
        return String.valueOf(orario);
    }
    public String getPartyDateOut() {return  String.valueOf(date);}
    public String getPartyDurationOut() {return String.valueOf(duration);}
    public Image getPartyImgOut() {return ImageConverter.convertFileToFxImage(image);}
    public String getPartyId() {return String.valueOf(id);}

}
