package thekongcontroller;

import javafx.animation.AnimationTimer;
import thekongview.HeroView;
import thekongview.PlayAreaView;


public class AnimationController extends AnimationTimer {
    
    private PlayAreaView playareaview;
    
    public AnimationController(PlayAreaView playareaview) {
        this.playareaview = playareaview;
    }
    
    @Override
    public void handle(long now) {
       
        HeroView hero = playareaview.getHero();
        
        /* Boundary Collision Detection */
        if(hero.getCenterX() - hero.getBoundingRadius() < 0) {
            hero.setDirection(0.0);
            hero.setSpeed(1.0);
            hero.move();
            hero.setSpeed(0.0);
        }
           
        if(hero.getCenterX() + hero.getBoundingRadius() > playareaview.getWidth()) {
            hero.setDirection(180.0);
            hero.setSpeed(1.0);
            hero.move();
            hero.setSpeed(0.0);
                   
        }
        
        playareaview.moveSprites();
        
    }

}
