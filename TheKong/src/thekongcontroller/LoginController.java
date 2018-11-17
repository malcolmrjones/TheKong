package thekongcontroller;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import thekongmodel.PlayerProfile;
import thekongmodel.PlayerProfileCollection;
import thekongview.LoginView;


public class LoginController {
    
    private PlayerProfileCollection profiles;
    
    public LoginController(PlayerProfileCollection profiles) {
        this.profiles = profiles;
    }
    
    public PlayerProfile getLoginProfile() {
        
        ArrayList<String> profilenames = new ArrayList<String>();
        for(int i = 0; i < profiles.getNumPlayerProfiles(); i++) {
            profilenames.add(profiles.getPlayerProfile(i).getPlayerName());
        }
        profilenames.add("+ CREATE NEW PROFILE");
        LoginView loginview = new LoginView(profilenames);
        
        String choice = loginview.displayLoginView();
        if(profilenames.subList(0, profilenames.size()-2).contains(choice)) {
            return profiles.getPlayerProfile(profilenames.indexOf(choice));
        }
        
        //the choice is creating a new profile
        String name = (new TextInputDialog()).showAndWait().get();
        PlayerProfile newprofile = new PlayerProfile(name);
        profiles.addPlayerProfile(newprofile);
        return newprofile;
        
    }
    
    private void showLoginErrorAndExit() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("LOGIN ERROR");
        alert.setContentText("INVALID PLAYER PROFILE");
        alert.showAndWait();
    }
    
}
