package thekongcontroller;

import com.sun.javafx.css.Combinator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import thekongmodel.LevelCollection;
import thekongmodel.PlayerProfileCollection;
import thekongmodel.SpriteDataCollection;
import thekongview.CommandView;
import thekongview.GameView;
import thekongview.PlayAreaView;
import thekongview.StatusView;


public class MainController extends Application {
    
    private static String playerProfileConfigFileName;
    private static String spriteConfigFileName;
    private static String objectConfigFileName;
    
    private PlayerProfileCollection playerCollection;
    private SpriteDataCollection spriteCollection;
    private LevelCollection levelCollection;
    //private PlayAreaView playAreaView;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        spriteCollection = IOController.readSpriteConfigFile(spriteConfigFileName);
        playerCollection = IOController.readPlayerProfiles(playerProfileConfigFileName);
        levelCollection = IOController.readObjectConfigFile(objectConfigFileName);
        
        LoginController logincontroller = new LoginController(playerCollection);
        String playerSelected = logincontroller.getLoginProfile().getPlayerName();
        savePlayerData();
        
        
        
        PlayAreaView playareaview = new PlayAreaView(spriteCollection, levelCollection.getLevel(0));
        StatusView statusview = new StatusView(playerSelected, 0, 0, 1);
        CommandView commandview = new CommandView();
        
        GameView root = new GameView(statusview, commandview, playareaview);
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kong - Malcolm R. Jones");
        primaryStage.show();
        
        
    }
    
    public static void main(String[] args) {
        playerProfileConfigFileName = args[0];
        spriteConfigFileName = args[1];
        objectConfigFileName = args[2];
        launch(args);
    }
    
    public void savePlayerData() {
        IOController.writePlayerProfiles(playerCollection);
    }
    
}
