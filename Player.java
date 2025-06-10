import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    
    private int gridX;
    private int gridY;
    
    private boolean endBlockReached; // true if player has reached endBlock
    
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
        
        // Check if Player touches EndBlock (MyWorld will generate new maze if so)
        if(isTouching(EndBlock.class)) {
            endBlockReached = true;
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
    
    public boolean getEndBlockReached() {
        return endBlockReached;
    }
}
