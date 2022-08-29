package kapta.utils.mysession;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kapta.MainApp;

import java.io.IOException;

public class ThreadLocalSession implements Runnable {
    private static ThreadLocal<Session> userSession= new ThreadLocal<>();
    private static String usernameSession;
    private static int intrfc;

    public ThreadLocalSession() {
        //ignore
    }


    public static void setUsername(String username) {
        usernameSession = username;
    }

    public static void setUserSession(ThreadLocal<Session> userSession) {
       ThreadLocalSession.userSession = userSession;
    }

    public static String getUsername() {
        return usernameSession;
    }

    public static ThreadLocal<Session> getUserSession() {
        return userSession;
    }


    @Override
    public void run() {

        Platform.runLater(this::display);
    }

    private Runnable display() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/JFX1/JFX1Login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Mate");
        stage.setScene(scene);
        stage.showAndWait();
        return null;
    }

    public static int getIntrfc() {
        return intrfc;
    }

    public static void setIntrfc(int inter) {
        intrfc = inter;
    }
}
