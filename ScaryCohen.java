import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScaryCohen here.
 * 
 * @author Jaclyn
 * @version Jun 2025
 */
public class ScaryCohen extends World
{

    /**
     * Constructor for objects of class ScaryCohen.
     * 
     */
    private int timer;
    public ScaryCohen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        timer = 0;
        getBackground().setColor(Color.BLACK);
        getBackground().fill();
        setBackground("scary_cohen.png");
    }
    public void act() {
        timer++;
        if (timer == 300) { // 60 frames/sec * 5 seconds = 300
            Greenfoot.setWorld(new EndScreen());
        }
    }
}