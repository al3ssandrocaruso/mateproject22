package kapta.utils.dao;
import kapta.model.EventModel;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.InfoEvent;
import kapta.utils.bean.PartyEventSchedule;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.exception.*;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.mysession.ThreadLocalSession;
import kapta.utils.utils.ImageConverter;
import kapta.utils.utils.MysqlConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


public class EventDao {

    private EventDao(){
        //ignored
    }

    public static void saveNewEvent(EventModel eventModel) throws SystemException {
        String eventName = eventModel.getName();
        String eventAddress = eventModel.getAddress();

        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();

            int creatorId =  ThreadLocalSession.getUserSession().get().getClubBean().getId();

            int obbG;
            if (eventModel.isGreenPass()) {
                obbG = 1;
            } else {
                obbG = 0;
            }

            Double price = eventModel.getEventPrice();
            new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
            InfoEvent infoEvent = new InfoEvent(eventAddress, price, obbG);
            // check pre esisstent e???
            CRUD.saveNewEvent(stm, eventModel.getPartyEventSchedule(), eventName, infoEvent, creatorId, eventModel.getImg());


        } catch (MysqlConnectionFailed | SQLException | FileNotFoundException e) {
            ErrorHandler.getInstance().handleException(e);
        }
    }


    public static List<EventModel> getEventsbyEventName(String input) throws SystemException {
        Statement stm = null;
        List<EventModel> list = new ArrayList<>();
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rst = Query.askEventbyEventName(stm, input);
            assert rst != null;
            if (!rst.next()) {
                return list;
            }
            rst.first();
            Date date = rst.getDate(7);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            dateFormat.format(date);
            EventModel eM = new EventModel(rst.getInt(1));
            eM.setName(rst.getString(6));
            eM.setEventPrice(rst.getDouble(5));
            eM.setEventCreator(ClubDao.clubModelByID(rst.getInt(2)));
            eM.setGreenPass((rst.getBoolean(4)));
            eM.setName(rst.getString(6));
            PartyEventSchedule partyEventSchedule = new PartyEventSchedule(rst.getDate(7),rst.getTime(9), rst.getObject(12, LocalTime.class) );
            eM.setPartyEventSchedule(partyEventSchedule);
            eM.setStatus(rst.getInt(13));
            InputStream in = (rst.getBinaryStream(11));
            String filePath = eM.getName() + "pic" + ".png";
            File file = new File(filePath);
            ImageConverter.copyInputStreamToFile(in, file);
            eM.setImg(file);
            list.add(eM);
    } catch (MysqlConnectionFailed |SQLException e) {
            ErrorHandler.getInstance().handleException(e);
        }
        return list;
    }


    public static EventModel getEventbyEventId(int id) throws SystemException {
        EventModel eM = null;
        try {
            Statement stm = MysqlConnection.mysqlConnection();
            ResultSet rst = Query.askEventbyEventId(id, stm);
            if (!rst.next()) {
                return null;
            } else {
                rst.first();
                new SimpleDateFormat("MM/dd/yyyy");

                Date date = rst.getDate(7);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                dateFormat.format(date);
                eM = new EventModel(rst.getInt(1));
                ClubModel owner = ClubDao.clubModelByID(rst.getInt(2));
                eM.setEventPrice(rst.getDouble(5));
                eM.setName(rst.getString(6));
                eM.setGreenPass(rst.getBoolean(4));
                eM.setEventCreator(owner);
                PartyEventSchedule partyEventSchedule = new PartyEventSchedule(rst.getDate(7),rst.getTime(9), rst.getObject(12, LocalTime.class) );
                eM.setPartyEventSchedule(partyEventSchedule);
                eM.setStatus(rst.getInt(13));
                InputStream in = (rst.getBinaryStream(11));
                String filePath = eM.getName() + "pic" + ".png";
                File file = new File(filePath);
                ImageConverter.copyInputStreamToFile(in, file);
                eM.setImg(file);

            }
        } catch (MysqlConnectionFailed | SQLException e) {
            ErrorHandler.getInstance().handleException(e);
        }
        return eM;
    }

    public static int getIdByEventName(String eventName) throws SystemException {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            ResultSet rst = Query.askEventbyEventName(stm, eventName);
            assert rst != null;
            if (!rst.next()) {
                return -1;

            }
            rst.first();
            return rst.getInt(1);
        } catch (MysqlConnectionFailed |SQLException e) {
            ErrorHandler.getInstance().handleException(e);
        }
        return -1;
    }

    public static void deleteEvent(EventModel eventModel) throws SystemException {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.deleteEvent(stm, eventModel.getId());
        } catch (MysqlConnectionFailed | SQLException e) {
            ErrorHandler.getInstance().handleException(e);
        }
    }
}
