import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates pressable buttons of a customizable image.
 * (This class is taken from the RobotBuilder project)
 * 
 * @author Julia
 * @version April 2025
 */
public class Button extends Actor
{
    private boolean pressed = false;
    
    private GreenfootImage image;
    
    /**
     * Provides an image for the button and a scale value to resize the image.
     * 
     * @param image    The image of the button
     * @param scale    The scale of the button's image
     */
    public Button(String imageName, double scale) {
        image = new GreenfootImage(imageName);
        image.scale((int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
        setImage(image);
    }
    
    public void act()
    {
        if(Greenfoot.mousePressed(this)) {
            pressed = true;
        } else {
            pressed = false;
        }
    }
    
    /**
     * Returns whether or not button is pressed.
     * 
     * @return boolean    True if mouse has pressed button.
     */
    public boolean getPressed() {
        return pressed;
    }
}
