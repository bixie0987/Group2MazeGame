/**
 * Interface for all the 'listener' classes of maze completion - classes that want to be notified when player completes maze.
 * 
 * Used concept of event listening (learned from ChatGPT).
 * 
 * @author Julia
 * @version Jun 2025
 */
public interface PlayerEventListener  
{
    public void onMazeComplete();
}
