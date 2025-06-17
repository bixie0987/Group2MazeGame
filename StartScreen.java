import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main menu will have the game title (shadow's below) and two buttons to either go
 * to the game or to the instruction page.
 * 
 * @author Chin-En
 * @version Jun 2025
 */
public class StartScreen extends World
{
    private Button playButton;
    private Button helpButton;
    private Title titleLogo;
    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        getBackground().setColor(Color.BLACK);
        getBackground().fill();
        
        playButton = new Button("playButton.png", 1);
        addObject(playButton, 512, 550);
        
        helpButton = new Button("helpButton.png", 1);
        addObject(helpButton, 512, 650);
        
        titleLogo = new Title("titleGraphic.png", 1.5);
        addObject(titleLogo, 512, 200);
    }
    public void act() {
        if (playButton != null && playButton.getPressed()) {
            Greenfoot.setWorld(new MyWorld());
        }
        if (helpButton != null && helpButton.getPressed()) {
            Greenfoot.setWorld(new Instruction());
        }
    }
}
