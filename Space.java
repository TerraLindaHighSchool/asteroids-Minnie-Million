import greenfoot.*;

/**
 * Space. Something for rockets to fly in.
 * 
 * @author Millie Harrison
 * @version 2/11/20
 */
public class Space extends World
{
    private Counter scoreCounter;
    private int startAsteroids = 3;
    private int starSize;
    private int r;
    private int a;

    /**
     * Create the space and all objects within it.
     */
    public Space() 
    {
        super(600, 500, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        
        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);
        
        addAsteroids(startAsteroids);
        paintStars(300);
        
        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 480);

        Explosion.initializeImages();
        ProtonWave.initializeImages();
    }
    
    public void updateScore(int addToScore)
    {
        scoreCounter.add(addToScore);
    }
    
    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
    }
    
    private void paintStars(int count)
    {
        for(int i = 0; i < count; i++)
        {
            colorSpectrum();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            GreenfootImage background = getBackground();
            background.setColor(new Color(r, r, r, a));
            starSize = Greenfoot.getRandomNumber(5);
            background.fillOval(x, y, starSize, starSize);
            
        }
    }
    
    public void colorSpectrum()
    {
        r = Greenfoot.getRandomNumber(255);
        a = Greenfoot.getRandomNumber(255);
    }
    
    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int currentScore = scoreCounter.getValue();
        addObject(new ScoreBoard(currentScore),x ,y);
        Greenfoot.stop();
    }
}