package kapta.utils.utils;

import kapta.model.profiles.UserModel;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.exception.myexception.SystemException;

import java.util.List;

public class FollowUtils {

    private FollowUtils(){
        //ignore
    }

    public static boolean doAFollowB(UserBean userBA, UserBean userBB)  {

        UserModel userA = null;
        UserModel userB = null;
        List<UserModel> list = null;
        try {
            userA = UserDao.getUserByUsername(userBA.getUsername());
             userB= UserDao.getUserByUsername(userBB.getUsername());
             list= FollowingListDao.getFollowing(userA);
        } catch (SystemException e) {
            //non gestitat
            e.printStackTrace();
        }

            boolean followState=false;

            //get di CHI SEGUO IO
            if(list == null){
                return followState;
            }else{
                for (UserModel elem : list) {

                    if (userB.getId() == elem.getId()) {
                        followState = true;
                        break;
                    }
                }

            }
            return followState;
        }
    }
