package kapta.utils.pagesetter.setterjfx2;


import kapta.control.guicontroller.interfacetwo.JFX2ClubProfileGUIController;
import kapta.model.lists.RequestList;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.jfx2.JFX2ClubBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.listdao.RequestListDao;

import java.sql.SQLException;

public class JFX2ClubProfileSetter {

    private JFX2ClubProfileSetter(){
        //ignore
    }

    public static void setter(JFX2ClubBean jfx2ClubBean, JFX2ClubProfileGUIController clubProfileGUIController) {
        try {

            ClubModel clubModel = ClubDao.getClubByUserName(jfx2ClubBean.getUsername());
            new RequestList(clubModel, RequestListDao.getPendingRequestsByClubId(clubModel.getId()), clubProfileGUIController);
            ClubDao.getCreatedEventsList(clubModel, clubProfileGUIController);
            clubProfileGUIController.setAll(jfx2ClubBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
