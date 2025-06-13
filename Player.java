import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Player here.
 * 
 * @author Chin-En, Julia
 * @version Jun 2025
 */
public class Player extends Actor
{
    
    private int gridX;
    private int gridY;
    
    // Create listener that 'listens' to notifs of player events - ex: player completed maze, player died
    // listener is instantiated in setter, to be called by another class ()
    private PlayerEventListener listener;
    
    private int health;
    
    public Player()
    {
        GreenfootImage image = new GreenfootImage(MyWorld.BLOCK_SIZE, MyWorld.BLOCK_SIZE);
        image.setColor(Color.BLUE);
        image.fill();
        setImage(image);
    }
    
    public void setEventListener(PlayerEventListener listener) {
        this.listener = listener;
    }
    
    @Override
    protected void addedToWorld(World world) {
        this.gridX = MyWorld.getXCell(getX());
        this.gridY = MyWorld.getYCell(getY());
    }
    public void act() {
        handleMovement();
        
        // Check if Player touches EndBlock. If so, notify all listeners of maze completion, make them run onMazeComplete()
        if(isTouching(EndBlock.class)) {
            if(listener != null) {
                listener.onMazeComplete();
            }
        }
        
        // Check if Player is dead. If os, notify all listeners of player death, make them run onPlayerDeath()
        if(health == 0) {
            listener.onPlayerDeath();
        }
    }
    private void handleMovement() {
        String key = Greenfoot.getKey();
        if (key == null) {
            return;
        }
        
        int targetGridX = gridX;
        int targetGridY = gridY;
        
        switch (key) {
            case "w":
                targetGridY--;
                break;
            case "s":
                targetGridY++;
                break;
            case "a":
                targetGridX--;
                break;
            case "d":
                targetGridX++;
                break;
            default:
                return;
        }
        MyWorld world = (MyWorld) getWorld();
        if (world.isPath(targetGridX, targetGridY)) {
            gridX = targetGridX;
            gridY = targetGridY;
            
            setLocation(MyWorld.getXCoordinate(gridX), MyWorld.getYCoordinate(gridY));
        }
    }
    public void checkKeys() {
        int speed = 1;
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - speed);
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + speed);
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + speed, getY());
        }
    }
    
    /**
    public boolean getEndBlockReached() {
        return endBlockReached;
    }
    **/
    
    public ArrayList<Lighting> getNearbyShaders(){
        //return arraylist of surrounding shaders within a certain radius
        return (ArrayList<Lighting>)getObjectsInRange(40, Lighting.class);
    }
    public ArrayList<Lighting> getFurtherShaders(){
        //return arraylist of surrounding shaders within a certain radius
        return (ArrayList<Lighting>)getObjectsInRange(90, Lighting.class);
    }
    
    // getter
    public int getGridX() {
        return gridX;
    }
    
    // getter
    public int getGridY() {
        return gridY;
    }
}
