package kapta.control.appcontroller;

import kapta.model.EventModel;
import kapta.model.PartyEventModel;
import kapta.model.PartyModel;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;

public class DeletePartyEventController {

    private DeletePartyEventController(){
        //ignored
    }

    public static void delete(PartyEventModel partyEventModel) {
        if(partyEventModel instanceof EventModel eventModel) {
            //Controllo se l'ho creato io l'evento (e se esiste)
                EventDao.deleteEvent(eventModel);
            }
        if(partyEventModel instanceof PartyModel partyModel){
            //Controllo se l'ho creato io il party (e se esiste)
                PartyDao.removeParty(partyModel);

            }
        }
        public static void delete(PartyBean partyBean){
            PartyModel pm = PartyDao.getPartyById(partyBean.getId());
            PartyDao.removeParty(pm);
        }

        public static void delete (EventBean eventBean){
            EventModel eventModel = EventDao.getEventbyEventId(eventBean.getEventId());
            EventDao.deleteEvent(eventModel);


        }


    }
