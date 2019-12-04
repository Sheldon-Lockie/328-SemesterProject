import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.MathUtils;


public class Arrow extends BaseActor
{
    public float AngleToTarget;
    public float ArrowSizeX = 30;
    public float ArrowSizeY = 30;
    public float Timer;
    public float TimeToDelete = .25f;
    public Arrow (float OriginX, float OriginY, Stage s,float TargetX,float TargetY)
    {
        
        super(OriginX,OriginY,s);
        //Gdx.app.log("Placing Arrow at X",Float.toString(OriginX));
        //Gdx.app.log("Placing Arrow at Y",Float.toString(OriginY));
        //Gdx.app.log("The Enemy is at X",Float.toString(TargetX));
        //Gdx.app.log("The Enemy is at Y",Float.toString(TargetY));
        loadTexture("Assets/Img/Towers/Arrow.png");
        setSize(ArrowSizeX,ArrowSizeY);
        setOrigin(ArrowSizeX/2,ArrowSizeY/2);
        this.Timer = 0;
        this.AngleToTarget = getAngle(OriginX,OriginY,TargetX,TargetY);
        //Gdx.app.log("The angle to the target is",Double.toString(this.AngleToTarget));
        setAcceleration(500);
        setMaxSpeed(500);
        boundToWorld();
      
    }
    
    public void act(float dt)
    {
        super.act(dt);
        this.setRotation(this.getMotionAngle());
        this.accelerateAtAngle(this.AngleToTarget);
        applyPhysics(dt);
        this.Timer+= dt;
        if (this.Timer >= 0)
        {
            this.remove();
        }
        
    }
    
    public float getAngle(float originX,float originY, float TargetX,float TargetY)
    
    {
        float angle = (float) Math.toDegrees(Math.atan2(TargetY-originY,TargetX-originX));
        if (angle < 0)
            {
                angle+= 360;
            }  
            return angle;
    }
    
   
}
