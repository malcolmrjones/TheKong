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
    public final double playerSpeed = 3.0;
    public final double GRAVITY = 4;
    public boolean isHeroJump;
    private int frameCount;
    
    public AnimationController(PlayAreaView playareaview) {
        this.playareaview = playareaview;
        this.isHeroJump = false;
        this.frameCount = 0;
    }
    
    @Override
    public void handle(long now) {
       
        boolean heroIsOnLadder = isHeroOnLadder(playareaview);
       
        
        handleHeroOutOfBounds();
        handleGravity();
        handleJump();
        handleHeroHitsFloor();
        handleHeroOnPlatform();
        handleLadderClimb();
      
        
        playareaview.moveSprites();
        
    }
    
    private void handleHeroOutOfBounds() {
        HeroView hero = playareaview.getHero();
        
        if(hero.getCenterX() - hero.getBoundingRadius() < 0) {
            hero.setX(0);
        }
           
        if(hero.getCenterX() + hero.getBoundingRadius() > playareaview.getWidth()) {
            hero.setX(playareaview.getWidth() - hero.getBoundingRadius() * 2);
        }
    }
    
    private void handleHeroHitsFloor() {
        
        HeroView hero = playareaview.getHero();
        
        if(isHeroOnFloor(playareaview)) {
            hero.setY(playareaview.getHeight() - hero.getBoundsInLocal().getHeight());
        }
        
     }
   
    private void handleHeroOnPlatform() {
        
        HeroView hero = playareaview.getHero();
        PlatformView platform;
        Bounds herobounds = hero.getBoundsInLocal();
        double HERO_TOP = herobounds.getMinY();
        double HERO_BOTTOM = herobounds.getMinY() + herobounds.getHeight();
        

        for(int i = 0; i < playareaview.getLevelView().getNumPlatforms(); i++) {

            platform = playareaview.getLevelView().getPlatformView(i);
            double PLATFORM_TOP = platform.getBoundsInLocal().getMinY();
            double PLATFORM_BOTTOM = platform.getBoundsInLocal().getMinY() + platform.getBoundsInLocal().getHeight();


            if(HERO_BOTTOM >= PLATFORM_TOP && HERO_BOTTOM <= PLATFORM_BOTTOM &&
                    hero.getBoundsInLocal().intersects(platform.getBoundsInLocal())) {
                hero.setY(PLATFORM_TOP - hero.getBoundsInLocal().getHeight());
            } 
            
            if(HERO_TOP >= PLATFORM_TOP && !isHeroOnLadder(playareaview) &&
                    hero.getBoundsInLocal().intersects(platform.getBoundsInLocal())) {
                hero.setY(PLATFORM_BOTTOM + 1);
            }

        }

        
    }
    
    private void handleGravity() {
        
        HeroView hero = playareaview.getHero();
        
        if(!isHeroOnLadder(playareaview) && !isHeroOnFloor(playareaview)) {
            hero.setY(hero.getY() + GRAVITY);
        }
    }
    
    private void handleJump() {
        
        HeroView hero = playareaview.getHero();
        
        if(isHeroJump && !isHeroOnLadder(playareaview)) {
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
        
        HeroView hero = playareaview.getHero();
        
        if(hero.getDirection() == 270 || hero.getDirection() == 90 ) {
                if(isHeroOnLadder(playareaview)) {
                    hero.setSpeed(playerSpeed);
                }
                else {
                    hero.setSpeed(0);
                    hero.setDirection(0);
                }
        }
        
    }
    
    public boolean isHeroOnLadder(PlayAreaView playareaview) {
        
        HeroView hero = playareaview.getHero();
        LadderView ladder;
        boolean isHeroOnLadder = false;
        
        for(int i = 0; i < playareaview.getLevelView().getNumLadders(); i++) {
            
            ladder = playareaview.getLevelView().getLadder(i);
            
            isHeroOnLadder = hero.getBoundsInLocal().intersects(ladder.getBoundsInLocal());
            
            if(isHeroOnLadder) {
                break;
            }
        }
        
        return isHeroOnLadder;
    }
    
    public boolean isHeroOnFloor(PlayAreaView playareaview) {
        HeroView hero = playareaview.getHero();
        Bounds herobounds = hero.getBoundsInLocal();
        double HERO_BOTTOM = herobounds.getMinY() + herobounds.getHeight();
  
        if( HERO_BOTTOM >= playareaview.getHeight()) {
            return true;
        }
        
        return false;
    }
    
    public boolean isHeroOnPlatform(PlayAreaView playareaview) {
        
        HeroView hero = playareaview.getHero();
        PlatformView platform;
        Bounds herobounds = hero.getBoundsInLocal();
        double HERO_BOTTOM = herobounds.getMinY() + herobounds.getHeight();
        
        for(int i = 0; i < playareaview.getLevelView().getNumPlatforms(); i++) {
            
            platform = playareaview.getLevelView().getPlatformView(i);
            double PLATFORM_TOP = platform.getBoundsInLocal().getMinY();
            double PLATFORM_BOTTOM = platform.getBoundsInLocal().getMinY() + platform.getBoundsInLocal().getHeight();
            
            if(HERO_BOTTOM >= PLATFORM_TOP && HERO_BOTTOM <= PLATFORM_BOTTOM &&
                    hero.getBoundsInLocal().intersects(platform.getBoundsInLocal())) {
                return true;
            } 
            
        }
        
        return false;
    }
    
    
    
    
        

}
