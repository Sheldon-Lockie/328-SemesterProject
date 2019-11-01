import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
public class Button extends BaseActor
{
    public static float MinX;
    public static float MaxX;
    public static float MinY;
    public static float MaxY;
    public static float LengthX = 200;
    public static float LengthY = 100;
    
    public Button(float x, float y, Stage s)
    {
        super(x,y,s);
        MinX = x;
        MaxX = x+LengthX;
        MinY = y;
        MaxY = y+LengthY;
        
    }
   
}