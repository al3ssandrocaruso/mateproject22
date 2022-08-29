package test.junit;

import kapta.control.appcontroller.Search;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/*
NOTA:
    -Per far partire il test disattivare l'opzione "Delegate IDE build/run actions to Maven
    che si trova nelle sezione impostazioni: Build, Excecution, Deployment -> Maven -> Runner
*/

public class TestSearch {

    /**
     * Qui si implementano diversi test che ci permettono di capire se la funziona search ritorna effettivamente
     * un elemento in base alla stringa che gli si passa (inserita dall'utente)
     */
    //FAILED
    @Test
    public void testSearchUser(){
        List<UserModel> list = Search.searchUserByUsername("test");

        int ret = 0;

        if (!list.isEmpty()){
            ret = 1;
        }

        assertEquals(1, ret, 0);
    }

    //SUCCESS
    @Test
    public void testSearchParty(){
        List<PartyModel> list = Search.searchByPartyName("party1");

        int ret = 0;

        if (!list.isEmpty()){
            ret = 1;
        }

        assertEquals(1, ret, 0);
    }

    //FAILED
    @Test
    public void testSearchEvent(){
        List<EventModel> list = Search.searchByEventName("event");

        int ret = 0;

        if (!list.isEmpty()){
            ret = 1;
        }

        assertEquals(1, ret, 0);
    }
}
