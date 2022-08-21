package kapta.utils.bean.J1;

import kapta.utils.bean.TokenBean;

import java.time.LocalTime;

public class JFX1TokenBean extends TokenBean {
    public JFX1TokenBean(String inputToken, LocalTime localTime){
        super(inputToken,localTime);
    }
    public JFX1TokenBean(String inputToken){
        super(inputToken,LocalTime.now());
    }
}
