/**
 * Interface for all the 'listener' classes of player events (ex: when player completes maze, when player dies).
 * 
 * Uses concept of event listening (learned from ChatGPT).
 * 
 * @author Julia
 * @version Jun 2025
 */
public interface PlayerEventListener  
{
    public void onMazeComplete();
    
    public void onPlayerDeath();
    
    public void onCoinCollected();
    
    public void onPlayerMoved();
}
