import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Unit2 extends BaseActor
{
    public Unit2 (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/MageTowerNew.png");
        //centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
        float sizeX = 70;
        float sizeY = 120;        
        setSize(sizeX, sizeY);
        centerAtPosition2(x, y, sizeX, sizeY);
        setBoundaryPolygon(4);
        
    }
     public void act(float dt)
    {
        super.act(dt);
        this.toFront();
    }
    
}
