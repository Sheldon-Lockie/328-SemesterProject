import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;




public class SettingsScreen extends BaseScreen
{
    public void initialize()
    {
        //Gdx.app.log("This is the Settings Screen",null);
    }
 
    public void update (float dt)
    {
        EscCheck();            
    }
    
     public void EscCheck()
    {
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
        {
            BaseGame.setActiveScreen( new MenuScreen());
        }
    }
}
