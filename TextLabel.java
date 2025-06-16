import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates text that allows for customizable size and colour.
 * (from RobotBuilder)
 * 
 * Credit: 2nd constructor written by ChatGPT.
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
    private Font font;
    
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
    
    /**
     * Creates text with customizable size and colour AND FONT.
     * To get a list of fonts: follow instructions by Danpost on this link https://www.greenfoot.org/topics/65192/0
     * 
     * @param text           Text to display
     * @param givenSize      Size of font
     * @param givenColour    Colour of words
     * @param font           Font of words
     */
    public TextLabel(String text, int givenSize, Color givenColour, Font font) {
        // Set text values
        this.text = text;
        size = givenSize;
        colour = givenColour;
        this.font = font;
        
        // Set temporary image just to apply font
        GreenfootImage temp = new GreenfootImage(1, 1);
        temp.setFont(font);
    
        // Estimate width and height (you may tweak this)
        int width = text.length() * size;
        int height = size + 10;
    
        img = new GreenfootImage(width, height);
        img.setFont(font);
        img.setColor(colour);
        img.drawString(text, 0, size); // Y-offset should match size for baseline
    
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
     * Sets text transparency to new transparency (0-transparent, 255-opaque).
     * 
     * @param newTransparency    New transparency for text
     */
    public void setTransparency(int transparency) {
        img.setTransparency(transparency);
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
