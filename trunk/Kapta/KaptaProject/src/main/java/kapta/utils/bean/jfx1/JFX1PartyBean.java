package kapta.utils.bean.jfx1;

import javafx.scene.image.Image;
import kapta.model.PartyModel;
import kapta.utils.bean.PartyBean;
import kapta.utils.exception.myexception.InputDateException2;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class JFX1PartyBean extends PartyBean {

    public JFX1PartyBean(PartyModel partyModel){
        super(partyModel);
    }

    // this is used by the view
    public JFX1PartyBean(String partyName, String partyDuration, String partyAddress, String ore, String minutes, LocalDate partyDate, File partyImg) throws InputNullException, InputDateException2 {
        this.setPartyNameOut(partyName);
        this.setPartyDurationOut(partyDuration);
        this.setPartyAddressOut(partyAddress);
        this.setOrarioOut(ore,minutes);
        this.setDateOut(partyDate);
        this.setImgOut(partyImg);
    }

    public JFX1PartyBean(PartyBean partyBean){
        super(partyBean);

    }

    public void setPartyNameOut(String partyName) throws InputNullException {
        if("".equals(partyName )){
            throw new InputNullException("Party Name");
        }
        this.name=partyName;
    }
    public void setPartyAddressOut(String partyAddress) throws InputNullException {
        if("".equals(partyAddress )){
            throw new InputNullException("Party Address");
        }
        this.address=partyAddress;
    }
    public void setOrarioOut(String ore, String minuti) throws InputNullException, InputDateException2 {
        if("".equals(ore ) ||"".equals(minuti) ){
            throw new InputNullException("Party Time ");
        }
        try{
        this.orario = LocalTime.of(Integer.valueOf(ore),Integer.valueOf(minuti));
        } catch (DateTimeException d ){
            throw new InputDateException2("", "PartyTime","hh   mm");

        }}
    public void setDateOut(LocalDate partyDate) throws InputNullException, InputDateException2 {
        try {if(partyDate == null) {
            throw new InputNullException("partyDate");
        }
            this.date = java.sql.Date.valueOf(partyDate);
        }catch (DateTimeParseException d){
            throw new InputDateException2(  partyDate.toString(),"PartyDate"," DD/MM/YYYY ");

        }
    }
    public void setImgOut(File partyImg) throws InputNullException {if(partyImg == null){
        throw  new InputNullException("Party Image");
    }
        this.image = partyImg;}
    public void setPartyDurationOut(String partyDuration) throws InputNullException, InputDateException2 {

        try {
            if ("".equals(partyDuration)) {
                throw new InputNullException("Party Time ");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            long ms = 0;
            ms = sdf.parse(partyDuration).getTime();
            this.duration = new Time(ms);
        }catch (DateTimeException  | ParseException dateTimeException){
            throw new InputDateException2(partyDuration,"Party Duration", "hh:mm");
        }
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
    public Image getPartyImgOut() throws SystemException {
            return ImageConverter.convertFileToFxImage(image);
    }
    public String getPartyId() {return String.valueOf(id);}

}
