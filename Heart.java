import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Heart here.
 * 
 * @author Yuvia 
 * @version June 2025
 */

public class Heart extends Upgrades {
    private GreenfootImage heart;

    public Heart() {
        heart = new GreenfootImage("heart.png"); 
        setImage(heart);

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