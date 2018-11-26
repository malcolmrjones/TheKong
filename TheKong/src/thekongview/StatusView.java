package thekongview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class StatusView extends HBox {
    
    
    private Label lblCurrentPlayerName;
    private Label lblCurrentScore;
    private Label lblHighScore;
    private Label lblLevel;
    
    public StatusView(String currentplayername, int currentscore, int highscore, int level) {
        lblCurrentPlayerName = new Label("PLAYER: " + currentplayername);
        lblCurrentScore = new Label("SCORE: " + Integer.toString(currentscore));
        lblHighScore = new Label("HIGH SCORE: " + Integer.toString(highscore));
        lblLevel = new Label("LEVEL: " + level);
                
        this.getChildren().addAll(lblCurrentPlayerName, lblCurrentScore, lblHighScore, lblLevel);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10.0);
    }
    
    public void setCurrentPlayerName(String currentplayername) {
        lblCurrentPlayerName.setText("PLAYER: " + currentplayername);
    }
    
    public void setCurrentScore(int currentscore) {
        lblCurrentScore.setText("SCORE: " + Integer.toString(currentscore));
    }
    
    public void setHighScore(int highscore) {
        lblHighScore.setText("HGIH SCORE: " + Integer.toString(highscore));
    }
    
    public void setLevel(int level) {
        lblLevel.setText("LEVEL: " + Integer.toString(level));
    }

}