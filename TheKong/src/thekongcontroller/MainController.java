package thekongcontroller;

import javafx.application.Application;
import javafx.stage.Stage;
import thekongmodel.LevelCollection;
import thekongmodel.PlayerProfileCollection;
import thekongmodel.SpriteDataCollection;


public class MainController extends Application {
    
    private String playerProfileConfigFileName;
    private String spriteConfigFileName;
    private String objectConfigFileName;
    
    private PlayerProfileCollection pplayerCollection;
    private SpriteDataCollection spriteCollection;
    private LevelCollection levelCollection;
    //private PlayAreaView playAreaView;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void savePlayerData() {
        
    }
    

}
