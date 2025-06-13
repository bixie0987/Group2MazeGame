import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class lighting here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lighting extends Actor
{
    private GreenfootImage image;
    
    /**
     * Act - do whatever the lighting wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    /**
     * Sets the image for a single shader block
     */
    public Lighting(){
        image = new GreenfootImage("black_rectangle.png");
        setImage(image);
    }
}
