import greenfoot.*;

/**
 * A bullet that can hit asteroids.
 * 
 * @author Millie Harrison
 * @version 2/25/20
 */
public class Laser extends SmoothMover
{
    /** The damage this bullet will deal */
    private static final int damage = 16;
    private static final int pointsToAdd = 3;
    /** A bullet looses one life each act, and will disappear when life = 0 */
    private int life = 30;
    
    /**
     * Default constructor for testing.
     */
    public Laser()
    {
    }
    
    /**
     * Create a bullet with given speed and direction of movement.
     */
    public Laser(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        addToVelocity(new Vector(rotation, 15));
        Greenfoot.playSound("EnergyGun.wav");
    }
    
    /**
     * The bullet will damage asteroids if it hits them.
     */
    public void act()
    {
        if(life <= 0) {
            getWorld().removeObject(this);
        } 
        else {
            life--;
            move();
            checkRocketHit();
        }
    }
    
    /**
     * Check whether we have hit a rocket.
     */
    private void checkRocketHit()
    {
        Rocket rocket = (Rocket) getOneIntersectingObject(Rocket.class);
        if (rocket != null)
        {
            getWorld().removeObject(this);
            rocket.life();
        }
    }
}
