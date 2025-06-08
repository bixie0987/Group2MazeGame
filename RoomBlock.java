import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RoomBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoomBlock extends Block
{

    private boolean visited;

    public RoomBlock (int mazeX, int mazeY){

        super(true, mazeX, mazeY);
        visited = false;
        image.setColor(Color.LIGHT_GRAY);
        image.fill();
        if (MyWorld.SHOW_CELL_BORDERS){
            image.setColor(Color.BLACK);
            image.drawRect(0,0, MyWorld.BLOCK_SIZE - 2, MyWorld.BLOCK_SIZE-2);
        }
        setImage(image);
    }

    public void visit(){
        visited = true;
    }

    public boolean visited () {
        return visited;
    }
    
    public void setStartBlock (){
        image.setColor(Color.RED);
        image.fill();
        if (MyWorld.SHOW_CELL_BORDERS){
            image.setColor(Color.BLACK);
            image.drawRect(0,0, MyWorld.BLOCK_SIZE - 2, MyWorld.BLOCK_SIZE-2);
        }
        setImage(image);
    }
    
    public void setEndBlock (){
        image.setColor(Color.GREEN);
        image.fill();
        if (MyWorld.SHOW_CELL_BORDERS){
            image.setColor(Color.BLACK);
            image.drawRect(0,0, MyWorld.BLOCK_SIZE - 2, MyWorld.BLOCK_SIZE-2);
        }
        setImage(image);
    }

}
