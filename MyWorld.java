import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;

/**
 * A Maze Drawing World.
 * 
 * Implemented Prim's Algorithm (Credit: Robert C Prim, 1957)
 * 
 * Notes:
 * - The algorithm requires all blocks to be either Walls, Posts or Rooms
 * - The algorithm requires the overall grid size to be odd on both axes.
 * - Posts are placed around edges and in every block in the grid where x and y are both EVEN numbers
 * --- Posts are not passable, and cannot be "opened" by the algorithm
 * - Rooms are placed throughout the grid in every block where x and y are both ODD numbers
 * --- Rooms are passable
 * - Walls are placed wherever we do not place a Post
 * --- Walls are not passable, but can be opened, creating an open wall block (which could also be replaced with a Room block if desired)
 * 
 * The layout before Prim looks like:
 * 
 *   0123456
 * 0 OOOOOOO    O = Post
 * 1 O.#.#.O    . = Room
 * 2 O#O#O#O    # = Wall (initially)
 * 3 O.#.#.O
 * 4 O#O#O#O
 * 5 O.#.#.O
 * 6 OOOOOOO
 * 
 * The Prim algorithm will turn some walls into open walls:
 * 
 *   0123456
 * 0 OOOOOOO    O = Post
 * 1 O.#.,.O    . = Room
 * 2 O,O#O,O    # = Wall (initially)
 * 3 O.,.,.O    , = Open Wall
 * 4 O,O,O,O
 * 5 O.#.#.O
 * 6 OOOOOOO
 * 
 * To see this clearly, try changing the SHOW_CELL_BORDERS to true and you can see it on the generated map.
 * 
 * For a better understanding of the algorithm, try setting DEMO_ALGORITHM to true and watch the maze get generated.
 * 
 * MODE -- The MODE constant can be changed to 1 to try a more biased maze. MODE = 0 is a fully random maze. If you want
 * to alter the maze in interesting and creative ways, there are some great ideas in this article:
 * 
 * https://www.gamedeveloper.com/programming/algorithms-for-making-more-interesting-mazes
 * 
 * @author Jordan Cohen
 * @version 1.0, Dec 2022
 */
public class MyWorld extends World
{
    // Constants
    public static final int BLOCK_SIZE = 50;
    public static final int BLOCKS_WIDE = 19; // must be odd
    public static final int BLOCKS_HIGH = 15; // must be odd

    public static final int X_OFFSET = 60;
    public static final int Y_OFFSET = 40;
    public static final int MODE = 0;

    // Help Learning by Visualizing
    public static final boolean SHOW_CELL_BORDERS = false; // help distinguish posts from walls and rooms
    public static final boolean DEMO_ALGORITHM = false; // show the algorithm as it works - this takes lots of time! Only for testing

    // Class Objects and Variables
    private Block[][] theGrid;

    // Create Player
    private Player player = new Player();
    private Lighting[][] shaders;
    private ArrayList<Lighting> allShaders = new ArrayList<Lighting>();
    //arraylist of shaders within a certain radius of player
    private ArrayList<Lighting> shaders1;

    
    // Create ScoreDisplay
    private ScoreDisplay scoreDisplay;
    
    private int enemyWaveCount = 1;
    private int enemySpawnTimer = 0;
    private int spawnDelay = 450; // ~5 seconds if act() is called 60 times/sec
    

    /**
     * Constructor for objects of class Maze.
     * 
     */
    public MyWorld()
    {    
        super(1024, 800, 1); 

        // Init grid based on constants
        theGrid = new Block[BLOCKS_WIDE][BLOCKS_HIGH];

        // Prepare the grid (build Posts, Walls and Rooms)
        prepareGrid();
        // Generate the maze, unless we are in demo mode, in which case
        // we will wait until the started () method (when the user clicks run)
        if (!DEMO_ALGORITHM)
            init();

        spawn();
        setPaintOrder(ImageLabel.class, TextLabel.class, ScoreDisplay.class, Lighting.class, Bullet.class, Enemy.class, Player.class);

    }

    public void spawn(){
        spawnCoins();

        // Create Player, set its event listeners
        addObject(player, getXCoordinate(1), getYCoordinate(1));
        player.addEventListener(GameManager.getInstance());

        //Play background music
        Sounds.getInstance().playBackgroundMusicLoop();
        Sounds.getInstance().pauseBackgroundMusic();
        
        //Play monster footsteps
        Sounds.getInstance().playMonsterFootstepsLoop();
        Sounds.getInstance().pauseMonsterFootsteps();


        buildLighting();
        adjustLighting();

        spawnEnemy();
        spawnLantern();
        spawnHeart();
        
        // Create ScoreDisplay, pass through instance of this world
        scoreDisplay = new ScoreDisplay(this);
        }
    private void spawnCoins()
    {
        int numCoins = 100;
        for(int i = 0; i<numCoins; i++){
            int x = Greenfoot.getRandomNumber(BLOCKS_WIDE);
            int y = Greenfoot.getRandomNumber(BLOCKS_HIGH);
            if(theGrid[x][y] instanceof RoomBlock){
                addObject (new Coins(), getXCoordinate(x), getYCoordinate(y));
            }
        }
    }
    private void spawnLantern()
    {
        int numLanterns = 10;
        for(int i = 0; i<numLanterns; i++){
            int x = Greenfoot.getRandomNumber(BLOCKS_WIDE);
            int y = Greenfoot.getRandomNumber(BLOCKS_HIGH);
            if(theGrid[x][y] instanceof RoomBlock){
                addObject (new Lantern(), getXCoordinate(x), getYCoordinate(y));
            }
        }
    }
    
    public void spawnHeart() {
        int numHeart = 10;
         for(int i = 0; i<numHeart; i++){
            int x = Greenfoot.getRandomNumber(BLOCKS_WIDE);
            int y = Greenfoot.getRandomNumber(BLOCKS_HIGH);
            if(theGrid[x][y] instanceof RoomBlock){
                addObject (new Heart(), getXCoordinate(x), getYCoordinate(y));
            }
        }
    }
    
    public void act() {
        adjustLighting();
        playSoundEffects();
        
         // Spawn enemies after a delay
        enemySpawnTimer++;
        if (enemySpawnTimer >= spawnDelay) {
            spawnEnemyWave();
            enemySpawnTimer = 0;
            enemyWaveCount++; // increase difficulty
        }
        
        scoreDisplay.updateScoreDisplay();
    }
    public void spawnEnemy() {
        int x, y;
        do {
            x = Greenfoot.getRandomNumber(BLOCKS_WIDE);
            y = Greenfoot.getRandomNumber(BLOCKS_HIGH);
        } while (!(theGrid[x][y] instanceof RoomBlock)); // must be a room

        Enemy enemy = new Enemy(player, enemyWaveCount);
        addObject(enemy, getXCoordinate(x), getYCoordinate(y));
        
        // Set enemy to be a Player event listener
        player.addEventListener(enemy);
    }
    public void playSoundEffects(){
        //random sounds that will play
        int randNum = Greenfoot.getRandomNumber(1000);
        if(randNum==0){
            Sounds.getInstance().playSounds(Sounds.HORROR_SWISH);
        }
        //rarer sounds
        randNum = Greenfoot.getRandomNumber(5000);
        if(randNum==1){
            Sounds.getInstance().playSounds(Sounds.SCREAM);
        }
    }

    /**
     * Called when Greenfoot's Run button is pressed. Used to start the init() method if the
     * DEMO is turned on because Greenfoot won't repaint() during World construction.
     */
    public void started() {
        // if demo mode is enabled, don't initialize the map until the Run button is clicked
        if (DEMO_ALGORITHM){
            init();
        }
        Sounds.getInstance().playBackgroundMusicLoop();
        Sounds.getInstance().playMonsterFootstepsLoop();
    }

    public void stopped(){
        Sounds.getInstance().pauseBackgroundMusic();
        Sounds.getInstance().pauseMonsterFootsteps();
    }
    

    /**
     * Uses shaders to build a grid of dark lighting, with shaders around the player getting increasingly lighter
     */
    public void buildLighting(){

        Lighting lightingSquare = new Lighting();

        //calculates number of horizontal and vertical squares needed to fill the world, +1 for overflow
        int numOfHorizontalSquares = getWidth() / lightingSquare.getImage().getWidth() +1;
        int numOfVerticalSquares = getHeight() / lightingSquare.getImage().getHeight() +1;

        //starting place to spawn shaders
        int xPos = 0 + lightingSquare.getImage().getWidth()/2;
        int yPos = 0 + lightingSquare.getImage().getHeight()/2;

        //2d array holding shaders
        shaders = new Lighting[numOfVerticalSquares][numOfHorizontalSquares];

        //fills up the 2d array with shaders
        for(int i=0; i<numOfVerticalSquares; i++){
            for(int y=0; y<numOfHorizontalSquares; y++){
                Lighting shader = new Lighting();
                allShaders.add(shader);
                addObject(shader, xPos, yPos);
                xPos += lightingSquare.getImage().getWidth();
            }
            yPos += lightingSquare.getImage().getHeight();
            xPos = 0 + lightingSquare.getImage().getWidth()/2; //reset xPos for next row
        }
    }

    /**
     * Change transparency of shaders that are near the player
     */
    public void adjustLighting(){
        //reset all shaders to full darkness
        for (Lighting s : allShaders) {
            s.getImage().setTransparency(255);
        }

        shaders1 = player.getEvenFurtherShaders();
        for(Lighting s:shaders1){
            s.getImage().setTransparency(175);
        }
        shaders1 = player.getFurtherShaders();
        for(Lighting s:shaders1){
            s.getImage().setTransparency(90);
        }
        shaders1 = player.getNearbyShaders();
        for(Lighting s:shaders1){
            s.getImage().setTransparency(50);
        }
        
    }

    public void spawnEnemyWave() {
        for (int i = 0; i < enemyWaveCount; i++) {
            int x, y;
            do {
                x = Greenfoot.getRandomNumber(BLOCKS_WIDE);
                y = Greenfoot.getRandomNumber(BLOCKS_HIGH);
            } while (!(theGrid[x][y] instanceof RoomBlock));
    
            Enemy enemy = new Enemy(player,enemyWaveCount);
            addObject(enemy, getXCoordinate(x), getYCoordinate(y));
            // Set enemy to be a Player event listener
            player.addEventListener(enemy);
        }
    }
    
    /**
     * Generate the Maze. This includes setting up the start point for the algorithm, running the algorithm,
     * and placing a start block.
     */
    private void init(){
        // Start approximately in the middle.
        // You can start on any RoomBlock 
        int startX = BLOCKS_WIDE / 2;
        int startY =  BLOCKS_HIGH / 2;
        // Both X and Y for generation must be odd as all rooms are (odd, odd)
        if (startX % 2 == 0){
            startX++;
        }
        if (startY % 2 == 0){
            startY++;
        }
        prims(startX, startY);
        // Time generation time
        long startTime = System.nanoTime();
        // Run the generation algorithm
        boolean success = prims(startX,startY);
        if (!success){
            return;
        }
        long duration = System.nanoTime() - startTime;

        // Report generation time if desired
        // System.out.println("Generated a Maze size " + BLOCKS_WIDE + " x " + BLOCKS_HIGH + " in " + (duration/1000000.0) + " ms.");
        // System.out.println("Generated a Maze size " + BLOCKS_WIDE + " x " + BLOCKS_HIGH + " in " + (duration/1000000.0) + " ms.")

        // Set start and end blocks
        ((RoomBlock)theGrid[1][1]).setStartBlock();
        // Set end block
        removeObject(theGrid[BLOCKS_WIDE-2][BLOCKS_HIGH-2]);
        EndBlock end = new EndBlock(BLOCKS_WIDE-2, BLOCKS_HIGH-2);
        theGrid[BLOCKS_WIDE-2][BLOCKS_HIGH-2] = end;
        addObject(end, getXCoordinate(BLOCKS_WIDE-2), getYCoordinate(BLOCKS_HIGH-2));

    }
    /*
     * Prepare a Grid for Prim algorithm.
     * 
     * - Blocks around the outside are Posts'
     * - Remaining Blocks where both x and y are even are Posts
     * - Remaining Blocks where both x and y are odd are Rooms
     * - All Remaining Blocks are Walls
     * - (Posts never move, Walls may be removed, Rooms are open spaces)
     */
    private void prepareGrid () {
        // flood blocks
        for (int y = 0; y < BLOCKS_HIGH; y++){
            for (int x = 0; x < BLOCKS_WIDE; x++){
                Block b;
                // Put a unmovable Post on every edge square as well as every every (even, even) square
                if (x == 0 || y == 0 || x == BLOCKS_WIDE - 1 || y == BLOCKS_HIGH - 1 || (y % 2 == 0 && x % 2 == 0)){
                    b = new PostBlock(x, y);

                } else if (y % 2 == 1 && x % 2 == 1){ // where y and x are both odd, make a room
                    b = new RoomBlock(x, y);
                }
                else { // All remaining Blocks will be (removable) Wall Blocks
                    b = new WallBlock(x, y);
                }
                theGrid[x][y] = b;
                addObject(theGrid[x][y], getXCoordinate(x), getYCoordinate(y));
            }
        }
    }
    

    /**
     * Mr. Cohen's Implementation of Prim's Algorithm for Maze Building on a 
     * Grid of Square blocks! 
     * 
     * Credit to:
     * 
     * The succinct explanation at: https://www.gamedeveloper.com/programming/algorithms-for-making-more-interesting-mazes
     * 
     * Check it out for a variety of ways to vary both the setup and execution of this algorithm (for example, with other starting
     * grids or other starting squares for the algorithm).
     */
    private boolean prims (int startX, int startY) {
        int x = startX;
        int y = startY;

        // Check for invalid states:
        if (theGrid.length % 2 == 0 || theGrid[0].length % 2 == 0 || startX > theGrid.length-1 || startY > theGrid[0].length || x < 1 || y < 1){
            System.out.println("Prim Algorithm: Invalid Parameters - Please read the notes!");
            //Greenfoot.stop();
            return false;
        }

        ArrayList<WallBlock> walls = new ArrayList<WallBlock>();
        //ArrayList<RoomBlock> path = new ArrayList<RoomBlock>();
        walls.addAll(getRoomWalls(x, y));
        RoomBlock firstRoom = (RoomBlock)theGrid[x][y];
        //path.add (firstRoom);
        firstRoom.visit();
        walls.addAll(getRoomWalls(x,y));
        while (walls.size() > 0){
            // Choose a random wall

            // Each run of the algorithm, a wall block is chosen from the list. 
            // This can be done randomly (as in MODE == 0) or in a biased way to 
            // alter generation (as in MODE == 1)
            WallBlock procBlock;

            if (MODE == 0){ // Standard mode => Random
                int rand = Greenfoot.getRandomNumber(walls.size());
                procBlock = walls.get(rand);
            } else if (MODE == 1){ // Experimenting - different ways to choose the next block
                int rand = Greenfoot.getRandomNumber(10);
                int max = walls.size() - 1;
                int target;
                if (rand == 0){
                    target = 0;
                } else if (rand <= 2) {
                    target = 1;
                } else if (rand <= 5) {
                    target = 0;
                } else {
                    target = max;
                }
                target = Math.min (target, max);
                procBlock = walls.get(target);
            }

            ArrayList<RoomBlock> adjacentRooms = getWallAdjacentRooms(procBlock.getMazeX(), procBlock.getMazeY());

            if (adjacentRooms.size() == 2){
                int unvisitedCount = 0;
                RoomBlock unvisitedRoom = null;
                for (RoomBlock r : adjacentRooms){
                    if (!r.visited()){
                        unvisitedCount++;
                        unvisitedRoom = r;
                        //r.visit();
                    }
                }
                if (unvisitedRoom != null && unvisitedCount == 1){
                    unvisitedRoom.visit();
                    //path.add(unvisitedRoom);
                    walls.addAll(getRoomWalls(unvisitedRoom.getMazeX(), unvisitedRoom.getMazeY()));
                    //turn grid into path
                    int pX = procBlock.getMazeX();
                    int pY = procBlock.getMazeY();
                    removeObject(procBlock);

                    RoomBlock pathBlock = new RoomBlock(pX, pY);
                    //Replace the WallBlock with the new RoomBlock.
                    theGrid[pX][pY] = pathBlock;
                    addObject(pathBlock, getXCoordinate(pX), getYCoordinate(pY));
                }
            }
            walls.remove(procBlock);
            if (DEMO_ALGORITHM){
                repaint(); // This method will redraw the screen even before the act ends, causing one act to go on a LONG time while this generates
            }
        }
        return true;
    }

    public boolean isPath(int gridX, int gridY) {
        //Check for out-of-bounds requests to prevent errors
        if (gridX < 0 || gridX >= BLOCKS_WIDE || gridY < 0 || gridY >= BLOCKS_HIGH) {
            return false;
        }
        return theGrid[gridX][gridY] instanceof RoomBlock;
    }

    private ArrayList<WallBlock> getRoomWalls (int x, int y){
        ArrayList<WallBlock> walls = new ArrayList<WallBlock>();
        if (theGrid[x-1][y] instanceof WallBlock){
            walls.add((WallBlock)theGrid[x - 1][y]);
        }
        if (theGrid[x+1][y] instanceof WallBlock){
            walls.add((WallBlock)theGrid[x + 1][y]);
        }
        if (theGrid[x][y-1] instanceof WallBlock){
            walls.add((WallBlock)theGrid[x][y - 1]);
        }
        if (theGrid[x][y+1] instanceof WallBlock){
            walls.add((WallBlock)theGrid[x][y + 1]);
        }
        return walls;
    }

    private ArrayList<RoomBlock> getWallAdjacentRooms (int x, int y){
        ArrayList<RoomBlock> rooms = new ArrayList<RoomBlock>();
        if (x > 1 && theGrid[x-1][y] instanceof RoomBlock){ // room to the left
            rooms.add ((RoomBlock)theGrid[x - 1][y]);
        }
        if (x <= BLOCKS_WIDE - 3 && theGrid[x+1][y] instanceof RoomBlock){ // room to the right
            rooms.add ((RoomBlock)theGrid[x + 1][y]);
        } 
        if (y > 1 && theGrid[x][y-1] instanceof RoomBlock){ // room above
            rooms.add ((RoomBlock)theGrid[x][y-1]);
        }
        if (y <= BLOCKS_HIGH - 3 && theGrid[x][y+1] instanceof RoomBlock){
            rooms.add ((RoomBlock)theGrid[x][y+1]);
        }

        return rooms;
    }

    private Block[] findPrimsNeightbors (int x, int y){
        return null;  
    }

    public static int getXCoordinate (int cellNumber){
        return (cellNumber * BLOCK_SIZE) + X_OFFSET;
    }

    public static int getXCell(int coordinate){
        return (coordinate - X_OFFSET) / BLOCK_SIZE;
    }

    public static int getYCoordinate (int cellNumber){
        return (cellNumber * BLOCK_SIZE) + Y_OFFSET;
    }

    public static int getYCell(int coordinate){
        return (coordinate - Y_OFFSET) / BLOCK_SIZE;
    }

    // Getter
    public Block[][] getGrid() {
        return theGrid;
    }
}