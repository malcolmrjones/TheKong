package thekongview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class CommandView extends HBox {
    
    private Button btnStart;
    private Button btnExit;
    
    public CommandView() {
        btnStart = new Button("START");
        btnExit = new Button("EXIT");
        
        this.getChildren().addAll(btnStart, btnExit);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10.0);
    }
    
    public Button getStartButton() {
        return btnStart;
    }
    
    public Button getExitButton() {
        return btnExit;
    }
   

}
