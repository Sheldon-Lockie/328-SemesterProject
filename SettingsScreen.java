import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;




public class SettingsScreen extends BaseScreen
{
    private BaseActor background;
    
    public void initialize()
    {
        background =  new BaseActor(0,0,uiStage);
        background.loadTexture("Assets/Img/PlaceHolders/SettingsMenu.png");
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
