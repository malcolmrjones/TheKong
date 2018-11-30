package thekongcontroller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
import javafx.scene.paint.Color;
import sun.tools.jconsole.inspector.XDataViewer;
import thekongmodel.BarrelData;
import thekongview.BarrelView;
import thekongview.HeroView;
import thekongview.LadderView;
import thekongview.PlatformView;
import thekongview.PlayAreaView;


public class AnimationController extends AnimationTimer {
    
    private PlayAreaView playareaview;
    private HeroView hero;
    public final double PLAYER_SPEED = 3;
    public final double BARREL_SPEED = 1.5;
    public final double GRAVITY = 4;
    public boolean isHeroJump;
    public long frameCount;
    public long jumpStartFrame;
    public long barrelStartFrame;
    
    
    
    public AnimationController(PlayAreaView playareaview) {
        this.playareaview = playareaview;
        this.hero = playareaview.getHero();
        this.isHeroJump = false;
        this.frameCount = 0;
        this.barrelStartFrame = 0;
    }
    
    
    
    @Override
    public void handle(long now) {
        
        handleHeroOutOfBounds();
        handleGravity();
        handleHeroOnFloor();
        handleHeroBelowPlatform();
        handleLadderClimb();
        handleJump();
        handleBarrels();
        
        playareaview.moveSprites();
        frameCount += 1;
        
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
            if((frameCount - jumpStartFrame) < 10) {
                hero.setY(hero.getY() - GRAVITY * 2.5);
            }
            else {
                isHeroJump = false;
            }
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
    
    private void handleBarrels() {
        
        //Random (int)(Math.random() * (60 - 30) + 30)
        /* CREATE NEW BARRELS */
        if((frameCount - barrelStartFrame) > (int)(Math.random() * (250 - 75) + 75)) {
           
            BarrelView barrel = new BarrelView(playareaview.getBarrelData());
            barrel.setDirection(0);
            barrel.setSpeed(BARREL_SPEED);
            
            playareaview.addBarrel(barrel);
           
            barrelStartFrame = frameCount;
        }
        
        for(int i = 0; i < playareaview.getNumBarrels(); i++) {
            
            BarrelView barrel = playareaview.getBarrel(i);
            
            handleBarrelOutOfBounds(barrel);
            handleBarrelGravity(barrel);
            handleBarrelHitsHero(barrel);
            handleBarrelOnFloor(barrel);
            
            
        }
        
        
    }
    
    private void handleBarrelOutOfBounds(BarrelView barrel) {
        
        if(barrel.getCenterX() - barrel.getBoundingRadius() < 0) {
            barrel.setDirection(0);
        }

        if(barrel.getCenterX() + barrel.getBoundingRadius() > playareaview.getWidth()) {
            barrel.setDirection(180);
        }
        
    }
    
    private void handleBarrelGravity(BarrelView barrel) {
        if(!isBarrelOnPlatform(barrel) && !isBarrelOnFloor(barrel)) {
            barrel.setY(barrel.getY() + GRAVITY);
        }
    }
    
    private void handleBarrelOnFloor(BarrelView barrel) {
        
        int indexOfBarrel = 0;
        
        for(int i = 0; i < playareaview.getNumBarrels(); i++) {
            if(playareaview.getBarrel(i) == barrel) {
                indexOfBarrel = i;
                break;
            }
        }
        
        if(isBarrelOnFloor(barrel)) {
            if(barrel.getCenterX() - barrel.getBoundingRadius() < 0) {
                playareaview.deleteBarrel(indexOfBarrel);
            }
        }
    }
    
    private void handleBarrelHitsHero(BarrelView barrel) {
        if(barrel.getBoundsInLocal().intersects(hero.getBoundsInLocal())) {
            this.stop();
            Alert gameover = new Alert(AlertType.INFORMATION);
            gameover.setTitle("GAME OVER");
            gameover.setHeaderText(null);
            gameover.setContentText("A BARREL HIT YOU, YOU LOST");
            gameover.setOnHidden(new EventHandler<DialogEvent>() { 
                @Override
                public void handle(DialogEvent event) {
                    System.exit(0);
                }
        
            });
            gameover.show();
            
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
    
    private boolean isBarrelOnPlatform(BarrelView barrel) {
        PlatformView platform;
        double barrelboundingdiameter = barrel.getBoundingRadius()*2;
        
        
        for(int i = 0; i < playareaview.getLevelView().getNumPlatforms(); i++) {
            
            platform = playareaview.getLevelView().getPlatformView(i);

            if(barrel.getY() >= platform.getY() - barrelboundingdiameter &&
                barrel.getY() <= platform.getY()+platform.getHeight() - barrelboundingdiameter) {
                
                if(barrel.getX() > platform.getX() && barrel.getX() < platform.getX()+platform.getWidth() ||
                    barrel.getX()+barrelboundingdiameter > platform.getX() && barrel.getX()+barrelboundingdiameter < platform.getX()+platform.getWidth())
                
                return true;
            }
            
        }
        
        return false;
    }
    
    private boolean isBarrelOnFloor(BarrelView barrel) {
        if( barrel.getY() + barrel.getBoundingRadius()*2 >= playareaview.getHeight()) {
            return true;
        }
        return false;
    }
    
        

}
