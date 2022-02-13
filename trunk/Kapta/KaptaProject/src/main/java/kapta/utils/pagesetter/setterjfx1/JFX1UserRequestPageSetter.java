package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.request.JFX1UserRequestPageGUIController;
import kapta.model.lists.RequestList;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.RequestListDao;

public class JFX1UserRequestPageSetter {

    private JFX1UserRequestPageSetter(){
        //ignore
    }

    public static void setter(JFX1UserRequestPageGUIController jfx1UserRequestPageGUIController, UserModel pageOwner) {

        new RequestList(pageOwner, RequestListDao.getAllTypeRequests(pageOwner) , jfx1UserRequestPageGUIController);

    }
}
