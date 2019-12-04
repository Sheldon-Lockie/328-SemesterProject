import com.badlogic.gdx.Gdx;

public class CastleHealth
{
    private float Health;
    public CastleHealth()
    {
        Health = 100;
        
        
    }
    
    
    
    /** Removes CastleHealth , reutrns true if dead, false if alive??**/
    public boolean DecreaseCastleHealth(float amount)
    {
       if (Health - amount <= 0)
       {
           return true;
       }
       else
       {
         Health -= amount;
         return false;
       }
       
        
    }
    /** Adds CastleHealth **/
    public float addCastleHealth(float amount)
    {
        Health += amount;
        return Health;
        
    }
    /** Returns Castle Health **/
    public float getHealth()
    {
        return Health;
    }
}
