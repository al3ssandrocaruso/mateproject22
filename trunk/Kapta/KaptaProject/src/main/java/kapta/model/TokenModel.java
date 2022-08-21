package kapta.model;

import kapta.utils.bean.TokenBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TokenModel {
    private String token;
    private LocalDateTime generationTime;

    public String getToken() {
        return token;
    }
    public LocalDateTime getGenerationTime(){return generationTime;}

    public void setToken(String token) {
        this.token = token;
    }

    public void setGenerationTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.generationTime = LocalDateTime.now();
        dtf.format(this.generationTime);
    }

    public void setGenerationTime(LocalDateTime generationTime) {
        this.generationTime = generationTime;
    }

    public TokenModel(String token) {
        this.setGenerationTime();
        this.setToken(token);
    }
    public TokenModel(TokenBean token) {
        this.setGenerationTime(LocalDateTime.from(token.getLocalTime()));
        this.setToken(token.getTokenString());
    }

}
