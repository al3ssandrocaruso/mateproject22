package kapta.model.lists;

import kapta.model.EventModel;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserClubModel;
import kapta.utils.Observer;
import kapta.utils.Subject;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.GenericListInfoBean;

import java.util.ArrayList;
import java.util.List;

public class CreatedEventList extends Subject {

        private List<EventModel> creatEvList = new ArrayList<>();
        private ClubModel owner;


        public CreatedEventList(ClubModel owner, List<EventModel> list, Observer obs){
            super(obs);
            this.owner=owner;
            for ( EventModel em : list ){
                this.addEvent(em);
            }
        }


    public Integer getSize(){
            return this.creatEvList.size();
        }

        public void addEvent(EventModel em) {
            this.creatEvList.add(em);
            EventBean eventBean = new EventBean(em);
            GenericListInfoBean g = new GenericListInfoBean(creatEvList.size(), 2);

            this.notifyObservers(eventBean,g);
        }

        public void removeElement(EventModel eventModel) {
            printList();    //da implementare
            for(EventModel em: this.creatEvList){
                if(em.getId()==eventModel.getId()){
                    this.creatEvList.remove(em);
                    notifyObservers(em);
                }
            }

        }
        public void printList(){
            //Da implementare per bene
        }

        public void setCreatedEventList(List<EventModel> createdEventList) {
            this.creatEvList = createdEventList;
        }

        public void setOwner(ClubModel owner) {
            this.owner = owner;
        }

        public List<EventModel> getCreatedEventList() {
            return creatEvList;
        }

        public UserClubModel getOwner() {
            return owner;
        }

}
