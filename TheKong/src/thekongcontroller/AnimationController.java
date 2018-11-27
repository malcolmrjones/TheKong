package thekongcontroller;

import javafx.animation.AnimationTimer;
import thekongview.PlayAreaView;


public class AnimationController extends AnimationTimer {
    
    private PlayAreaView playareaview;
    
    public AnimationController(PlayAreaView playareaview) {
        this.playareaview = playareaview;
    }
    
    @Override
    public void handle(long now) {
        playareaview.moveSprites();
    }

}
