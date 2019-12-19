import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class NoPlacementArea extends BaseActor
{
    public NoPlacementArea (float x, float y, Stage s,float SizeX, float SizeY)
    {
        super(x,y,s);
        loadTexture("Assets/Img/PlaceHolders/DebugPlacementArea.png");
        setSize(SizeX,SizeY);
        setBoundaryPolygon(4);
        setOpacity(0f);
        
    }
    
    
}
