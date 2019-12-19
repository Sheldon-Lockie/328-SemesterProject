import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

/**
 *  This will create the help screen to display controls and game objective
 */
public class HelpScreen extends BaseScreen
{
    BaseActor Background;
    public void initialize()
    {
        //Gdx.app.log("This is the Settings Screen",null);
        Background =  new BaseActor(0,0,uiStage);
        Background.loadTexture("Assets/Img/PlaceHolders/HelpMenu.png");
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
