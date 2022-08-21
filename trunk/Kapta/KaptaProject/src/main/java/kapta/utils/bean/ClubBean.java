package kapta.utils.bean;

import kapta.model.lists.CreatedEventList;
import kapta.model.profiles.ClubModel;

public   class ClubBean extends GenericUserBean{

    public ClubBean (){

    }
    public ClubBean(ClubModel cl){
        super(cl);
    }
    public ClubBean(ClubModel cl, CreatedEventList cr){
        super(cl, cr);
    }

}
