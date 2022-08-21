package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.JFX1ClubProfileGUIcontroller;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.jfx1.JFX1ClubBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.GenericObservableList;

import java.sql.SQLException;

public class JFX1ClubProfileSetter {

    private JFX1ClubProfileSetter(){
        //ignore
    }



    public static  void setter(JFX1ClubBean clubBean, JFX1ClubProfileGUIcontroller jfx1ClubProfileGUIController){
        try {
            jfx1ClubProfileGUIController.setAll(clubBean);
            ClubModel cm =ClubDao.getClubByUserName(clubBean.getUsername());
            ClubDao.getCreatedEventsList(cm, jfx1ClubProfileGUIController);
            new GenericObservableList(jfx1ClubProfileGUIController);
        }
       catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
