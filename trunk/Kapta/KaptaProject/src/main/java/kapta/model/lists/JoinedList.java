package kapta.model.lists;

import kapta.model.PartyEventModel;
import kapta.model.profiles.UserModel;
import kapta.utils.Observer;
import kapta.utils.Subject;
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
        this.notifyObservers(partyEventModel);
    }


}

