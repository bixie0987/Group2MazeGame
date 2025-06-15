import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HighScoreScreen here.
 * 
 * @author Julia 
 * @version Jun 2025
 */
public class HighScoreScreen extends Actor
{
    private GreenfootImage img;
    
    // High score variables
    private int highScore, highScoreCoins;
    
    // Your score variables
    private int score, coins;
    
    // Icons
    private GreenfootImage coinsIconImage;
    
    private int SCORE_SIZE = 30; // 40
    private Font SCORE_FONT = new Font("MS UI Gothic", SCORE_SIZE);
    private int TITLE_SIZE = 40;
    private Font TITLE_FONT = new Font("MS UI Gothic", true, false, TITLE_SIZE); // true = bold, false = not italics
    private Font XP_ICON_FONT = new Font("Impact", SCORE_SIZE);
    private int NEW_HIGH_SCORE_SIZE = 50;
    private Font NEW_HIGH_SCORE_FONT = new Font("MS UI Gothic", true, false, NEW_HIGH_SCORE_SIZE); // true = bold, false = not italics
    
    public HighScoreScreen(World w) {
        int width = 800; // adjust this!!
        int height = 500;
        img = new GreenfootImage(width, height);
        
        // Background (optional translucent black)
        img.setColor(new Color(0, 0, 0, 180));
        img.fill();
        
        // HIGH SCORE TITLE ----
        int hsTitleX = 60;
        int hsTitleY = 80;
        
        // Set font
        img.setFont(TITLE_FONT);
        img.setColor(Color.WHITE);
        
        // Draw title
        img.drawString("HIGH SCORES", hsTitleX, hsTitleY);
        
        // HIGH SCORE DISPLAY ----
        // Set font
        img.setFont(SCORE_FONT);
        img.setColor(Color.WHITE);
        
        // Set high score values (taken from PlayerData)
        highScore = PlayerData.getInstance().highScore;
        highScoreCoins = PlayerData.getInstance().highScoreCoins;
        
        // Set icon images
        coinsIconImage = new GreenfootImage("coin.png");
        coinsIconImage.scale((int)(coinsIconImage.getWidth()*1.5), (int)(coinsIconImage.getHeight()*1.5));
        
        int hsX = hsTitleX + 10; // x-pos of column
        int hsY = hsTitleY + 70; // y-pos of highScore
        int hscY = hsTitleY + 115; // y-pos of highScoreCoins
        img.drawString("High score: " + highScore, hsX, hsY); // score text
        img.drawString("High coins: " + highScoreCoins, hsX, hscY); // coins text
        img.drawImage(coinsIconImage, hsX+250, hscY-30); // coins icon
        img.drawString("XP", hsX+250, hsY); // score icon
        
        // YOUR SCORE TITLE ----
        int sTitleX = hsTitleX + 380;
        int sTitleY = hsTitleY;
        
        // Set font
        img.setFont(TITLE_FONT);
        img.setColor(Color.WHITE);
        
        // Draw title
        img.drawString("YOUR SCORES", sTitleX, sTitleY);
        
        // YOUR SCORE DISPLAY ----
        // Set font
        img.setFont(SCORE_FONT);
        img.setColor(Color.WHITE);
        
        // Set your score values (taken from GameManager)
        score = GameManager.getInstance().score;
        coins = GameManager.getInstance().coins;
        
        int sX = sTitleX + 10; // x-pos of column
        int sY = sTitleY + 70; // y-pos of score
        int scY = sTitleY + 115; // y-pos of coins
        img.drawString("Your score: " + score, sX, sY); // score text
        img.drawString("Your coins: " + coins, sX, scY); // coins text
        img.drawImage(coinsIconImage, sX+250, scY-30); // coins icon
        img.drawString("XP", sX+250, sY); // score icon
        
        setImage(img);
        
        newHighScore();
        newCoinRecord();
    }
    
    public void setTransparency(int transparency) {
        img.setTransparency(transparency);
        setImage(img);
    }
    
    public void newHighScore() {
        // Set font
        img.setFont(NEW_HIGH_SCORE_FONT);
        img.setColor(Color.WHITE);
        
        // Draw text
        img.drawString("NEW HIGH SCORE!", img.getWidth()/2-230, img.getHeight()-70);
        
        setImage(img);
    }
    
    public void newCoinRecord() {
        // Set font
        img.setFont(NEW_HIGH_SCORE_FONT);
        img.setColor(Color.WHITE);
        
        // Draw text
        img.drawString("NEW COIN RECORD!", img.getWidth()/2-250, img.getHeight()-1);
        
        setImage(img);
    }
}
