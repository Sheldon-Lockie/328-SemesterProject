import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class RangeRectangle extends BaseActor
{
    BaseActor RangeRectangle = this;
    public RangeRectangle (float x, float y, Stage s,float SizeX,float SizeY)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/Unit14.png");
        setOpacity(0);
        setSize(SizeX,SizeY);
        centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
        setBoundaryPolygon(4);
        CheckForRange(s);
        
        
    }
    public void CheckForRange(Stage s)
    {
       for (BaseActor WizardHandler : BaseActor.getList(s,"Wizard"))
       {
           if (WizardHandler.overlaps(RangeRectangle))
           {
               Gdx.app.log("There was an overlap",null);
           }
           
       }
        
    }
}
    
