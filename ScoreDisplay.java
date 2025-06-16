import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manages on-screen score display.
 * 
 * @author Julia
 * @version Jun 2025
 */
public class ScoreDisplay
{
    // For text
    private int score, coins;
    private TextLabel scoreLabel, coinsLabel;
    
    private Color SCORE_COLOUR = Color.WHITE;
    private int SCORE_SIZE = 28;
    private Font SCORE_FONT = new Font("MS UI Gothic", SCORE_SIZE);
    private Font XP_ICON_FONT = new Font("Impact", SCORE_SIZE);
    // Impact, Lucida Console, Consolas, Courier New
    
    
    // For icons
    private GreenfootImage coinsIconImage;
    private ImageLabel coinsIcon;
    private TextLabel scoreIcon;
    
    // General position coordinates of entire score display
    int X;
    int Y;
    
    public ScoreDisplay(World w) {
        // Set general position coordinates
        X = 900;
        Y = 23;
        
        // Set current score values (taken from GameManager)
        score = GameManager.getInstance().score;
        coins = GameManager.getInstance().coins;
        
        // Set Labels
        scoreLabel = new TextLabel(String.valueOf(score), SCORE_SIZE, SCORE_COLOUR, SCORE_FONT);
        coinsLabel = new TextLabel(String.valueOf(coins), SCORE_SIZE, SCORE_COLOUR, SCORE_FONT);
        
        // Set icons
        coinsIconImage = new GreenfootImage("coin.png");
        coinsIconImage.scale((int)(coinsIconImage.getWidth()*1.5), (int)(coinsIconImage.getHeight()*1.5));
        coinsIcon = new ImageLabel(coinsIconImage);
        scoreIcon = new TextLabel("XP", SCORE_SIZE, SCORE_COLOUR, XP_ICON_FONT);
        
        // Add textLabel and icon objects
        int scoreX = X + 80;
        int coinsX = X - 80;
        w.addObject(scoreLabel, scoreX, Y);
        w.addObject(coinsLabel, coinsX, Y);
        w.addObject(scoreIcon, scoreX - 30, Y);        
        w.addObject(coinsIcon, coinsX - 50, Y);
    }
    
    public void updateScoreDisplay(int newScore, int newCoins) {
        scoreLabel.updateText(String.valueOf(newScore));
        coinsLabel.updateText(String.valueOf(newCoins));
    }
}
