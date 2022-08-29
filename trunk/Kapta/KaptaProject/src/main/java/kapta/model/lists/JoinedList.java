package kapta.model.lists;

import kapta.model.PartyEventModel;
import kapta.model.profiles.UserModel;
import kapta.utils.Observer;
import kapta.utils.Subject;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;
import kapta.utils.exception.myexception.SystemException;

import java.util.ArrayList;
import java.util.List;

public class JoinedList extends Subject {

    private List<PartyEventModel> partyEventJoined = new ArrayList<>();
    private UserModel owner ;

    public JoinedList(UserModel owner, Observer obs ){
        super(obs);
        this.owner = owner ;
    }

    public JoinedList(UserModel owner , List<PartyEventModel> lista, Observer obs) throws SystemException {
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

    public void addPartyEventModel(PartyEventModel partyEventModel) throws SystemException {
        this.partyEventJoined.add(partyEventModel);
        if(partyEventModel.getType()==0){
            // eee can be better
            PartyBean partyBean = new PartyBean(PartyDao.getPartyById(partyEventModel.getId()));
            this.notifyObservers(partyBean);
        }
        else if(partyEventModel.getType()==1){
            // eee the same
            EventBean eventBean = new EventBean(EventDao.getEventbyEventId(partyEventModel.getId()));
            this.notifyObservers(eventBean);
            this.notifyObservers(partyEventModel);
        }
    }


}

