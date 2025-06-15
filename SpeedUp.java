import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpeedUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpeedUp extends Upgrades
{
    /**
     * Act - do whatever the SpeedUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage lightning;
    public SpeedUp(){
        //set image for coins;
        lightning = new GreenfootImage("lightning.png");
        setImage(lightning);
    }
    public void act()
    {
        // Add your action code here.
    }
}
