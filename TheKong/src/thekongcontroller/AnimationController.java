package thekongcontroller;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import sun.tools.jconsole.inspector.XDataViewer;
import thekongview.HeroView;
import thekongview.LadderView;
import thekongview.PlatformView;
import thekongview.PlayAreaView;


public class AnimationController extends AnimationTimer {
    
    private PlayAreaView playareaview;
    private HeroView hero;
    public final double PLAYER_SPEED = 3.0;
    public final double GRAVITY = 4;
    public boolean isHeroJump;
    private int frameCount;
    
    
    
    public AnimationController(PlayAreaView playareaview) {
        this.playareaview = playareaview;
        this.hero = playareaview.getHero();
        this.isHeroJump = false;
        this.frameCount = 0;
    }
    
    
    
    @Override
    public void handle(long now) {
        
        handleHeroOutOfBounds();
        handleGravity();
        handleHeroOnFloor();
        handleHeroBelowPlatform();
        handleLadderClimb();
        handleJump();
        
        playareaview.moveSprites();
        
    }
    
    
    
    
    
    
    private void handleHeroOutOfBounds() {
        if(hero.getCenterX() - hero.getBoundingRadius() < 0) {
            hero.setX(0);
        }
           
        if(hero.getCenterX() + hero.getBoundingRadius() > playareaview.getWidth()) {
            hero.setX(playareaview.getWidth() - hero.getBoundingRadius() * 2);
        }
    }
    
    private void handleHeroOnFloor() {
        if(isHeroOnFloor(playareaview)) {
            hero.setY(playareaview.getHeight() - hero.getBoundingRadius()*2);
        }
        
     }
    
    private void handleHeroBelowPlatform() {
        PlatformView platform;
        double playerboundingdiameter = hero.getBoundingRadius()*2;
        
        
        for(int i = 0; i < playareaview.getLevelView().getNumPlatforms(); i++) {
            
            platform = playareaview.getLevelView().getPlatformView(i);

            if(hero.getY()+playerboundingdiameter >= platform.getY() + playerboundingdiameter &&
                hero.getY()+playerboundingdiameter <= platform.getY()+platform.getHeight() + playerboundingdiameter) {
                
                if(hero.getX() > platform.getX() && hero.getX() < platform.getX()+platform.getWidth() ||
                    hero.getX()+playerboundingdiameter > platform.getX() && hero.getX()+playerboundingdiameter < platform.getX()+platform.getWidth()) {
                    
                    if(!isHeroOnLadder(playareaview)) {
                        hero.setY(platform.getY()+platform.getHeight()+1);
                    }
                    
                }
               
            }
            
        }
    }
    
    private void handleGravity() {
        if(!isHeroOnLadder(playareaview) && 
            !isHeroOnFloor(playareaview) &&
            !isHeroOnPlatform(playareaview)) {
            hero.setY(hero.getY() + GRAVITY);
        }
    }
    
    private void handleJump() {

        if(isHeroJump) {
            if(frameCount < 10) {
                hero.setY(hero.getY() - GRAVITY * 2.5);
            }
            else {
                isHeroJump = false;
                frameCount = 0;
            }
            frameCount++;
        }
        
        
        
    }
    
    private void handleLadderClimb() {
        if(hero.getDirection() == 270 || hero.getDirection() == 90 ) {
            
            if(isHeroOnLadder(playareaview)) {
               hero.setSpeed(PLAYER_SPEED);
            }
            else {
                hero.setSpeed(0);
                hero.setDirection(0);
            }
            
        }
        
    }
    
    
    
    
    
    
    public boolean isHeroOnLadder(PlayAreaView playareaview) {

        LadderView ladder;
  
        for(int i = 0; i < playareaview.getLevelView().getNumLadders(); i++) {
            ladder = playareaview.getLevelView().getLadder(i);
            
            if(hero.getBoundsInLocal().intersects(ladder.getBoundsInLocal())) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isHeroOnFloor(PlayAreaView playareaview) {
        
        if( hero.getY() + hero.getBoundingRadius()*2 >= playareaview.getHeight()) {
            return true;
        }
        
        return false;
    }
    
    public boolean isHeroOnPlatform(PlayAreaView playareaview) {

        PlatformView platform;
        double playerboundingdiameter = hero.getBoundingRadius()*2;
        
        
        for(int i = 0; i < playareaview.getLevelView().getNumPlatforms(); i++) {
            
            platform = playareaview.getLevelView().getPlatformView(i);

            if(hero.getY() >= platform.getY() - playerboundingdiameter &&
                hero.getY() <= platform.getY()+platform.getHeight() - playerboundingdiameter) {
                
                if(hero.getX() > platform.getX() && hero.getX() < platform.getX()+platform.getWidth() ||
                    hero.getX()+playerboundingdiameter > platform.getX() && hero.getX()+playerboundingdiameter < platform.getX()+platform.getWidth())
                
                return true;
            }
            
        }
        
        return false;
    }
    
    
    
    
        

}
