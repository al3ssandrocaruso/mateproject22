package kapta.model.lists;

import kapta.model.profiles.UserClubModel;
import kapta.model.RequestModel;
import kapta.utils.GenericObservableList;
import kapta.utils.Observer;
import kapta.utils.bean.RequestBean;

import java.util.ArrayList;
import java.util.List;

public class RequestList extends GenericObservableList {

    private List<RequestModel> reqList = new ArrayList<>();
    private UserClubModel owner;

    public RequestList(UserClubModel userClub, List<RequestModel> list, Observer obs){

            super(obs);
            for ( RequestModel rm : list ){
                this.addRequest(rm);
            }
            this.owner = userClub;
    }



    public void addRequest(RequestModel rm){
        this.reqList.add(rm);
        RequestBean requestBean = new RequestBean(rm) ;
        notifyObservers(requestBean);
    }

    public void setListCreator(UserClubModel creator) {
        this.owner= creator;
    }

    public void setPendingRequestList(List<RequestModel> pendingRequestList) {
        this.reqList = pendingRequestList;
    }

    public List<RequestModel> getPendingRequestList() {
        return reqList;
    }

    public UserClubModel getListCreator() {
        return owner;
    }

    public void removeRequest (RequestModel rm){
        for (RequestModel rq : this.reqList ){
            if(rq.getRequestId() == rm.getRequestId()){
                    this.reqList.remove(rq);
                RequestBean requestBean = new RequestBean(rq) ;
                    notifyObservers(requestBean);
            }
        }
    }
}
