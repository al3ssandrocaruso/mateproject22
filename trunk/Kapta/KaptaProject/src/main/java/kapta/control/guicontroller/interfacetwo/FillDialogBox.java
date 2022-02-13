package kapta.control.guicontroller.interfacetwo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class FillDialogBox {
    private FillDialogBox(){
        //ignored
    }
    public static int fill(Label label1, Button button, Label label2, int numSbagliate) {
        label1.setVisible(false);
        button.setVisible(false);
        label2.setText("Retry in ");
        Timeline tm = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<>() {
            int i = 5*numSbagliate;
            @Override
            public void handle(ActionEvent actionEvent) {
                label2.setText("Retry in "+i);
                if(i==0){
                    label1.setText("An email has been sent to you");
                    label2.setText("Please, insert your token to confirm");
                    button.setVisible(true);
                    label1.setVisible(true);
                }
                i--;
    }
}));
        tm.setCycleCount((5*numSbagliate)+1);
        tm.play();
        return numSbagliate+1;

}}
