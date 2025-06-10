import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class coins here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coins extends Actor
{
    /**
     * Act - do whatever the coins wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage coin;
    public Coins(){
        //set image for suppliers
        coin = new GreenfootImage("coin.png");
        setImage(coin);
    }
    public void act()
    {
        /*
        if(Player.isTouching(Coins.class)){
            Greenfoot.removeObject(Coins.class);
        }
        */
    }
}
