package kapta.utils.pagesetter.setterjfx2;

import kapta.control.guicontroller.interfacetwo.JFX2UserRequestPageGUIController;
import kapta.model.lists.RequestList;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.RequestListDao;

public class JFX2RequestPageSetter {
    public static void setter(UserModel userModel, JFX2UserRequestPageGUIController jfx2UserRequestPageGUIController) {
        new RequestList(userModel, RequestListDao.getAllTypeRequests(userModel),jfx2UserRequestPageGUIController);

    }
}
