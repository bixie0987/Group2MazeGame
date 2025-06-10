import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{   
    private MyWorld world;
    private Player player;
    private int speed = 18; // move 1 block at a time

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

        int[] next = bfsToPlayer(currentX, currentY, targetGridX, targetGridY);
        if (next != null) {
            setLocation(next[0], next[1]);
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

}
