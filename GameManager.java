import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manages game overall, transitioning to new levels
 * I tried to make this a singleton! (aka only one instance of GameManager exists, global access to it)
 * 
 * Sources I used to learn this:
 * ChatGPT, GeeksForGeeks (https://www.geeksforgeeks.org/singleton-class-java/)
 * 
 * @author Julia
 * @version Jun 2025
 */
public class GameManager
{
    // Private static instance (GameManager creates its own instance, other classes use getter to access this instance)
    private static GameManager instance = new GameManager();
    
    // Variables are public
    public int coins;
    public int mazeNumber;
    
    // Constructor is private - prevents direct instantiation, no one else can make a new constructor
    private GameManager() {
        coins = 0;
        mazeNumber = 0;
    }
    
    /**
     * Public getter for one-and-only instance
     * Other classes access GameManager's variables/methods through this getter
     */
    public static GameManager getInstance() {
        return instance;
    }
    
    /**
     * Runs when player reaches end of maze.
     */
    public void onMazeComplete() {
        // Generate new maze
        Greenfoot.setWorld(new MyWorld());
        
        mazeNumber++;
    }
}
