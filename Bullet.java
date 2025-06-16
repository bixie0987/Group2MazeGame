import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor {
    private double dx, dy;
    private double speed = 5;

    public Bullet(int startX, int startY, int targetX, int targetY) {
        GreenfootImage img = new GreenfootImage(5, 5);
        img.setColor(Color.YELLOW);
        img.fill();
        setImage(img);

        double angle = Math.atan2(targetY - startY, targetX - startX);
        dx = Math.cos(angle) * speed;
        dy = Math.sin(angle) * speed;
    }

    public void act() {        
        //remove bullet if off screen or hits something
        if (getX() < 0 || getX() >= getWorld().getWidth() || getY() < 0 || getY() >= getWorld().getHeight()) {
            getWorld().removeObject(this);
            return;
        }
        
        if (isTouching(Player.class)) {
            Player p = (Player) getOneIntersectingObject(Player.class);
            p.takeDamage(1);  // Damage per bullet
        }

        setLocation((int)(getX() + dx), (int)(getY() + dy));
    }
}
