import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Unit11 extends BaseActor
{
    public Unit11 (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/Unit11.png");
        centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
        setSize(80,80);
        setBoundaryPolygon(4);
        
    }
    
    
}
