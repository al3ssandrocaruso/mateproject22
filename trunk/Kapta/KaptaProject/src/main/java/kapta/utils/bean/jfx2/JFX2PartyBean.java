package kapta.utils.bean.jfx2;

import javafx.scene.image.Image;
import kapta.model.PartyModel;
import kapta.utils.bean.PartyBean;
import kapta.utils.utils.HelperSetDuration;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JFX2PartyBean extends PartyBean {

    public JFX2PartyBean(String partyName, Double partyDuration, String partyAddress, String orario, String partyDate, File partyImg) {
        this.setPartyNameOut(partyName);
        this.setPartyDurationOut(partyDuration);
        this.setPartyAddressOut(partyAddress);
        this.setOrarioOut(orario);
        this.setDateOut(partyDate);
        this.setImgOut(partyImg);
    }

    public JFX2PartyBean(PartyModel partyModel) {
        super(partyModel);
    }

    public JFX2PartyBean(PartyBean partyBean){
        this.setPartyCreator(partyBean.getPartyCreator());
        this.setName(partyBean.getName());
        this.setAddress(partyBean.getAddress());
        this.setOrario(partyBean.getOrario());
        this.setDate(partyBean.getDate());
        this.setDuration(partyBean.getDuration());
        this.setId(partyBean.getId());
        this.setImage(partyBean.getImage());

    }

    public void setPartyNameOut(String partyName) {
        this.name = partyName;
    }

    public void setPartyAddressOut(String partyAddress) {
        this.address = partyAddress;
    }

    public void setImgOut(File partyImg) {
        this.image = partyImg;
    }

    public void setOrarioOut(String orario) {
        Integer ore = Integer.valueOf(orario.substring(0, 2));
        Integer minuti = Integer.valueOf(orario.substring(3, 5));
        this.orario = LocalTime.of(Integer.valueOf(ore), Integer.valueOf(minuti));
    }

    public void setDateOut(String partyDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(partyDate, formatter);
        this.date = java.sql.Date.valueOf(localDate);
    }

    public void setPartyDurationOut(Double partyDuration) {
        this.duration = new Time(HelperSetDuration.conv(partyDuration));
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





}
