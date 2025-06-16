import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


/**
 * Write a description of class Enemy here.
 * 
 * @author Yuvia
 * @version June 2025
 */
public class Enemy extends Actor
{   
    private MyWorld world;
    private Player player;
    private int speed = 9; // move 1 block at a time
    private int shootingRange = 2;
    private int shootCooldown = 0;


    public Enemy(Player p) {
        this.player = p;
        GreenfootImage img = new GreenfootImage("enemy_placeholder.png"); 
        img.scale(20, 20);
        setImage(img);
    }

    public void addedToWorld(World w) {
        world = (MyWorld) w;
    }

    public void act() {
        moveTowardPlayer();
    }

    private void moveTowardPlayer() {
        int currentX = MyWorld.getXCell(getX());
        int currentY = MyWorld.getYCell(getY());
        int targetGridX = player.getGridX();
        int targetGridY = player.getGridY();

        int distance = Math.abs(currentX - targetGridX) + Math.abs(currentY - targetGridY);
         if (distance <= shootingRange) {
        // Within range → shoot
        shootAtPlayer();
        } else {
            // Move toward player
            int[] next = bfsToPlayer(currentX, currentY, targetGridX, targetGridY);
            if (next != null) {
                if (getWorld().getObjectsAt(next[0], next[1], Enemy.class).isEmpty()) {
                    setLocation(next[0], next[1]);
                }
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
            
            shootCooldown = 50; // cooldown time in frames (adjust as needed)
        } else {
            shootCooldown--;
        }
}

}
