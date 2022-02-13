package kapta.utils.pagesetter.setterjfx1;

import kapta.application.ClubProfileApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1CreateEventGUIController;
import kapta.model.profiles.ClubModel;

public class JFX1CreateEventSetter {

    private JFX1CreateEventSetter(){
        //ignore
    }

    public static void setter(ClubModel clubModel, JFX1CreateEventGUIController jfx1CreateEventGUIControllereateEventGUIController) {
        ClubProfileApplicationLayer clubProfileApplication = new ClubProfileApplicationLayer(clubModel);
        jfx1CreateEventGUIControllereateEventGUIController.setClubProfileApplication(clubProfileApplication);
    }
}

