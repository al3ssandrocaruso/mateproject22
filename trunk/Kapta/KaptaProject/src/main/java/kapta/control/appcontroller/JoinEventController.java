package kapta.control.appcontroller;

import kapta.model.EventModel;
import kapta.model.RequestModel;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.bean.RequestBean;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.JoinedListDAO;
import kapta.utils.dao.RequestDao;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.ExpiredGreenPassException;
import kapta.utils.session.ThreadLocalSession;

public class JoinEventController {

    private JoinEventController(){
        //ignore
    }

    public static void sendRequest(RequestBean requestBean, EventBean eventBean) throws ExpiredGreenPassException {
        //UserModel sender, EventModel event, int numDoses, String vaccinationDate
        if(requestBean.getDoses()>1 || !eventBean.isGreenPass()) {
            EventModel eventModel = EventDao.getEventbyEventId(eventBean.getEventId());
            UserModel userModel = UserDao.getUserById(ThreadLocalSession.getUserSession().get().getUserBean().getId());
            RequestModel toSend = new RequestModel(eventModel,  userModel, 0, eventModel.getEventCreator(), eventModel.isGreenPass(), requestBean.getVaccinationDate(), requestBean.getDoses());
            RequestDao.sendNewRequest(toSend);
        }else{
            Trigger.expiredGreenPass();
        }

    }

    public static void acceptRequest(RequestBean requestBean)  {

        UserModel userModel= UserDao.getUserByUsername(requestBean.getSender());
        ClubModel reciver = ClubDao.getClubByUserName(requestBean.getClubReceiver());
        EventModel eventModel= EventDao.getEventbyEventId(requestBean.getEventId());
        RequestDao.acceptRequest(eventModel,userModel,reciver);
        JoinedListDAO.addJoinedEvent(userModel,eventModel);
    }
    public static void rejectRequest(RequestBean requestBean)  {
        EventModel eventModel = EventDao.getEventbyEventId(requestBean.getEventId()) ;
        UserModel sender =UserDao.getUserByUsername(requestBean.getSender());
        ClubModel reciver = ClubDao.getClubByUserName(requestBean.getClubReceiver());
        RequestDao.rejectRequest(eventModel,sender,reciver);
    }

    public static void userDeleteRequest(RequestModel requestModel) {
            RequestDao.userDeleteRequest(requestModel.getEvent(), requestModel.getSender(), requestModel.getReceiver());

    }
    public static int manageRequestInfo(EventBean eventBean, UserBean userBean, GenericUserBean creator){
        EventModel eventModel = EventDao.getEventbyEventId(eventBean.getEventId());
        ClubModel clubModel = ClubDao.getClubByUserName(creator.getUsername());
        UserModel um = UserDao.getUserById(userBean.getId());
        int requestId= RequestDao.getRequestIdentifier(eventModel, um, clubModel);
        return  RequestDao.getRequestStatus(requestId);
    }



    }

