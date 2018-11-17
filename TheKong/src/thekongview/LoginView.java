package thekongview;

import java.util.List;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;


public class LoginView extends ChoiceDialog<String> {
    
    
    public LoginView(List<String> choices) {
         super(choices.get(0), choices);
         this.setTitle("Login");
         this.setHeaderText("Player Login");
         this.setContentText("Please select a player profile:");
    }
    
    public String displayLoginView() {
       Optional<String> result = this.showAndWait();
       return result.get();
    }
    
    
}
