import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Heart class represents a collectible item that heals the player
 * when picked up. It inherits from the Upgrades class.
 * 
 * When the player touches the heart, they gain health, and the heart is removed from the world.
 * 
 * @author Yuvia 
 * @version June 2025
 */

public class Heart extends Upgrades {
    private GreenfootImage heart;

    public Heart() {
        heart = new GreenfootImage("heart.png"); 
        setImage(heart);
        heart.scale(30,30);

    }

    public void act() {
        if (isTouching(Player.class)) {
            Player p = (Player) getOneIntersectingObject(Player.class);
            if (p != null) {
                p.heal(20); // heal 20 points, or any value you want
            }
            getWorld().removeObject(this);
        }
    }
}