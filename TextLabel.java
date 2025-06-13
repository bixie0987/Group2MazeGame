import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates text that allows for customizable size and colour.
 * (from RobotBuilder)
 * 
 * @author Julia
 * @version April 2025
 */
public class TextLabel extends Actor
{
    // Image for text
    private GreenfootImage img;
    
    // Text settings
    private String text;
    private int size;
    private Color colour;
    private Color highlightColour = new Color(0, 0, 0, 0); // transparent  bg/highlight
    
    /**
     * Creates text with customizable size and colour.
     * 
     * @param text           Text to display
     * @param givenSize      Size of font
     * @param givenColour    Colour of words
     */
    public TextLabel(String text, int givenSize, Color givenColour) {
        // Set text values
        this.text = text;
        size = givenSize;
        colour = givenColour;
        
        // Draw text
        img = new GreenfootImage(text, size, colour, highlightColour);
        setImage(img);
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    /**
     * Updates text to display new value.
     * 
     * @param newText    New text value to display
     */
    public void updateText(String newText) {
        GreenfootImage img = new GreenfootImage(newText, size, colour, highlightColour);
        setImage(img);
    }
    
    /**
     * Sets text colour to new colour.
     * 
     * @param newColour    New colour for text
     */
    public void setColour(Color newColour) {
        GreenfootImage img = new GreenfootImage(text, size, newColour, highlightColour);
        setImage(img);
    }
    
    /**
     * @return int    Returns width of text label image
     */
    public int getTextWidth() {
        return img.getWidth();
    }
}
