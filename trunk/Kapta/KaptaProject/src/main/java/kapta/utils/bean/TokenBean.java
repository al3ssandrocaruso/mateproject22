package kapta.utils.bean;

import kapta.model.TokenModel;

import java.time.LocalTime;

public class TokenBean {
    private String TokenString;
    private LocalTime localTime;


    public TokenBean(String TokenString, LocalTime localTime){
        setTokenString(TokenString);
        setLocalTime(localTime);
    }

    public void setTokenString(String tokenString) {
        TokenString = tokenString;
    }

    public String getTokenString() {
        return TokenString;
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
