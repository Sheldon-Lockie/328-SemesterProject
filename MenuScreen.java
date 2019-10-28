import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class MenuScreen extends BaseScreen
{
    TextButton ExitButton;
    TextButton SettingsButton;
    TextButton PlayButton;
    private BaseActor Background;
    private int ViewportHeight = 600;
    private int ViewportWidth = 800;
    public void initialize()
    {
       
        
        Background = new BaseActor (0,0,mainStage);
        Background.setWorldBounds(ViewportWidth,ViewportHeight);
        Background.loadTexture("Assets/test.png");
        
    }
 
    public void update (float dt)
    {
        
    }
    
    
}