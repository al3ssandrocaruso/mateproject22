package kapta.control.appcontroller;

import kapta.utils.bean.beanin.ClubSettingsBean;
import kapta.utils.bean.beanin.UserSettingsBean;
import kapta.utils.bean.beanin.SettingsBean;
import kapta.utils.dao.SettingDao;

public class SettingsPageController {

    private SettingsPageController(){
        //ignored
    }

    public static void saveSettings(SettingsBean settingsBean) {
        if(settingsBean instanceof UserSettingsBean userSettingBean){
            String username =  userSettingBean.getUsername();
            String name = userSettingBean.getName();
            String secondName = userSettingBean.getSecondName();
            String email = userSettingBean.getEmail();
            int id = userSettingBean.getId();
            SettingDao.setUser(username, name, secondName, email, id);
        }
        if(settingsBean instanceof ClubSettingsBean clubSettingBean){
            String username =  clubSettingBean.getUsername();
            String address = clubSettingBean.getAddress();
            String city = clubSettingBean.getCity();
            String email = clubSettingBean.getEmail();
            int id = clubSettingBean.getId();
            SettingDao.setClub(username, city, address, email, id);
        }
    }
}
