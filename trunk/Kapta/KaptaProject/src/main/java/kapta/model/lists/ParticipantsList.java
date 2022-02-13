package kapta.model.lists;

import kapta.model.PartyEventModel;
import kapta.model.profiles.UserModel;
import kapta.utils.Observer;
import kapta.utils.Subject;

import java.util.ArrayList;
import java.util.List;

// questa lista indica i partecipanti ad un certo party o evento ==> infatti la lista è la stessa sia nel caso si tratti di un evento che nel caso di un party.


public class ParticipantsList extends Subject {

    private List<UserModel> participants;
    private PartyEventModel partyEvent;

    public ParticipantsList(PartyEventModel partyEvent){
        super((Observer) null);
        this.partyEvent=partyEvent;
        this.participants = new ArrayList<>();
    }
    public ParticipantsList(PartyEventModel partyEventModel, List<UserModel> list, Observer obs){
        super(obs);
        this.partyEvent=partyEventModel;
        this.participants = new ArrayList<>();
        for ( UserModel pm : list ){
            this.addParticipant(pm);
        }
    }
    public ParticipantsList(PartyEventModel partyEventModel, Observer obs){
        super(obs);
        this.partyEvent=partyEventModel;
        this.participants = new ArrayList<>();
    }

    public void setParty(PartyEventModel partyEvent) {
        this.partyEvent = partyEvent;
    }

    public void setParticipants(List<UserModel> participants) {
        this.participants = participants;
    }

    public PartyEventModel getPartyEvent() {
        return partyEvent;
    }

    public List<UserModel> getParticipants() {
        return participants;
    }

    public void addParticipant(UserModel p){
        this.participants.add(p);
        this.notifyObservers(p);
    }

}