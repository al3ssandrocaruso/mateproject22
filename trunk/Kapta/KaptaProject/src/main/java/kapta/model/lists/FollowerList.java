package kapta.model.lists;


import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.GenericObservableList;
import kapta.utils.Observer;
import java.util.ArrayList;
import java.util.List;

public class FollowerList extends GenericObservableList {

    private List<UserModel> follList = new ArrayList<>();
    private UserClubModel owner;

    public FollowerList(UserClubModel owner, List<UserModel> list, Observer obs){
        super(obs);
        this.owner=owner;
        for ( UserModel pm : list ){
            this.addUser(pm);
        }
    }
    public Integer getSize(){
        return this.follList.size();
    }

    public void addUser(UserModel pm) {
        this.follList.add(pm);
        this.notifyObservers(pm);
    }
    public void removeUser(UserModel pm) {
        for(UserModel um: this.follList){
            if(um.getId()==pm.getId()){
                this.follList.remove(um);
                notifyObservers(um);
            }
        }

    }

    public void setFollowerList(List<UserModel> followerList) {
        this.follList = followerList;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public List<UserModel> getFollowerList() {
        return follList;
    }

    public UserClubModel getOwner() {
        return owner;
    }
}
