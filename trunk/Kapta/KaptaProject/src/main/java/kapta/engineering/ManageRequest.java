package kapta.engineering;

import kapta.model.lists.RequestList;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.Observer;
import kapta.utils.bean.ClubBean;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.RequestListDao;
import kapta.utils.exception.myexception.SystemException;

public class ManageRequest {

    private ManageRequest(){

    }

    public static void setRequestListClub(ClubBean clubBean, Observer obs) throws SystemException {
        ClubModel cl = ClubDao.getClubByUserName(clubBean.getUsername());
        new RequestList(cl, RequestListDao.getPendingRequestsByClubId(cl.getId()), obs);
    }

    public static void setRequestListUser(UserBean userBean, Observer obs) throws SystemException {
        UserModel us = UserDao.getUserByUsername(userBean.getUsername());
        new RequestList(us, RequestListDao.getAllTypeRequests(us), obs);
    }



}
