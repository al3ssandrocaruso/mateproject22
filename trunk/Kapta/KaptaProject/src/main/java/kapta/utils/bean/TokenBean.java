package kapta.utils.bean;

import kapta.model.TokenModel;

import java.time.LocalTime;

public class TokenBean {
    private String tokenString;
    private LocalTime localTime;


    public TokenBean(String TokenString, LocalTime localTime){
        setTokenString(TokenString);
        setLocalTime(localTime);
    }

    public void setTokenString(String tokenSt) {
        tokenString = tokenSt;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
    public TokenBean(TokenModel tokenModel){
        setLocalTime(LocalTime.from(tokenModel.getGenerationTime()));
        setTokenString(tokenModel.getToken());
    }

    public LocalTime getLocalTime() {
        return localTime;
    }
}
