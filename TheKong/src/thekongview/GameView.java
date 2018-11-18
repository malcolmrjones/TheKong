package thekongview;

import javafx.scene.layout.BorderPane;


public class GameView extends BorderPane {
    
    private StatusView statusview;
    private CommandView commandview;
    //private PlayAreaView playareaview;
    
    public GameView() {
        
        statusview = new StatusView("PLAYERNAME", 100, 200, 1);
        this.setTop(statusview);
        
        //playareaview = new PlayAreaView();
        //this.setCenter(playareaview);
        
        commandview = new CommandView();
        this.setBottom(commandview);
    }
    
}
