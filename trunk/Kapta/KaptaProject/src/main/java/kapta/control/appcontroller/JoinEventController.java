package kapta.control.appcontroller;

import kapta.model.EventModel;
import kapta.model.RequestModel;
import kapta.utils.bean.beanin.RequestBean;
import kapta.utils.dao.listdao.JoinedListDAO;
import kapta.utils.dao.RequestDao;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.ExpiredGreenPassException;
import kapta.utils.session.ThreadLocalSession;

public class JoinEventController {

    private JoinEventController(){
        //ignore
    }

    public static void sendRequest(RequestBean requestBean, EventModel eventModel) throws ExpiredGreenPassException {
        //UserModel sender, EventModel event, int numDoses, String vaccinationDate
        if(requestBean.getNumDoses()>1 || !eventModel.isGreenPass()) {
            RequestModel toSend = new RequestModel(eventModel,  ThreadLocalSession.getUserSession().get().getUserModel(), 0, eventModel.getEventCreator(), eventModel.isGreenPass(), requestBean.getVaccinationDate(), requestBean.getNumDoses());
            RequestDao.sendNewRequest(toSend);
        }else{
            Trigger.expiredGreenPass();
        }

    }

    public static void acceptRequest(RequestModel requestModel)  {
        //mando email di avvenuta conferma a utente
            RequestDao.acceptRequest(requestModel.getEvent(),requestModel.getSender(),requestModel.getReceiver());
            JoinedListDAO.addJoinedEvent(requestModel.getSender(),requestModel.getEvent());
    }
    public static void rejectRequest(RequestModel requestModel)  {
        RequestDao.rejectRequest(requestModel.getEvent(),requestModel.getSender(),requestModel.getReceiver());
    }

    public static void userDeleteRequest(RequestModel requestModel) {
            RequestDao.userDeleteRequest(requestModel.getEvent(), requestModel.getSender(), requestModel.getReceiver());

        }
    }

