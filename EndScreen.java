import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author Chin-En, Julia
 * @version Jun 2025
 */
public class EndScreen extends World
{
    private Title death;
    private int fadeTimer = 0;
    private boolean spawnedReturnScreen = false;
    private Button yes;
    private Button no;
    private Title returnToMenu;
    
    
    
    private int fadeTimer2 = 0;
    private boolean spawnedScoresScreen = false;
    private HighScoreScreen highScoreScreen;
    
    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen()
    {
        super(1024, 800, 1);
        
        getBackground().setColor(Color.BLACK);
        getBackground().fill();
        
        death = new Title("gameOver.png", 2);
        addObject(death, 512, 400);
        death.setTransparency(0);
        
        highScoreScreen = new HighScoreScreen(this);
        addObject(highScoreScreen, getWidth()/2, getHeight()/2);
        highScoreScreen.setTransparency(0);
        
        setPaintOrder(HighScoreScreen.class);
    }
    public void act() {
        if (fadeTimer < 225) {
            fadeTimer += 5;
            death.setTransparency(Math.min(fadeTimer, 255));
        } else if (fadeTimer >= 225 && fadeTimer < 1200) {
            fadeTimer += 5;
        } else if (fadeTimer2 < 225) { // high score screen
            // Remove previous screen's objects
            removeObject(death);
            
            // Gradually fade in new text
            fadeTimer2 += 5;
            int transparency = Math.min(fadeTimer2, 255);
            highScoreScreen.setTransparency(transparency);
        } else if (fadeTimer2 >= 225 && fadeTimer2 < 2400) {
            fadeTimer2 += 5;
        } else {
            // Remove previous screen's objects
            removeObject(highScoreScreen);
            
            spawnReturnScreen();
            spawnedReturnScreen = true;
            if (yes != null && yes.getPressed()) {
                Greenfoot.setWorld(new StartScreen());
            }
            if (no != null && no.getPressed()) {
                removeObject(no);
                removeObject(yes);
                removeObject(returnToMenu);
                Greenfoot.stop();
            }
        }
    }
    public void spawnReturnScreen() {
        if (!spawnedReturnScreen) {
            yes = new Button("yes.png", 1);
            addObject(yes, 512, 500);
            
            no = new Button("no.png", 1);
            addObject(no, 512, 600);
            
            returnToMenu = new Title("return.png", 1.5);
            addObject(returnToMenu, 512, 275);
        }
    }
}
