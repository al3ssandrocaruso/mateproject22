package kapta.utils.session;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kapta.MainApp;
import java.io.IOException;

public class ThreadLocalSession implements Runnable
{
    public static ThreadLocal<Session> userSession= new ThreadLocal<>();
    public static String usernameSession;
    public static int intrfc;

    public ThreadLocalSession() {}

    public ThreadLocalSession(String username){
        usernameSession = username;
        userSession = new ThreadLocal<>();
    }

    public void setUsername(String username) {
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

        Platform.runLater(() -> display()); //<- Forma corretta senza code smells
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

    public static void setIntrfc(int intrfc) {
        ThreadLocalSession.intrfc = intrfc;
    }
}