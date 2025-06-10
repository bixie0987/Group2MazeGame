import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndBlock extends RoomBlock
{
    public EndBlock(int mazeX, int mazeY) {
        super(mazeX, mazeY);
        image.setColor(Color.GREEN);
        image.fill();
        if (MyWorld.SHOW_CELL_BORDERS){
            image.setColor(Color.BLACK);
            image.drawRect(0,0, MyWorld.BLOCK_SIZE - 2, MyWorld.BLOCK_SIZE-2);
        }
        setImage(image);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
