import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Unit10 extends BaseActor
{
    public Unit10 (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/Unit10.png");
        centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
        setSize(80,80);
        setBoundaryPolygon(4);
        
    }
    
    
}
