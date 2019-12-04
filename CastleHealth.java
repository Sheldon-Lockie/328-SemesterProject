import com.badlogic.gdx.Gdx;

public class CastleHealth
{
    private float health;
    
    public CastleHealth()
    {
        System.out.print("Starting Health: 100\n");
        health = 100;        
    }
    
    
    
    /** Removes CastleHealth , reutrns true if dead, false if alive??**/
    public boolean DecreaseCastleHealth(float amount)
    {
       if (health - amount <= 0)
       {
           return true;
       }
       
       else
       {
         health -= amount;
         return false;
       }        
    }
    
    // reduce health
    public void reduceHealth(float damage)
    {
        System.out.print("Castle hit for " + damage + " damage. Remaining health: " + (float)(health-damage) + "\n");
        health -= damage;
    }
    
    /** Adds CastleHealth **/
    public float addCastleHealth(float amount)
    {
        health += amount;
        return health;
        
    }
    /** Returns Castle Health **/
    public float getHealth()
    {
        return health;
    }
}
