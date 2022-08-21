package kapta.model.lists;

import kapta.model.PartyEventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.Observer;
import kapta.utils.Subject;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;

import java.util.ArrayList;
import java.util.List;

public class JoinedList extends Subject {

    private List<PartyEventModel> partyEventJoined = new ArrayList<>();
    private UserModel owner ;

    public JoinedList(UserModel owner, Observer obs ){
        super(obs);
        this.owner = owner ;
    }

    public JoinedList(UserModel owner , List<PartyEventModel> lista, Observer obs){
        super(obs);
        this.owner = owner;
        for ( PartyEventModel pm : lista ){
            this.addPartyEventModel(pm);
        }
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public void setPartyEventJoined(List<PartyEventModel> partyEventJoined) {
        this.partyEventJoined = partyEventJoined;
    }

    public List<PartyEventModel> getPartyEventJoined() {
        return partyEventJoined;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void addPartyEventModel(PartyEventModel partyEventModel){
        this.partyEventJoined.add(partyEventModel);
        if(partyEventModel.getType()==0){
            // eee can be better

            PartyBean partyBean = new PartyBean(PartyDao.getPartyById(partyEventModel.getId()));
            ///eee to tryy
            //PartyBean partyBean2 = new PartyBean((PartyModel)partyEventModel);
            this.notifyObservers(partyBean);
        }
        else if(partyEventModel.getType()==1){
            EventBean eventBean = new EventBean(EventDao.getEventbyEventId(partyEventModel.getId()));
            this.notifyObservers(eventBean);
            this.notifyObservers(partyEventModel);
        }
    }


}

