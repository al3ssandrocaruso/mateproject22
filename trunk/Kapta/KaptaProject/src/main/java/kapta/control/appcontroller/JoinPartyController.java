package kapta.control.appcontroller;

import kapta.model.lists.JoinedList;
import kapta.model.lists.ParticipantsList;
import kapta.model.PartyEventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.JoinedListDAO;
import kapta.utils.dao.listdao.ParticipantListDao;
import kapta.utils.email.SendEmail;
import kapta.utils.utils.FollowUtils;

import java.time.LocalDate;
import java.util.Date;

import static kapta.utils.session.ThreadLocalSession.userSession;


public class JoinPartyController {

    private JoinPartyController(){
        //ignored
    }

    public static int joinParty(UserModel participantToAdd, PartyModel partyModel) {
        JoinedList joinedList=JoinedListDAO.getJoined(participantToAdd,null);

        //User already busy
        for(PartyEventModel pm : joinedList.getPartyEventJoined()){
            if(pm.getDate().toString().equals(partyModel.getDate().toString())){
                return -1;
            }
        }

        //Party expired
        if(partyModel.getDate() != null) {
            Date dt = partyModel.getDate();
            LocalDate today = LocalDate.now();
            java.sql.Date tdy = java.sql.Date.valueOf(today);

            if (dt.compareTo(tdy) < 0) {
                return -2;
            }
        }

        String toWrite="The user you follow '"+userSession.get().getUserModel().getUsername()+"' has joined party: "+partyModel.getName()+ " too!";
        ParticipantsList participantList=ParticipantListDao.getParticipantList(partyModel,null);

        //Send Email to my followers
        for(UserModel um: participantList.getParticipants()){
            if(FollowUtils.doAFollowB(um, userSession.get().getUserModel())){
                SendEmail.send(um.getEmail(),"A friend to go with!",toWrite);
            }
        }

        JoinedListDAO.addJoinedParty(participantToAdd, partyModel);

        return 0;
    }

    public static void leaveParty(UserModel participantToRemove, PartyModel partyModel){
        JoinedListDAO.removeJoinedParty(participantToRemove, partyModel);
    }

}
