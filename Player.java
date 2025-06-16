import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Player here.
 * 
 * @author Chin-En, Julia, Elise, Yuvia
 * @version Jun 2025
 */
public class Player extends Actor
{
    private int gridX;
    private int gridY;

    //variables for animation of player movement
    private int frame=0;
    //image of all walking frames
    private GreenfootImage spriteSheet = new GreenfootImage("walk.png");
    private Direction direction;
    private Animation animation;
    private GreenfootImage playerImage = new GreenfootImage("walk.png");

    // Create listener that 'listens' to notifs of player events - ex: player completed maze, player died
    // listener is instantiated in setter, to be called by another class ()
    private PlayerEventListener listener;

    private SuperStatBar healthBar;

    private int health = 100; //100
    private int shortRange = 40;
    private int midRange = 90;
    private int farRange = 120;

    public Player(){
        direction = Direction.RIGHT;
        //create animation
        animation = AnimationManager.createAnimation(spriteSheet,1,4,9,64, 64);
        //get one image from animation
        playerImage = (GreenfootImage)animation.getOneImage(direction, frame);
        setImage(playerImage);
    }

    public void setEventListener(PlayerEventListener listener) {
        this.listener = listener;
    }

    @Override
    protected void addedToWorld(World world) {
        int barWidth = getImage().getWidth();
        healthBar = new SuperStatBar(100, 100, this, barWidth, 6, -20); // -30 = above player
        world.addObject(healthBar, getX(), getY() - 20);
        this.gridX = MyWorld.getXCell(getX());
        this.gridY = MyWorld.getYCell(getY());
    }

    public void act() {
        handleMovement();
        setImage(animation.getOneImage(direction,frame));
        //Resets frame back to 0 when animation is over
        if(frame==8){
            frame=0;
        }

        //Check for nearby monsters and play corresponding sound effect
        //if there are monsters within 40m radius, play footsteps at high volume
        if(monsterNearby()){
            Sounds.getInstance().changeStepsVolume(90);
            //System.out.println("near");
        }
        else if(monsterFarAway()){
            Sounds.getInstance().changeStepsVolume(40);
            //System.out.println("far");
        }
        else{
            Sounds.getInstance().changeStepsVolume(0);
            System.out.println("0");
        }

        // Check if Player is dead. If so, notify all listeners of player death, make them run onPlayerDeath()
        if(health == 0) {
            listener.onPlayerDeath();
        }

        if (healthBar != null) {
            healthBar.moveMe();
        }

        // Check if Player touches EndBlock. If so, notify all listeners of maze completion, make them run onMazeComplete()
        if(isTouching(EndBlock.class)) {
            if(listener != null) {
                listener.onMazeComplete();
            }
        }
        
        // Check if Player touches coin. If so, notify all listeners of coin collection, make them run onCoinCollected()
        if(isTouching(Coins.class)) {
            if(listener != null) {
                listener.onCoinCollected();
            }
        }

        if(isTouching(Lantern.class)){
            shortRange += 50;
            midRange += 50;
            farRange += 50;
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

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;

        if (healthBar != null) {
            healthBar.update(health);
        }


        // Check if Player is dead. If os, notify all listeners of player death, make them run onPlayerDeath()
        if (health == 0 && listener != null) {
            listener.onPlayerDeath();
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
        return (ArrayList<Lighting>)getObjectsInRange(shortRange, Lighting.class);
    }

    public boolean monsterNearby(){
        // returns true if there is no enemy within 80 pixels
        return !getObjectsInRange(80, Enemy.class).isEmpty();
    }

    public boolean monsterFarAway(){
        // returns true if there is no enemy within 120 pixels
        return !getObjectsInRange(120, Enemy.class).isEmpty();
    }

    public ArrayList<Lighting> getFurtherShaders(){
        //return arraylist of surrounding shaders within a certain radius
        return (ArrayList<Lighting>)getObjectsInRange(midRange, Lighting.class);
    }

    public ArrayList<Lighting> getEvenFurtherShaders(){
        //return arraylist of surrounding shaders within a certain radius
        return (ArrayList<Lighting>)getObjectsInRange(farRange, Lighting.class);
    }

    /*
    public ArrayList<Lighting> getNearbyShadersLantern(){
    //return arraylist of surrounding shaders within a certain radius
    return (ArrayList<Lighting>)getObjectsInRange(55, Lighting.class);
    }
    public ArrayList<Lighting> getFurtherShadersLantern(){
    //return arraylist of surrounding shaders within a certain radius
    return (ArrayList<Lighting>)getObjectsInRange(105, Lighting.class);
    }
    public ArrayList<Lighting> getEvenFurtherShadersLantern(){
    //return arraylist of surrounding shaders within a certain radius
    return (ArrayList<Lighting>)getObjectsInRange(135, Lighting.class);
    }
     */

    // getter
    public int getGridX() {
        return gridX;
    }

    // getter
    public int getGridY() {
        return gridY;
    }
}
