import com.badlogic.gdx.Gdx;

public class Currency
{
    private float Coins;
    public Currency()
    {
        Coins = 0;
        
        
    }
       
    /** Removes Currency To Player's Balance **/
    public float removeBalance(float amount)
    {
       if (Coins - amount >= 0)
       {
           Coins -= amount;
           return Coins;
       }
       else
       {
         Gdx.app.log("The Player doesn't have enough, they have this many coins",Float.toString(Coins)); 
         //Trigger invalid popup...etc
         return Coins;
       }
       
        
    }
    /** Adds Currency To Player's Balance **/
    public float addBalance(float amount)
    {
        Coins += amount;
        return Coins;
        
    }
    /** Returns Player's current balance **/
    public float getBalance()
    {
        return Coins;
    }
}
