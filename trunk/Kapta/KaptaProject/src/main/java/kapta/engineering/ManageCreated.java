package kapta.engineering;

import kapta.utils.Observer;
import kapta.utils.bean.ClubBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.exception.myexception.SystemException;


public class ManageCreated {

    private ManageCreated(){

    }

    public static void manageCreated (ClubBean  clubBean, Observer obs) throws SystemException {
        ClubDao.getCreatedEventsList(ClubDao.getClubByUserName(clubBean.getUsername()), obs);
    }


}
