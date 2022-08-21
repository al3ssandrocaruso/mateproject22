package kapta.control.appcontroller;

import kapta.model.lists.JoinedList;
import kapta.model.lists.ParticipantsList;
import kapta.model.PartyEventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.JoinedListDAO;
import kapta.utils.dao.listdao.ParticipantListDao;
import kapta.utils.email.SendEmail;
import kapta.utils.session.ThreadLocalSession;
import kapta.utils.utils.FollowUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class JoinPartyController {

    private JoinPartyController(){
        //ignored
    }

    // eee
    public static int joinParty(GenericUserBean participantToAddBean, PartyBean partyBean, ParticipantsList pl) {
        pl.addParticipant(UserDao.getUserById(participantToAddBean.getId()));
       return  joinParty(participantToAddBean, partyBean);
    }


    public static int joinParty(GenericUserBean participantToAddBean, PartyBean partyBean) {

        PartyModel  partyModel = PartyDao.getPartyById(partyBean.getId());
        UserModel participantToAdd = UserDao.getUserById(participantToAddBean.getId()) ;

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

        String toWrite="The user you follow '"+ ThreadLocalSession.getUserSession().get().getUserBean().getUsername()+"' has joined party: "+partyModel.getName()+ " too!";
        ParticipantsList participantList=ParticipantListDao.getParticipantList(partyModel,null);

        //Send Email to my followers
        for(UserModel um: participantList.getParticipants()){
            //eee
            UserBean userBean = new UserBean(um);
            if(FollowUtils.doAFollowB(userBean,  ThreadLocalSession.getUserSession().get().getUserBean())){
                SendEmail.send(um.getEmail(),"A friend to go with!",toWrite);
            }
        }

        JoinedListDAO.addJoinedParty(participantToAdd, partyModel);

        return 0;
    }

    public static void leaveParty(GenericUserBean userBean, PartyBean partyBean){
        PartyModel partyModel = PartyDao.getPartyById(partyBean.getId());
        UserModel participantToRemove = UserDao.getUserById(userBean.getId());
        JoinedListDAO.removeJoinedParty(participantToRemove, partyModel);
    }

    public static boolean joinedYetInfo(PartyBean partyBean, GenericUserBean userBean){

        boolean state = false;
        PartyModel partyModel = PartyDao.getPartyById(partyBean.getId());
        List<UserModel> list = ParticipantListDao.getParticipantList(partyModel, null).getParticipants();
        for(UserModel um : list) {
            if ( userBean.getId() == um.getId()) {
                state = true;
            }
        }
        return state;

    }


}
