import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the non-abstract superclass of Block. 
 * <p>An instantiated Block is a generic block, and the subclasses are special
 * blocks
 * 
 * @author Jordan Cohen
 * @version 0.1
 */
public class Block extends Actor
{
    protected boolean passable;
    protected GreenfootImage image;
    protected int mazeX, mazeY;
    
    public Block (boolean passable, int mazeX, int mazeY){
        this.passable = passable;
        this.mazeX = mazeX;
        this.mazeY = mazeY;
        
        image = new GreenfootImage (MyWorld.BLOCK_SIZE, MyWorld.BLOCK_SIZE);
    }
    
    public int getMazeX () {
        return mazeX;
    }
    
    public int getMazeY () {
        return mazeY;
    
    }
    
    public boolean isPassable() {
        return passable;
    }
    
}
