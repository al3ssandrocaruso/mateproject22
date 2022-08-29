package kapta.control.appcontroller;

import kapta.model.EventModel;
import kapta.model.PartyEventModel;
import kapta.model.PartyModel;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;
import kapta.utils.exception.myexception.SystemException;

public class DeletePartyEventController {

    private DeletePartyEventController(){
        //ignored
    }

    public static void delete(PartyEventModel partyEventModel) throws SystemException {
        if(partyEventModel instanceof EventModel eventModel) {
            //Controllo se l'ho creato io l'evento (e se esiste)
                EventDao.deleteEvent(eventModel);
            }
        if(partyEventModel instanceof PartyModel partyModel){
            //Controllo se l'ho creato io il party (e se esiste)
                PartyDao.removeParty(partyModel);

            }
        }
        public static void delete(PartyBean partyBean) throws SystemException{
            PartyModel pm = PartyDao.getPartyById(partyBean.getId());
            PartyDao.removeParty(pm);
        }

        public static void delete (EventBean eventBean) throws SystemException {
            EventModel eventModel = EventDao.getEventbyEventId(eventBean.getEventId());
            EventDao.deleteEvent(eventModel);


        }


    }
