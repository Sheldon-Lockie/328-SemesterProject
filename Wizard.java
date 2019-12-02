import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Wizard extends BaseActor
{
    private boolean check1;
    private boolean check2;
    private boolean check3;
    private boolean check4;
    
    private int health;
    
    public Wizard (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Enemies/WizardStatic.png");
        setSize(30,50);
        setBoundaryPolygon(8);
     
        setSpeed(130);
        setMaxSpeed(130);
        setDeceleration(0);
        setMotionAngle(0); // move towards player initially
        
        health = 100;
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        applyPhysics(dt);
        setSpeed(130);
        
        // HARD CODED PATHING ---- FIIIXXX
        if(getX() > 370)
        {
            if(check1 == false)
            {
                setMotionAngle(270);
                check1 = true;
            }
            
            else if(check2 == false)
            {
                if(getY() < 370)
                {
                    setMotionAngle(0);
                    check2 = true;
                }
            }
            
            else if(check3 == false)
            {
                if(getX() > 680)
                {
                    setMotionAngle(90);
                    check3 = true;
                }
            }
            
            else if(check4 == false)
            {
                if(getY() > 550)
                {
                    setMotionAngle(0);
                    check4 = true;
                }
            }
        }
    }
    
    public void decreaseHealth(int damage)
    {
        health -= damage;
        
        if(health <= 0)
        {
            this.remove();
        }
    }
}
