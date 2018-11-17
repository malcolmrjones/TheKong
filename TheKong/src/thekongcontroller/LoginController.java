package thekongcontroller;

import thekongmodel.PlayerProfile;
import thekongmodel.PlayerProfileCollection;
import thekongview.LoginView;


public class LoginController {
    
    private PlayerProfileCollection profiles;
    
    public LoginController(PlayerProfileCollection profiles) {
        this.profiles = profiles;
    }
    
    public PlayerProfile getLoginProfile() {
       
        
    }
    
    private void showLoginErrorAndExit() {
        
    }
    
}
