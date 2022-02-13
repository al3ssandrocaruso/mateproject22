package kapta.utils.pagesetter.setterjfx2;

import kapta.application.ClubProfileApplicationLayer;
import kapta.control.guicontroller.interfacetwo.JFX2ClubProfileGUIController;
import kapta.model.lists.CreatedEventList;
import kapta.model.lists.RequestList;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.beanout.jfx2.JFX2ClubBeanOut;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.listdao.RequestListDao;

import java.sql.SQLException;

public class JFX2ClubProfileSetter {
    public static void setter(ClubModel clubModel, JFX2ClubProfileGUIController clubProfileGUIController) {

        JFX2ClubBeanOut clubBeanOut= new JFX2ClubBeanOut(clubModel);
        try {
            new RequestList(clubModel, RequestListDao.getPendingRequestsByClubId(clubModel.getId()), clubProfileGUIController);
            CreatedEventList createdEventList =ClubDao.getCreatedEventsList(clubModel, clubProfileGUIController);
            ClubProfileApplicationLayer clubProfileApplication = new ClubProfileApplicationLayer(createdEventList);
            clubProfileGUIController.setAll(clubBeanOut, clubProfileApplication);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
