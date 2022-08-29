package kapta;

import javafx.application.Application;
import javafx.stage.Stage;
import kapta.utils.mysession.ThreadLocalSession;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        ThreadLocalSession firstUser = new ThreadLocalSession();
        new Thread(firstUser).start();
    }



    public static void main(String[] args) {
        launch();
    }


}
