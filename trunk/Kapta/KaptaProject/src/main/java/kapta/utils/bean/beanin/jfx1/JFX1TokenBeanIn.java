package kapta.utils.bean.beanin.jfx1;

import kapta.utils.bean.beanin.TokenBeanIn;

public class JFX1TokenBeanIn extends TokenBeanIn {
    public JFX1TokenBeanIn(String inputToken){
        this.setToken(inputToken);
    }

    public void setToken(String inputToken){
        super.token=inputToken;
    }
}
