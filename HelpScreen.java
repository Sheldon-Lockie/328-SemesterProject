import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

/**
 *  This will create the help screen to display controls and game objective
 */
public class HelpScreen extends BaseScreen
{
    public void initialize()
    {
        //Gdx.app.log("This is the Settings Screen",null);
    }
 
    public void update (float dt)
    {
        EscCheck();            
    }
    
    // Escapes to main menu if esc is pressed
     public void EscCheck()
    {
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
        {
            BaseGame.setActiveScreen( new MenuScreen());
        }
    }
}
