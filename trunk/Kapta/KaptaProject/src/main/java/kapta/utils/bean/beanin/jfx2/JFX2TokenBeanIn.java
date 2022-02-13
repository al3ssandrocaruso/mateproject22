package kapta.utils.bean.beanin.jfx2;

import kapta.utils.bean.beanin.TokenBeanIn;

public class JFX2TokenBeanIn extends TokenBeanIn {
    public JFX2TokenBeanIn(String inputToken){
        this.setToken(inputToken);
    }

    public void setToken(String inputToken){
        super.token=inputToken;
    }
}


