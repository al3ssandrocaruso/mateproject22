package kapta.control.guicontroller.interfaceone;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import kapta.utils.VisualComponent;

public class JFX1UserPanel implements VisualComponent {
    private VBox panel;
    public JFX1UserPanel(VBox userPanel){this.setPanel((userPanel));}

    public void setPanel(VBox panel){
        this.panel = panel;
        this.panel.setMinWidth(50);
        this.panel.setMinHeight(50);
        this.panel.setStyle("-fx-background-color: white");
        VBox.setMargin(this.panel, new Insets(10, 0, 0, 0));
        this.panel.setAlignment(Pos.CENTER);
    }
    @Override
    public VBox addUserPanel() {
        return this.panel;
    }
}
