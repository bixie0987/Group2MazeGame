import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *  Bullet class represents projectiles fired by either the player or enemies.
 * 
 * - Player bullets are yellow and move in one of the four cardinal directions.
 * - Enemy bullets are red and automatically target the player's location.
 * - Bullets deal damage on impact and are removed upon hitting their target or going off screen.
 * 
 * @author Yuvia, Chin-En
 * @version June 2025
 */
public class Bullet extends Actor {
    private double dx, dy;
    private double speed = 5;
    private boolean isRed;

    
    /**
     * Constructor for enemy bullet.
     * Fires from start position toward target (usually the player).
     */
    public Bullet(int startX, int startY, int targetX, int targetY) {
        GreenfootImage img = new GreenfootImage(5, 5);
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
        isRed = true;

        double angle = Math.atan2(targetY - startY, targetX - startX);
        dx = Math.cos(angle) * speed;
        dy = Math.sin(angle) * speed;
    }
    
     /**
     * Constructor for player bullet.
     * Fires in the direction the player is facing.
     */
    public Bullet(int startX, int startY, String direction) {
        GreenfootImage img = new GreenfootImage(5, 5);
        img.setColor(Color.YELLOW);
        img.fill();
        setImage(img);
        isRed = false;
        
        if (direction.equals("up")) {
            dx = 0;
            dy = -speed;
        } else if (direction.equals("down")) {
            dx = 0;
            dy = speed;
        } else if (direction.equals("left")) {
            dx = -speed;
            dy = 0;
        } else if (direction.equals("right")) {
            dx = speed;
            dy = 0;
        }
    }

    public void act() {        
        //remove bullet if off screen or hits something
        if (getX() < 0 || getX() >= getWorld().getWidth() || getY() < 0 || getY() >= getWorld().getHeight()) {
            getWorld().removeObject(this);
            return;
        }
        
        if (isRed && isTouching(Player.class)) {
            Player p = (Player) getOneIntersectingObject(Player.class);
            p.takeDamage(GameManager.getInstance().enemyDamage);  // Damage per bullet
            getWorld().removeObject(this);
            return;
        }
        if (!isRed && isTouching(Enemy.class)) {
            Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
            e.takeDamage(15);
            getWorld().removeObject(this);
            return;
        }

        // Move bullet forward
        setLocation((int)(getX() + dx), (int)(getY() + dy));
    }
}
