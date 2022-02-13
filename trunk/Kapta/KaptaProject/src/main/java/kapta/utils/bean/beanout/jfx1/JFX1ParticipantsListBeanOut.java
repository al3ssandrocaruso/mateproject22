package kapta.utils.bean.beanout.jfx1;

import kapta.model.lists.ParticipantsList;
import kapta.model.profiles.UserModel;

import java.util.ArrayList;
import java.util.List;

public class JFX1ParticipantsListBeanOut {

    private List<UserModel> participantsList = new ArrayList<>();

    public JFX1ParticipantsListBeanOut(ParticipantsList pl) {
        setParticipantsList(pl);

    }
    public List<UserModel> getParticipantsList() {
        return participantsList;
    }

    public void setParticipantsList(ParticipantsList pl) {
        this.participantsList = pl.getParticipants();
    }


}
