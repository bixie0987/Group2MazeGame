import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BiggerLight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lantern extends Upgrades
{
    /**
     * Act - do whatever the BiggerLight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage lantern;
    public Lantern(){
        //set image for coins;
        lantern = new GreenfootImage("lantern.png");
        setImage(lantern);
    }
    public void act()
    {
        if(isTouching(Player.class)){
            getWorld().removeObject(this);
        }
    }
}
