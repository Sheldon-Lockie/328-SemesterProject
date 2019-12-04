import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class ArcherTower extends BaseActor
{
    public ArcherTower (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/ArcherTower.png");
        centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
        setSize(80,80);
        setBoundaryPolygon(4);
        CreateRangeRectangle(x,y,s);
        
        
        
    }
    
    public void act(float dt)
    {
    super.act(dt);
    }
    void CreateRangeRectangle(float CenterX ,float CenterY,Stage s)
    {
        new RangeRectangle(CenterX,CenterY,s,300,300);
    }
    
}
    