import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scoreboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scoreboard extends World
{
    private Title scoreFont;
    private Button back;
    /**
     * Constructor for objects of class Scoreboard.
     * 
     */
    public Scoreboard()
    {    
        super(1024, 800, 1); 
        
        getBackground().setColor(Color.BLACK);
        getBackground().fill();
        
        scoreFont = new Title("highscoresFont.png", 1);
        addObject(scoreFont, 512, 150);
        
        back = new Button("backButton.png", 1);
        addObject(back, 512, 425);
    }
    public void act() {
        if (back != null && back.getPressed()) {
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
