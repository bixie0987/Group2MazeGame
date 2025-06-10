import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int gridX;
    private int gridY;
    public Player()
    {
        GreenfootImage image = new GreenfootImage(MyWorld.BLOCK_SIZE, MyWorld.BLOCK_SIZE);
        image.setColor(Color.BLUE);
        image.fill();
        setImage(image);
    }
    
    @Override
    protected void addedToWorld(World world) {
        this.gridX = MyWorld.getXCell(getX());
        this.gridY = MyWorld.getYCell(getY());
    }
    public void act() {
        handleMovement();
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

        // Generate new maze (aka re-instantiate MyWorld) if Player touches EndBlock
        if(isTouching(EndBlock.class)) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
    
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
