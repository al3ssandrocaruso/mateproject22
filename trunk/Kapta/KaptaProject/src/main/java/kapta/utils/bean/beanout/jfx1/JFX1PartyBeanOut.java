package kapta.utils.bean.beanout.jfx1;

import javafx.scene.image.Image;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class JFX1PartyBeanOut {

    private String partyCreator;
    private String partyName;
    private String partyAddress;
    private String partyTime;
    private String partyDate;
    private String partyDuration;
    private String partyId;
    private Image partyImg;
    private List<UserModel> participantList;

    public JFX1PartyBeanOut(PartyModel partyModel) {
        this.setPartyCreator(partyModel.getPartyCreator());
        this.setPartyName(partyModel.getName());
        this.setPartyAddress(partyModel.getAddress());
        this.setPartyTime(partyModel.getOrario());
        this.setPartyDate(partyModel.getDate());
        this.setPartyDuration(partyModel.getDuration());
        this.setPartyId(partyModel.getId());
        this.setPartyImg(partyModel.getImg());
    }

    public String getPartyCreator() {
        return partyCreator;
    }
    public void setPartyCreator(UserModel partyCreator) {
        this.partyCreator = partyCreator.getUsername();
    }
    public String getPartyName() {
        return partyName;
    }
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
    public String getPartyAddress() {
        return partyAddress;
    }
    public void setPartyAddress(String partyAddress) {
        this.partyAddress = partyAddress;
    }
    public String getPartyTime() {
        return partyTime;
    }
    public void setPartyTime(LocalTime partyTime) {
        this.partyTime = String.valueOf(partyTime);
    }
    public String getPartyDate() {
        return partyDate;
    }
    public void setPartyDate(Date partyDate) {
        String helper = String.valueOf(partyDate);
        this.partyDate = helper;
    }
    public String getPartyDuration() {
        return partyDuration;
    }
    public void setPartyDuration(Time partyDuration) {
        this.partyDuration = String.valueOf(partyDuration);
    }
    public Image getPartyImg() {
        return partyImg;
    }
    public void setPartyImg(File partyImg) {
        this.partyImg =ImageConverter.convertFileToFxImage(partyImg);
    }
    public String getPartyId() {
        return partyId;
    }
    public void setPartyId(int partyId) {
        this.partyId = String.valueOf(partyId);
    }
    public List<UserModel> getParticipantList() {
        return participantList;
    }
    public void setParticipantList(List<UserModel> participantList) {
        this.participantList = participantList;
    }
}
