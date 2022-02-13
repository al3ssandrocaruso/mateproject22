package kapta.utils;

//Deve essee interfaccia e non classe astratta per evitare code smells
public interface Observer {

   void update(Object ob);
   void updateFrom(Object ob, Object from);

}
