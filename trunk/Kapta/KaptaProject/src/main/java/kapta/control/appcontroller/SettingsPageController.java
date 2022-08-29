package kapta.control.appcontroller;

import kapta.utils.bean.GenericUserBean;
import kapta.utils.dao.SettingDao;
import kapta.utils.exception.myexception.SystemException;

public class SettingsPageController {

    private SettingsPageController(){
        //ignored
    }

    public static void saveSettings(GenericUserBean bean) throws SystemException {
        if(bean.getType()   == 0 ){
            String username =  bean.getUsername();
            String name = bean.getName();
            String secondName = bean.getSecondName();
            String email = bean.getEmail();
            int id = bean.getId();
            SettingDao.setUser(username, name, secondName, email, id);
        }
        else{
            String username =  bean.getUsername();
            String address = bean.getAddress();
            String city = bean.getCity();
            String email = bean.getEmail();
            int id = bean.getId();
            SettingDao.setClub(username, city, address, email, id);
        }
    }
}
