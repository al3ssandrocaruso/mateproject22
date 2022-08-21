package kapta.utils.bean.J2;

import kapta.utils.bean.TokenBean;

import java.time.LocalTime;

public class JFX2TokenBean extends TokenBean {
    public JFX2TokenBean(String inputToken, LocalTime localTime){
       super(inputToken, localTime);
    }
    public JFX2TokenBean(String inputToken){
        super(inputToken, LocalTime.now());
    }



}
