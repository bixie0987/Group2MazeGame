import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends World
{
    private Title death;
    private int fadeTimer = 0;
    private boolean spawned = false;
    private Button yes;
    private Button no;
    private Title returnToMenu;
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
    }
    public void act() {
        if (fadeTimer < 225) {
            fadeTimer += 5;
            death.setTransparency(Math.min(fadeTimer, 255));
        } else if (fadeTimer >= 225 && fadeTimer < 1200) {
            fadeTimer += 5;
        } else {
            spawn();
            spawned = true;
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
    public void spawn() {
        if (!spawned) {
            removeObject(death);
            
            yes = new Button("yes.png", 1);
            addObject(yes, 512, 500);
            
            no = new Button("no.png", 1);
            addObject(no, 512, 600);
            
            returnToMenu = new Title("return.png", 1.5);
            addObject(returnToMenu, 512, 275);
        }
    }
}
