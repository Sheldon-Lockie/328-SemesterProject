import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Unit9 extends BaseActor
{
    public Unit9 (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/Unit9.png");
        centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
        setSize(80,80);
        setBoundaryPolygon(4);
        
    }
    
    
}
