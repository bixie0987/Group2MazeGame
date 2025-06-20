import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


/**
 * Enemy class represents a hostile character that tracks and shoots at the player.
 * 
 * Tracks the player using a BFS pathfinding algorithm.
 * Shoots when the player is within a certain range.
 * Has a health bar that decreases when hit by bullets.
 * Enemy health increases with wave number (max capped at 100).
 * 
 * 
 * @author Yuvia, Julia
 * @version June 2025
 */
public class Enemy extends Actor implements PlayerEventListener
{   
    private int gridX;
    private int gridY;
    private MyWorld world;
    private Player player;
    private int speed = 5; // move 1 block at a time
    private int shootingRange = 2;
    private int shootCooldown = 0;
    private SuperStatBar healthBar;

    private int health;

    /**
     * Constructor initializes enemy with a reference to the player and sets health based on wave number.
     */
    public Enemy(Player p, int waveNumber) {
        this.player = p;
        GreenfootImage img = new GreenfootImage("ghost.png"); 
        img.scale(40, 40);
        setImage(img);
        
        // Set health based on wave number
        int baseHP = 30 + 20 * (waveNumber - 1);
        if (baseHP > 100) baseHP = 100;
        this.health = baseHP;
    }

    /**
     * Called automatically when the enemy is added to the world.
     * Sets up the health bar and initializes grid position.
     */
    @Override
    protected void addedToWorld(World world) {
        this.world = (MyWorld) world;
        int barWidth = getImage().getWidth();
        healthBar = new SuperStatBar(100, 100, this, barWidth, 6, -20); // -30 = above player
        world.addObject(healthBar, getX(), getY() - 20);
        
        this.gridX = MyWorld.getXCell(getX());
        this.gridY = MyWorld.getYCell(getY());
    }
    
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;

        if (healthBar != null) {
            healthBar.update(health);
        }
        
        if (health == 0) {
            // Remove this enemy from being a listener for PlayerEventListener
            player.removeEventListener(this);
            
            getWorld().removeObject(this);
        }
    }

    public void act() {
        
    }
    
    /**
     * Empty method lol ignore this.
     */
    @Override
    public void onMazeComplete() {}
    
    /**
     * Empty method lol ignore this.
     */
    @Override
    public void onPlayerDeath() {}
    
    /**
     * Empty method lol ignore this.
     */
    @Override
    public void onCoinCollected() {}
    
    /**
     * Runs each time a player moves 1 step. -> Pathfinds towards player by 1 step
     */
    @Override
    public void onPlayerMoved() {
        moveTowardPlayer();
    }

    private void moveTowardPlayer() {
        int currentX = MyWorld.getXCell(getX());
        int currentY = MyWorld.getYCell(getY());
        int targetGridX = player.getGridX();
        int targetGridY = player.getGridY();

   
       
        // Shoot at player
        int distance = Math.abs(currentX - targetGridX) + Math.abs(currentY - targetGridY);
        if (distance <= shootingRange) {
            // Within range → shoot
            shootAtPlayer();
        }
        
        // Move toward player
        int[] next = bfsToPlayer(currentX, currentY, targetGridX, targetGridY);
        if (next != null) {
            if (getWorld().getObjectsAt(next[0], next[1], Enemy.class).isEmpty()) {
                setLocation(next[0], next[1]);
            }
        }
    
    }
    
    private int[] bfsToPlayer(int startGridX, int startGridY, int targetGridX, int targetGridY) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, int[]> parentMap = new HashMap<>();
    
        queue.add(new int[] {startGridX, startGridY});
        visited.add(startGridX + "," + startGridY);
    
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
    
            if (x == targetGridX && y == targetGridY) {
                // Backtrack to next step
                while (parentMap.containsKey(x + "," + y) && !(x == startGridX && y == startGridY)) {
                    int[] parent = parentMap.get(x + "," + y);
                    if (parent[0] == startGridX && parent[1] == startGridY) break;
                    x = parent[0];
                    y = parent[1];
                }
                return new int[] {MyWorld.getXCoordinate(x), MyWorld.getYCoordinate(y)};
            }
    
            // Explore 4 directions
            for (int[] dir : new int[][] {{-1,0},{1,0},{0,-1},{0,1}}) {
                int nx = x + dir[0], ny = y + dir[1];
                if (!visited.contains(nx + "," + ny) && world.isPath(nx, ny)) {
                    queue.add(new int[] {nx, ny});
                    visited.add(nx + "," + ny);
                    parentMap.put(nx + "," + ny, new int[] {x, y});
                }
            }
        }
    
        return null;

    }

    private boolean isPassable(int x, int y) {
        int gridX = MyWorld.getXCell(x) + (x - MyWorld.X_OFFSET) / MyWorld.BLOCK_SIZE;
        int gridY = MyWorld.getYCell(y) + (y - MyWorld.Y_OFFSET) / MyWorld.BLOCK_SIZE;
    
        if (gridX < 0 || gridX >= MyWorld.BLOCKS_WIDE || gridY < 0 || gridY >= MyWorld.BLOCKS_HIGH) {
            return false;
        }
    
        Block[][] grid = world.getGrid();
        Block block = grid[gridX][gridY];
        return block.isPassable();
    }
    
    private void shootAtPlayer() {
        if (shootCooldown == 0) {
            // Spawn a Bullet object toward the player
            Bullet bullet = new Bullet(getX(), getY(), player.getX(), player.getY());
            MyWorld world = (MyWorld) getWorld();
            world.addObject(bullet, getX(), getY());
            bullet.setLocation(getX(), getY()); // put bullet at correct location
            
            shootCooldown = 50; // cooldown time in frames 
        } else {
            shootCooldown--;
        }
    }

}
