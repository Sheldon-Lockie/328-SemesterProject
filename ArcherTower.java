import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class ArcherTower extends BaseActor
{
    public ArcherTower (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/ArcherTower.png");
        centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
    }
    
    
}