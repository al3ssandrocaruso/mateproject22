package kapta.utils.session;

import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;

public class Session {

    // 0 per user
    // 1 per Club

    private int type;
    private int id;
    private UserModel eventModel;
    private ClubModel clubModel;


    // Costruttore
    public Session(UserModel userModel, int type) {
        this.setType(type);
        this.setUserModel(userModel);
        this.setId(userModel.getId());

    }

    public Session(ClubModel clubModel, int type) {
        this.setType(type);
        this.setClubModel(clubModel);
        this.setId(clubModel.getId());
    }

    //Set e Get
    public int getType() {
        return type;
    }
    public void setType(int type){this.type = type;}
    public UserModel getUserModel() {
        return eventModel;
    }
    public void setUserModel(UserModel eventModel) {
        this.eventModel = eventModel;
    }
    public ClubModel getClubModel() {
        return clubModel;
    }
    public void setClubModel(ClubModel clubModel) {
        this.clubModel = clubModel;
    }
    public void setId(int  id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
