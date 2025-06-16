import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Animation Class is controlled by AnimationController.
 * 
 * In some of my examples, you may find both baked together into a single class.
 * 
 * The Animation Class is mostly a container for images, can be based on 1D or 2D
 * array of images (for one-direction or multi-direction Animations) and also includes
 * an enumerator for Direction to keep direction variables clean and manageable.
 * 
 * @author Jordan Cohen
 * @since 2017
 * @version (unsure)
 */
public class Animation {
    private GreenfootImage[][] directionalImages;
    private GreenfootImage[] nonDirectionalImages;

    private boolean directional;

    private int directions;
    /**
     * Constructor for directional animations - should be a 2d array of GreenfootImage with
     * 4 directions (dimension 1) and at least one image per direction (dimension 2)
     *
     * @param images    2d array of images as described above
     * @param terminal  true if this animation is not intended to repeat
     */

    public Animation (GreenfootImage[][] images){
        this.directional = true;
        directionalImages = images;

        directions = images.length;
    }

    public Animation (GreenfootImage[] images){
        this.directional = false;
        nonDirectionalImages = images;

        directions = 1;
    }

    public void setImages (GreenfootImage[][] images){
        directionalImages = images;
    }

    public void setImages (GreenfootImage[] images){
        nonDirectionalImages = images;
    }

    public boolean isDirectional (){
        return this.directional;
    }

    public GreenfootImage getOneImage (Direction d, int frame){
        return directionalImages[d.getDirection()][frame];
    }

    public GreenfootImage[][] getDirectionalImages (){
        return directionalImages;
    }

    public GreenfootImage[] getNonDirectionalImages (){
        return nonDirectionalImages;
    }

}

    // ENUM to keep direction related code clean.
    enum Direction {
        RIGHT(0),

        LEFT(1),

        UP(2),

        DOWN(3);

        private final int dirCode;
        private Direction (int dirCode){
            this.dirCode = dirCode;
        }

        public int getDirection (){
            return this.dirCode;
        }

        public static Direction fromInteger(int x) {
            switch(x) {
                case 0:
                return RIGHT;
                case 1:
                return LEFT;
                case 2:
                return UP;
                case 3:
                return DOWN;
            }
            return null;
        }

        public static Direction randomDirection (){
            return fromInteger (Greenfoot.getRandomNumber(4));

        }

        public final static int size = Direction.values().length;
    }




