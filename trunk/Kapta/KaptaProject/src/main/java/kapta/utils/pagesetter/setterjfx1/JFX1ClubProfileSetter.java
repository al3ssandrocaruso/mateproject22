package kapta.utils.pagesetter.setterjfx1;

import kapta.application.ClubProfileApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1ClubProfileGUIcontroller;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.beanout.jfx1.JFX1ClubBeanOut;
import kapta.utils.dao.ClubDao;
import kapta.utils.GenericObservableList;
import java.sql.SQLException;

public class JFX1ClubProfileSetter {

    private JFX1ClubProfileSetter(){
        //ignore
    }

    public static  void setter(ClubModel clubModel, JFX1ClubProfileGUIcontroller jfx1ClubProfileGUIController){
        try {
            JFX1ClubBeanOut clubBean = new JFX1ClubBeanOut(clubModel, ClubDao.getCreatedEventsList(clubModel, null));
            ClubProfileApplicationLayer clubProfileApplication = new ClubProfileApplicationLayer(clubModel);
            jfx1ClubProfileGUIController.setAll(clubBean, clubProfileApplication);
            ClubDao.getCreatedEventsList(clubModel, jfx1ClubProfileGUIController);
            new GenericObservableList(jfx1ClubProfileGUIController);
        }
       catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
