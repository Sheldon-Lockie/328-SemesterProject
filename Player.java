import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Player extends BaseActor
{
    public Player (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Player/PlayerStatic.png");
        setSize(86,65);
        setBoundaryPolygon(12);
        
    }
    
    
}
