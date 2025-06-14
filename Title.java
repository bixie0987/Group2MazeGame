import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
