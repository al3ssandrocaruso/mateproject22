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

    protected ClubBean (ClubBean clubBean){
        setEmail(clubBean.getEmail());
        setUsername(clubBean.getUsername());
        setName(clubBean.getName());
        setPassword(clubBean.getPassword());
        setType(1);
        setImg(clubBean.getImg());
        setId(clubBean.getId());
        setAddress(clubBean.getAddress());
        setWebsite(clubBean.getWebsite());
        setCity(clubBean.getCity());
    }

}
