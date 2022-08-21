package kapta.utils;

import java.util.List;
import java.util.Vector;

public abstract class Subject {
    private List<Observer> observers;

    protected Subject(Observer obs){
        this(new Vector<>());
        if(obs!= null){
            this.attach(obs);
        }
    }

    protected Subject(List<Observer> list) {
        this.observers= list;
    }

    public void attach(Observer obs ){
        this.observers.add(obs);
    }


    public void remove(Observer obs){
        this.observers.remove(obs);
    }

    public void notifyObservers(Object ob){
        for(Observer o : observers){
            o.update(ob);
           // o.updateFrom(ob,this);
        }
    }
    public  void notifyObservers(Object ob, Object from){
            for(Observer o : observers){
                o.update(ob);
                o.updateFrom(ob,from);
            }


    }




}