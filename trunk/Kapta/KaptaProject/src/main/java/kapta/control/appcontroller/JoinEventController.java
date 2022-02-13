package kapta.control.appcontroller;

import kapta.model.EventModel;
import kapta.model.RequestModel;
import kapta.utils.bean.beanin.RequestBean;
import kapta.utils.dao.listdao.JoinedListDAO;
import kapta.utils.dao.RequestDao;
import kapta.utils.exception.Trigger;
import kapta.utils.exception.myexception.ExpiredGreenPassException;

import static kapta.utils.session.ThreadLocalSession.userSession;

public class JoinEventController {
    //verifica se sono già stato rigettato in questo evento
    //check di greenpass(se evento richiede)
    //caso 1) evento NON ha greenpass -> modello classico, arriva richiesta a event manager
    //caso 2) evento HA il green pass -> viene richiesto caricamento greenpass
    //  -> Caricamento valido -> palla passa a event manager
    //  -> Caricamento non valido -> popup errore (da  gestire con eccezione, vedi commento su funzione)
    //in 2ndo caso creo l'item con factory

    private JoinEventController(){
        //ignore
    }

    public static void sendRequest(RequestBean requestBean, EventModel eventModel) throws ExpiredGreenPassException {
        //UserModel sender, EventModel event, int numDoses, String vaccinationDate
        if(requestBean.getNumDoses()>5) {
            RequestModel toSend = new RequestModel(eventModel, userSession.get().getUserModel(), 0, eventModel.getEventCreator(), eventModel.isGreenPass(), requestBean.getVaccinationDate(), requestBean.getNumDoses());
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

