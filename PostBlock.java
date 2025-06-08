import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PostBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PostBlock extends Block
{
    public PostBlock (int mazeX, int mazeY){

        super(false, mazeX, mazeY);
        image.setColor(Color.DARK_GRAY);
        image.fill();
        if (Maze.SHOW_CELL_BORDERS){
            image.setColor(Color.RED);
            image.drawRect(0,0, Maze.BLOCK_SIZE - 2, Maze.BLOCK_SIZE-2);
        }
        setImage(image);
    }

    /**
     * Act - do whatever the PostBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
