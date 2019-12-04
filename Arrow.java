import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.MathUtils;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.g2d.Animation;


public class Arrow extends BaseActor
{
    public float AngleToTarget;
    public float ArrowSizeX = 30;
    public float ArrowSizeY = 30;
    public float Timer;
    public float TimeToDelete = .35f;
    public double XTarget,YTarget;
    public double DistanceToTarget;
    public double XOrigin,YOrigin;
    //public Animation<TextureRegion> ArrowTexture;
    public Arrow (float OriginX, float OriginY, Stage s,float TargetX,float TargetY)
    {
        
        super(OriginX,OriginY,s);
        //Gdx.app.log("Placing Arrow at X",Float.toString(OriginX));
        //Gdx.app.log("Placing Arrow at Y",Float.toString(OriginY));
        //Gdx.app.log("The Enemy is at X",Float.toString(TargetX));
        //Gdx.app.log("The Enemy is at Y",Float.toString(TargetY));
        //this.ArrowTexture = loadTexture("Assets/Img/Towers/Arrow.png");
        loadTexture("Assets/Img/Towers/Arrow.png");
        setSize(ArrowSizeX,ArrowSizeY);
        setOrigin(ArrowSizeX/2,ArrowSizeY/2);
        this.XTarget = TargetX;
        this.YTarget = TargetY;
        this.YOrigin = OriginY;
        this.XOrigin = OriginX;
        this.AngleToTarget = getAngle(OriginX,OriginY,TargetX,TargetY);
        this.DistanceToTarget = computeRequiredDistance();
        //Gdx.app.log("The arrow must fly this far",Double.toString(this.DistanceToTarget));       //Gdx.app.log("The angle to the target is",Double.toString(this.AngleToTarget));
        setAcceleration(1000);
        setMaxSpeed(900);
        
      
    }
    
    public void act(float dt)
    {
        super.act(dt);
        this.setRotation(this.getMotionAngle());
        this.accelerateAtAngle(this.AngleToTarget);
        applyPhysics(dt);
        checkDistance();
        
    }
    public void checkDistance()
    {
        if (TraveledDistance() >= this.DistanceToTarget)
        {
            this.remove();
        }
        
    }
    public double TraveledDistance()
    {
        return Math.sqrt(Math.pow((this.getX() -XOrigin),2)+Math.pow((this.getY()-YOrigin),2));
        
    }
    public double computeRequiredDistance()
    {
      return Math.sqrt(Math.pow((XTarget -XOrigin),2)+Math.pow((YTarget-YOrigin),2));
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
