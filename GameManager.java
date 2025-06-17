import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manages game overall, oversees game flow.
 * Useful bc maze will reload (therefore MyWorld is reinstantiated for every maze), but this doesn't!
 * I made this a singleton (aka only one instance of GameManager exists, global access to it)
 * 
 * Sources I used to learn singleton:
 * ChatGPT, GeeksForGeeks (https://www.geeksforgeeks.org/singleton-class-java/)
 * 
 * @author Julia
 * @version June 2025
 */
public class GameManager implements PlayerEventListener
{
    // Private static instance (GameManager creates its own instance, other classes use getter to access this instance)
    private static GameManager instance;
    
    // Variables are public
    public int coins; // convert into score
    public int coinWorth; // value of each coin, greater value converts to greater score; increases as mazeNumber increases
    public int score;
    public int mazeNumber;
    public int enemyDamage = 4;
    
    public boolean beatHighScore = false;
    public boolean beatHighCoins = false;
    
    private GameManager() {
        // Set initial variable values
        coins = 0;
        coinWorth = 1;
        score = 0;
        mazeNumber = 0;
        
        System.out.println("GameManager constructor"); // TESTING
        
        // Load player data (this line is currently the 1st time that PlayerData is called, so this is where PlayerData instance is created.)
        // (Later, when working on UI, if high score is displayed at the BEGINNING, then move this line there)
        // (... since GameManager is currently first called/created by MyWorld at the end of the first maze)
        PlayerData.getInstance().loadData(); // MOVE THIS LATER TO UI CLASS!!!!!!!11
    }
    
    /**
     * Public getter for one-and-only instance.
     * Other classes access GameManager's variables/methods through this getter.
     */
    public static GameManager getInstance() {
        if(instance == null) {
            instance = new GameManager(); // 'lazy instantiation' - create instance only when getInstance() is called (when it's needed!)
        }
        
        return instance;
    }
    
    /**
     * Resets this instance manually (since singleton classes won't be reset automatically when you press 'reset' on Greenfoot).
     */
    public static void resetInstance() {
        instance = null;
    }
    
    /**
     * Runs when player reaches end of maze. -> Generates new maze, modifies some stats.
     * Overrides method in PlayerEventListener interface.
     */
    @Override
    public void onMazeComplete() {
        // Generate new maze
        Greenfoot.setWorld(new MyWorld());
        
        mazeNumber++;
        coinWorth++;
        enemyDamage+=2;
    }
    
    /**
     * Runs when player dies. -> Saves data
     * Overrides method in PlayerEventListener interface.
     */
    @Override
    public void onPlayerDeath() {
        System.out.println("inside onPlayerDeath()");
        
        Sounds.getInstance().playSounds(Sounds.PLAYER_DEATH);
        // Save data (score + coins) - but check if high scores are higher first
        if(score > PlayerData.getInstance().highScore) {
            System.out.println("score > highScore");
            System.out.println("highScore: " + PlayerData.getInstance().highScore);
            System.out.println("score: " + score);
            PlayerData.getInstance().highScore = score;
            beatHighScore = true;
            
            PlayerData.getInstance().saveData();
        } else {
            System.out.println("score not higher");
        }
        if(coins > PlayerData.getInstance().highScoreCoins) {
            System.out.println("coins > highCoins");
            System.out.println("highCoins: " + PlayerData.getInstance().highScoreCoins);
            System.out.println("coins: " + coins);
            PlayerData.getInstance().highScoreCoins = coins;
            beatHighCoins = true;
            
            PlayerData.getInstance().saveData();
        } else {
            System.out.println("coins not higher");
        }
        
        // Switch to end screen
        Greenfoot.setWorld(new EndScreen());
        
        //Stops playing background music
        Sounds.getInstance().stopBackgroundMusic();
        Sounds.getInstance().stopMonsterFootsteps();
        
        
        // Reset instance!
        resetInstance();
    }
    
    /**
     * Runs when player collects a coin.
     * Overrides method in PlayerEventListener interface.
     */
    @Override
    public void onCoinCollected() {
        // Increase coins and score
        coins++;
        score += coinWorth;
        
        // ScoreDisplay is updated in MyWorld
    }
}
