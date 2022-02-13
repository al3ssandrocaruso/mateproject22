package kapta;

import javafx.application.Application;
import javafx.stage.Stage;
import kapta.utils.session.ThreadLocalSession;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        ThreadLocalSession firstUser = new ThreadLocalSession();
        ThreadLocalSession secondUser = new ThreadLocalSession();
        new Thread(firstUser).start();
        new Thread(secondUser).start();

    }



    public static void main(String[] args) {
        launch();
    }


}
