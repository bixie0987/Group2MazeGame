import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class coins here.
 * 
 * @author Jaclyn
 * @version Jun 2025
 */
public class Coins extends Actor
{
    /**
     * Act - do whatever the coins wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage coin;
    public Coins(){
        //set image for coins;
        coin = new GreenfootImage("coin.png");
        setImage(coin);
    }
    public void act()
    {
        if(isTouching(Player.class)){
            getWorld().removeObject(this); // coin disappears when collected
            
        }
    }
}
