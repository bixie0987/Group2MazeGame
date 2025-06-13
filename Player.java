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
    
    //variables for animation of player movement
    private int frame=0;
    private int actsPassed = 0;
    //image of all walking frames
    private GreenfootImage spriteSheet = new GreenfootImage("walk.png");
    private Direction direction;
    private Animation animation;
    public Player(){
        direction = Direction.RIGHT;
        animation = AnimationManager.createAnimation(spriteSheet,1,4,9,64, 64);
        setImage(animation.getOneImage(direction, frame));
    }
    
    @Override
    protected void addedToWorld(World world) {
        this.gridX = MyWorld.getXCell(getX());
        this.gridY = MyWorld.getYCell(getY());
    }
    public void act() {
        handleMovement();
        setImage(animation.getOneImage(direction,frame));
        if(frame==8){
            frame=0;
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
                frame++;
                direction = Direction.UP;
                break;
            case "s":
                targetGridY++;
                frame++;
                direction = Direction.DOWN;
                break;
            case "a":
                targetGridX--;
                frame++;
                direction = Direction.LEFT;
                break;
            case "d":
                targetGridX++;
                frame++;
                direction = Direction.RIGHT;
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
}
