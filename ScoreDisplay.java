import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manages on-screen score display.
 * 
 * @author Julia
 * @version Jun 2025
 */
public class ScoreDisplay
{
    private int highScore, highScoreCoins, currScore, currCoins;
    private TextLabel highScoreLabel, highScoreCoinsLabel, currScoreLabel, currCoinsLabel;
    
    private Color SCORE_COLOUR = Color.WHITE;
    private int SCORE_SIZE = 30;
    
    // General position coordinates of entire score display
    int X;
    int Y;
    
    public ScoreDisplay(World w) {
        // Set general position coordinates
        X = w.getWidth() - 100;
        Y = 50;
        
        // Set high score values (taken from PlayerData)
        highScore = PlayerData.getInstance().highScore;
        highScoreCoins = PlayerData.getInstance().highScoreCoins;
        
        // Set current score values (taken from GameManager)
        currScore = GameManager.getInstance().score;
        currCoins = GameManager.getInstance().coins;
        
        // Set Labels
        highScoreLabel = new TextLabel("High: " + highScore + " points", SCORE_SIZE, SCORE_COLOUR);
        highScoreCoinsLabel = new TextLabel("High: " + highScoreCoins + " coins", SCORE_SIZE, SCORE_COLOUR);
        currScoreLabel = new TextLabel("Curr: " + currScore + " points", SCORE_SIZE, SCORE_COLOUR);
        currCoinsLabel = new TextLabel("Curr: " + currCoins + " coins", SCORE_SIZE, SCORE_COLOUR);
        
        // Add textLabel objects
        w.addObject(highScoreLabel, X, Y);
        w.addObject(highScoreCoinsLabel, X, Y + 20);
        w.addObject(currScoreLabel, X, Y + 40);
        w.addObject(currCoinsLabel, X, Y + 60);
    }
    
    public void updateScoreDisplay(int newScore, int newCoins) {
        currScoreLabel.updateText(String.valueOf(newScore));
        currCoinsLabel.updateText(String.valueOf(newCoins));
    }
}
