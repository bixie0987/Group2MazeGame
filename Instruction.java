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
    private Title text2;
    private Title text3;
    private Title text4;
    private Title player;
    private Title enemy;
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
        addObject(text1, 256, 300);
        
        text2 = new Title("helpTXT2.png", 0.3);
        addObject(text2, 768, 300);
        
        text3 = new Title("helpTXT3.png", 0.25);
        addObject(text3, 256, 600);
        
        text4 = new Title("helpTXT4.png", 0.32);
        addObject(text4, 768, 600);
        
        player = new Title("singular player.png", 1);
        addObject(player, 256, 150);
        
        addObject(new Title("bullet.png", 2), 680, 150);
        addObject(new Title("bullet.png", 2), 720, 150);
        addObject(new Title("bullet.png", 2), 760, 150);
    
        enemy = new Title("ghost.png", 0.2);
        addObject(enemy, 830, 150);
        
        addObject(new Title("heart.png", 0.2), 256, 500);
        addObject(new Title("coin.png", 3), 156, 500);
        addObject(new Title("lantern.png", 3), 356, 500);
        
        addObject(new Title("endGoal.png", 1), 768, 500);
        
        back = new Button("backButton.png", 1);
        addObject(back, 512, 700);
    }
    public void act() {
        if (back != null && back.getPressed()) {
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
