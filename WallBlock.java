import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WallBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WallBlock extends Block
{

    public WallBlock(int mazeX, int mazeY){
        super(false, mazeX, mazeY);
        image.setColor(Color.DARK_GRAY);
        image.fill();
        if (Maze.SHOW_CELL_BORDERS){
            image.setColor(Color.BLACK);
            image.drawRect(0,0, Maze.BLOCK_SIZE - 2, Maze.BLOCK_SIZE-2);
        }
        setImage(image);
    }

    public void act()
    {
        // Add your action code here.
    }

    public void open () {
        passable = true; 
        image.setColor(Color.LIGHT_GRAY);
        image.fill();
        if (Maze.SHOW_CELL_BORDERS){
            image.setColor(Color.BLACK);
            image.drawRect(0,0, Maze.BLOCK_SIZE - 2, Maze.BLOCK_SIZE-2);
        }
        setImage(image);
    }

    public boolean isPassable(){     
        return passable;
    }

}
