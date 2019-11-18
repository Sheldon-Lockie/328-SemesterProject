import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Wizard extends BaseActor
{
    public Wizard (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Enemies/WizardStatic.png");
        setSize(58,87);
        setBoundaryPolygon(12);
        
    }
    
    
}
