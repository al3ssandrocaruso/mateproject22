package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.request.JFX1ClubRequestPageGUIController;
import kapta.model.lists.RequestList;
import kapta.model.profiles.ClubModel;
import kapta.utils.dao.listdao.RequestListDao;

public class JFX1ClubRequestPageSetter {

    private JFX1ClubRequestPageSetter(){
        //ignored
    }

    public static  void setter(ClubModel pageOwner, JFX1ClubRequestPageGUIController crpgc){
        new RequestList(pageOwner, RequestListDao.getPendingRequestsByClubId(pageOwner.getId()), crpgc);
    }
}
