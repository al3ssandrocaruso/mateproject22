package kapta.utils.utils;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GetFontedLabel {
    private GetFontedLabel(){
        //ignore
    }
    public static Label getFonted(String labelData, String font){
        Font font1 = Font.font(font, FontWeight.BOLD, 18);
        Label label1 = new Label(labelData);
        label1.setFont(font1);
        return label1;

    }
}
