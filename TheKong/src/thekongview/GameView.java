<<<<<<< HEAD
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
    //private PlayAreaView playareaview;
    
    public GameView() {
        
        //playareaview = new PlayAreaView();
        //this.setCenter(playareaview);
        
        statusview = new StatusView("PLAYERNAME", 100, 200, 1);     
        commandview = new CommandView();
        VBox vboxBottom = new VBox();
        vboxBottom.getChildren().addAll(statusview, commandview);
        vboxBottom.setSpacing(10);
        vboxBottom.setPadding(new Insets(5, 10, 10, 10));
        vboxBottom.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        this.setBottom(vboxBottom);
    }
    
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thekongview;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author chris
 */
public class GameView extends BorderPane{
    public GameView(StatusView statusView, PlayAreaView playAreaView, CommandView commandView) {
        this.setTop(statusView);
        this.setCenter(playAreaView);
        this.setBottom(commandView);
    }
>>>>>>> level-1
}
