package kapta.model.lists;

import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.GenericObservableList;
import kapta.utils.Observer;
import kapta.utils.bean.GenericListInfoBean;
import kapta.utils.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class FollowingList extends GenericObservableList {

    private List<UserModel> follList =new ArrayList<>();
    private UserClubModel owner;

    public FollowingList(UserClubModel owner, List<UserModel> list, Observer obs){
        super(obs);
        this.owner=owner;
        for ( UserModel pm : list ){
            this.addParticipant(pm);
        }
    }
    public void removeParticipant(UserModel pm) {
        for(UserModel um: this.follList){
            if(um.getId()==pm.getId()){
                this.follList.remove(um);
                UserBean ub = new UserBean(pm);
                GenericListInfoBean g = new GenericListInfoBean(follList.size(), 0);
                this.notifyObservers(ub,g);
            }
        }

    }
    public Integer getSize(){
        return this.follList.size();
    }

    public void addParticipant(UserModel pm) {
        this.follList.add(pm);
        UserBean ub = new UserBean(pm);
        GenericListInfoBean g = new GenericListInfoBean(follList.size(), 0);
        this.notifyObservers(ub,g);
    }

    public void setFollowingList(List<UserModel> followingList) {
        this.follList = followingList;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public List<UserModel> getFollowingList() {
        return follList;
    }

    public UserClubModel getOwner() {
        return owner;
    }
}
