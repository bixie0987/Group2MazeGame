import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For titles and instruction visuals, non animated (except for the game over)
 * 
 * @author Chin-En
 * @version Jun 2025
 * 
 * Pixel word ai generator: https://deepai.org/
 */
public class Title extends Actor
{
    private GreenfootImage image;
    /**
     * Act - do whatever the title wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Title (String imageName, double scale)
    {
        image = new GreenfootImage(imageName);
        image.scale((int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
        setImage(image);
    }
    public void setTransparency(int i) {
        GreenfootImage temp = new GreenfootImage(image);
        temp.setTransparency(i);
        setImage(temp);
    }
}
