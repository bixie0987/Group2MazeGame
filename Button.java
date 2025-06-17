import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates pressable buttons of a customizable image.
 * (This class is taken from the RobotBuilder project)
 * 
 * @author Julia, Chin-En
 * @version Jun 2025
 * 
 * Button generator: https://www.imageonlinetools.com/button-generator
 */
public class Button extends Actor
{
    private boolean pressed = false;
    private boolean hovering = false;
    
    private GreenfootImage image;
    private GreenfootImage hoverImage;
    
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
        hoverImage = new GreenfootImage(imageName);
        hoverImage.scale((int)(image.getWidth()*1.1), (int)(image.getHeight()*1.1));
    }
    
    public void act()
    {
        if (Greenfoot.getMouseInfo() != null && Greenfoot.mouseMoved(this)) {
            if (!hovering) {
                setImage(hoverImage);
                hovering = true;
            }
        } else if (Greenfoot.getMouseInfo() != null && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            if (hovering) {
                setImage(image);
                hovering = false;
            }
        }
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
