package kapta.control.appcontroller;

import kapta.model.EventModel;
import kapta.model.TokenModel;
import kapta.utils.bean.beanin.EventBean;
import kapta.utils.bean.beanin.PartyEventSchedule;
import kapta.utils.dao.EventDao;
import kapta.model.profiles.ClubModel;
import kapta.utils.email.SendEmail;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.TokenException;
import kapta.utils.session.ThreadLocalSession;
import kapta.utils.utils.GenerateNewToken;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateEventController {
    private static final int SECOND_TOLERANCE = 120; //how many second for token.

    private CreateEventController(){
        //ignore
    }

    public static EventModel createEvent(EventBean eventBean)  {

        ClubModel clubModel= ThreadLocalSession.getUserSession().get().getClubModel();

        PartyEventSchedule partyEventSchedule = new PartyEventSchedule(eventBean.getEventDate(), eventBean.getEventDuration(), eventBean.getEventOrario());
        EventModel eventModel=new EventModel(eventBean.getEventName(), eventBean.getEventPrice(), 0, eventBean.getEventAddress(), partyEventSchedule,  eventBean.isGreenPass(), eventBean.getEventImg());
            EventDao.saveNewEvent(eventModel);
            String toWrite="Nome: "+eventModel.getName()+"\nData: "+eventModel.getDate()+"\nDuration: "+eventModel.getDuration()+"\nHour: "+eventModel.getOrario()+"\nPrice: "+eventModel.getEventPrice()+"$";
            SendEmail.send(clubModel.getEmail(),"Evento creato con successo!",toWrite);
            return eventModel;
        }


    public static TokenModel generateToken(){
        String randomToken= GenerateNewToken.generateToken();
        SendEmail.send( ThreadLocalSession.getUserSession().get().getClubModel().getEmail(),"Mate: we need to verify you! ","Here the token to confirm your event creation: "+randomToken);
        return new TokenModel(randomToken);
    }

    public static void checkToken(TokenModel token1, TokenModel token2) throws TokenException {
        if(!timeGenerationValidation(token1.getGenerationTime(),token2.getGenerationTime()) || !token1.getToken().equals(token2.getToken())){
            ErrorHandler.getInstance().tokenException(token2.getToken());
        }
    }
    public static boolean timeGenerationValidation(LocalDateTime first, LocalDateTime second) {
        boolean ret = false;
        long diff = ChronoUnit.SECONDS.between(first, second);
        if(diff<=SECOND_TOLERANCE){
            ret = true;
        }
        return ret;
    }
}
