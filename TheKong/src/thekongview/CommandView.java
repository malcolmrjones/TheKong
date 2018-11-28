package thekongview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class CommandView extends HBox {
    
    private Button btnStart;
    private Button btnExit;
    
    public CommandView() {
        btnStart = new Button("START");
        btnExit = new Button("EXIT");
        
        btnStart.setFocusTraversable(false);
        btnExit.setFocusTraversable(false);
        
        this.getChildren().addAll(btnStart, btnExit);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10.0);
        this.setPadding(new Insets(15, 15, 15, 15));
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    public Button getStartButton() {
        return btnStart;
    }
    
    public Button getExitButton() {
        return btnExit;
    }
   

}

