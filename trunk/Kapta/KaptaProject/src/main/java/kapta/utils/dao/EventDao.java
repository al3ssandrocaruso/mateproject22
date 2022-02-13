package kapta.utils.dao;
import kapta.model.EventModel;
import kapta.model.profiles.ClubModel;
import kapta.utils.db.CRUD;
import kapta.utils.db.Query;
import kapta.utils.exception.*;
import kapta.utils.exception.myexception.MysqlConnectionFailed;
import kapta.utils.exception.myexception.WrongCrudException;
import kapta.utils.exception.myexception.WrongQueryException;
import kapta.utils.utils.ImageConverter;
import kapta.utils.utils.MysqlConnection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import static kapta.utils.session.ThreadLocalSession.userSession;

public class EventDao {

    private EventDao(){
        //ignored
    }

    public static void saveNewEvent(EventModel eventModel) {
        String eventName = eventModel.getName();
        LocalTime eventTime = eventModel.getOrario();
        Time eventDuration = eventModel.getDuration();
        String eventAddress = eventModel.getAddress();
        Date eventPartyDate = eventModel.getDate();

        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();

            int creatorId = userSession.get().getClubModel().getId();

            int obbG;
            if (eventModel.isGreenPass()) {
                obbG = 1;
            } else {
                obbG = 0;
            }

            Double price = eventModel.getEventPrice();
            Time sqlOrarioEvento = Time.valueOf(eventTime);
            java.sql.Date sqlEventDate = new java.sql.Date(eventPartyDate.getTime());
            new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            java.sql.Date sqlCreationDate = new java.sql.Date(date.getTime());
            CRUD.saveNewEvent(stm, eventName, sqlOrarioEvento, eventDuration, eventAddress, creatorId, obbG, sqlEventDate, sqlCreationDate, 0, price, eventModel.getImg());
        } catch (MysqlConnectionFailed | WrongCrudException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        }
    }


    public static List<EventModel> getEventsbyEventName(String input) {
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
            eM.setDate(rst.getDate(7));
            eM.setStatus(rst.getInt(13));
            InputStream in = (rst.getBinaryStream(11));
            String filePath = eM.getName() + "pic" + ".png";
            File file = new File(filePath);
            ImageConverter.copyInputStreamToFile(in, file);
            eM.setImg(file);
            list.add(eM);
        } catch (MysqlConnectionFailed | WrongQueryException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException | IOException t) {
            // non gestite
        }
        return list;
    }


    public static EventModel getEventbyEventId(int id) {
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
                eM.setOrario(rst.getObject(12, LocalTime.class));
                eM.setDuration(rst.getTime(9));
                eM.setEventCreator(owner);
                eM.setDate(rst.getDate(7));
                eM.setStatus(rst.getInt(13));
                InputStream in = (rst.getBinaryStream(11));
                String filePath = eM.getName() + "pic" + ".png";
                File file = new File(filePath);
                ImageConverter.copyInputStreamToFile(in, file);
                eM.setImg(file);

            }
        } catch (MysqlConnectionFailed | WrongQueryException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException | IOException throwables) {
            // non getsita
        }
        return eM;
    }

    public static int getIdByEventName(String eventName) {
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
        } catch (MysqlConnectionFailed | WrongQueryException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        } catch (SQLException throwables) {
            //ignored
        }
        return -1;
    }

    public static void deleteEvent(EventModel eventModel) {
        Statement stm = null;
        try {
            stm = MysqlConnection.mysqlConnection();
            CRUD.deleteEvent(stm, eventModel.getId());
        } catch (MysqlConnectionFailed | WrongCrudException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        }
    }
}
