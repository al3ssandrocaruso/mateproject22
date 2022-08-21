package kapta.model.lists;
import kapta.model.profiles.UserClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.GenericObservableList;
import kapta.utils.Observer;
import kapta.utils.bean.GenericListInfoBean;
import kapta.utils.bean.UserBean;
import java.util.ArrayList;
import java.util.Iterator;
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
        UserBean ub = new UserBean(pm);
        GenericListInfoBean gl = new GenericListInfoBean(follList.size(),1);
        this.notifyObservers(ub,gl);
    }
    public void removeUser(UserModel pm) {
        Iterator<UserModel> it = this.follList.iterator();
        while(it.hasNext()){
            UserModel um = it.next();
            if (um.getId()== pm.getId()){
                it.remove();
            }
        }
        GenericListInfoBean gl = new GenericListInfoBean(follList.size(),1);
        UserBean ub = new UserBean(pm);
        this.notifyObservers(ub,gl);


        /*for(UserModel um: this.follList){
            if(um.getId()==pm.getId()){
                GenericListInfoBean gl = new GenericListInfoBean();
                gl.setSize(follList.size());
                UserBean ub = new UserBean(pm);
                this.notifyObservers(ub,gl);
            }
        }*/

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
