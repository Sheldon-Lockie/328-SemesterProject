import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Goblin extends BaseActor
{
    public Goblin (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Enemies/GoblinStatic.png");
        setSize(53,87);
        setBoundaryPolygon(12);
        
    }
    
    
}
