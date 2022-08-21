package kapta.utils.utils;

import kapta.model.profiles.UserModel;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.FollowingListDao;
import java.util.List;

public class FollowUtils {

    private FollowUtils(){
        //ignore
    }

    public static boolean doAFollowB(UserBean userBA, UserBean userBB)  {

        UserModel userA = UserDao.getUserByUsername(userBA.getUsername());
        UserModel userB= UserDao.getUserByUsername(userBB.getUsername());
            boolean followState=false;
            List<UserModel> list= FollowingListDao.getFollowing(userA);
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
