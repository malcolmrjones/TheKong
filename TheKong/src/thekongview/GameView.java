package thekongview;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class GameView extends BorderPane {
    
    private StatusView statusview;
    private CommandView commandview;
    private PlayAreaView playareaview;
    
    public GameView(StatusView statusview, CommandView commandview, PlayAreaView playareaview) {
        
        this.playareaview = playareaview;
        this.setCenter(this.playareaview);
        
        this.statusview = statusview;   
        this.commandview = commandview;
        
        this.setTop(this.statusview);
        this.setBottom(this.commandview);
       
    }
}
