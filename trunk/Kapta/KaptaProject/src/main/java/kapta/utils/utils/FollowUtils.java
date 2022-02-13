package kapta.utils.utils;

import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.FollowingListDao;
import java.util.List;

public class FollowUtils {

    private FollowUtils(){
        //ignore
    }

    public static boolean doAFollowB(UserModel userA,UserModel userB)  {
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
