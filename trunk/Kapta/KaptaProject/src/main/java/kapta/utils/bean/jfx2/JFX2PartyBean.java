package kapta.utils.bean.jfx2;

import javafx.scene.image.Image;
import kapta.model.PartyModel;
import kapta.utils.bean.PartyBean;
import kapta.utils.exception.myexception.InputDateException2;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.utils.HelperSetDuration;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JFX2PartyBean extends PartyBean {

    public JFX2PartyBean(String partyName, Double partyDuration, String partyAddress, String orario, String partyDate, File partyImg) throws InputNullException, InputDateException2 {
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
       super(partyBean);
    }

    public void setPartyNameOut(String partyName) throws InputNullException {
       if ("".equals(partyName )){
           throw new InputNullException("Party Name");
       }
        this.name = partyName;
    }

    public void setPartyAddressOut(String partyAddress) throws InputNullException {
        if("".equals(partyAddress )){
            throw new InputNullException("Party Address");
        }
        this.address = partyAddress;
    }

    public void setImgOut(File partyImg) throws InputNullException {

        if(partyImg == null){
            throw  new InputNullException("Party Image");
        }
        this.image = partyImg;
    }

    public void setOrarioOut(String orario) throws InputNullException, InputDateException2 {
        if("".equals(orario)){
            throw new InputNullException("Orario");
        }
        try {
            Integer ore = Integer.valueOf(orario.substring(0, 2));
            Integer minuti = Integer.valueOf(orario.substring(3, 5));
            this.orario = LocalTime.of(Integer.valueOf(ore), Integer.valueOf(minuti));
        }catch (DateTimeException | NumberFormatException  d){
            throw new InputDateException2(orario,"Begin Time","hh:mm");

        }
    }

    public void setDateOut(String partyDate) throws  InputDateException2, InputNullException {
        try {
            if ("".equals(partyDate)){
                throw  new InputNullException("partyDate");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(partyDate, formatter);
            this.date = java.sql.Date.valueOf(localDate);
        }catch (DateTimeParseException d){
            throw new InputDateException2(  partyDate,"PartyDate"," DD/MM/YYYY ");
        }
    }

    public void setPartyDurationOut(Double partyDuration) throws InputNullException, InputDateException2 {

            if( partyDuration == null){
                throw new InputNullException("party Duration");
            }
            this.duration = new Time(HelperSetDuration.conv(partyDuration));

    }



    public String getPartyCreatorOut2() {return partyCreator;}
    public String getPartyNameOut2() {
        return name;
    }
    public String getPartyAddressOut2() {return address;}
    public String getPartyTimeOut2() {
        return String.valueOf(orario);
    }
    public String getPartyDateOut2() {return  String.valueOf(date);}
    public String getPartyDurationOut2() {return String.valueOf(duration);}
    public Image getPartyImgOut2() throws SystemException {return ImageConverter.convertFileToFxImage(image);}





}
