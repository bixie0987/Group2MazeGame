import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instruction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instruction extends World
{
    private Button back;
    private Title text1;
    /**
     * Constructor for objects of class Instruction.
     * 
     */
    public Instruction()
    {    
        super(1024, 800, 1); 
        
        getBackground().setColor(Color.BLACK);
        getBackground().fill();
        
        text1 = new Title("helpTXT1.png", 0.5);
        addObject(text1, 256, 250);
        
        back = new Button("backButton.png", 1);
        addObject(back, 512, 600);
    }
    public void act() {
        if (back != null && back.getPressed()) {
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
