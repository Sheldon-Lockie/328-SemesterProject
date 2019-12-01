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
     
        setSpeed(0);
        setMaxSpeed(200);
        setDeceleration(0);
        setMotionAngle(180); // move towards player initially
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
        setSpeed(200);
        setMotionAngle(0);
    }
}
