/**
 * FireBolt attack
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.audio.Sound;

public class FireBolt extends BaseActor
{
    // firebolt variables
    private float AngleToTarget;
    private float fireSizeX;
    private float fireSizeY;
    private float Timer;
    private float TimeToDelete;
    private double XTarget,YTarget;
    private double DistanceToTarget;
    private float xOrigin,yOrigin;
    
    // shooting helper variables
    private boolean inMotion;
    
    private Sound fireSound;
    
    /**
     * Constructor for objects of class FireBolt
     */
    public FireBolt(float OriginX, float OriginY, Stage s)
    {
        super(OriginX,OriginY,s);

        loadTexture("Assets/Img/Towers/FireBolt.png"); // change to firebolt
        setPosition(OriginX, OriginY);
        makeInvisible(true); // initialize to invisible
        
        // arrow variable initialization
        AngleToTarget = 0.0f;
        fireSizeX = 60.0f;
        fireSizeY = 40.0f;
        
        setSize(fireSizeX, fireSizeY);
        setOrigin(fireSizeX/2, fireSizeY/2);
        
        Timer = 0.0f;
        TimeToDelete = 0.50f;
        XTarget = 0;
        YTarget = 0;
        DistanceToTarget = 0;
        xOrigin = OriginX;
        yOrigin = OriginY;
        
        fireSound = Gdx.audio.newSound(Gdx.files.internal("Assets/Sounds/FireBolt.mp3"));
        
        // shooting helper variable initialization
        inMotion = false;
        
        setSpeed(0); // don't move initially
        setMaxSpeed(1000);  
        this.toFront();
    }
    
    // sets the visibility of the arrow
    public void makeInvisible(boolean state)
    {
        this.setVisible(!state); // will make the arrow visible or invisible as necessary
    }
    
    // will perform steps to shoot arrow from origin to target
    public void shootFireBolt(float OriginX, float OriginY, float TargetX,float TargetY)
    {
        fireSound.play(0.1f);
        setOrigin(fireSizeX/2,fireSizeY/2);
        setPosition(xOrigin, yOrigin); // reset position at tower
        makeInvisible(false); // make arrow visible
        inMotion = true; // set motion to occur
        
        // set origin and target variables
        this.XTarget = TargetX;
        this.YTarget = TargetY;
        this.yOrigin = OriginY;
        this.xOrigin = OriginX;
        
        // set angle and distance
        this.AngleToTarget = getAngle(OriginX,OriginY,TargetX,TargetY);
        this.DistanceToTarget = computeRequiredDistance();
                        
        setMotionAngle(AngleToTarget); // sets angle of object
        setRotation(getMotionAngle()); // sets rotation of image
        setSpeed(700);
        setMaxSpeed(1000);
    }
    
    // performs shooting if enabled
    public void act(float dt)
    {
        super.act(dt);
        
        if(inMotion == true)
        {
            this.toFront(); // moves arrow over other objects
            setMotionAngle(AngleToTarget); // sets angle of object
            setRotation(getMotionAngle()); // sets rotation of image
            setSpeed(700); // moves object at set speed
            applyPhysics(dt);
            checkDistance(); // check if object has reached target
        }
        
    }
    
    // check distance of arrow traveled
    public void checkDistance()
    {
        // indicates arrow has reached target
        if (TraveledDistance() >= this.DistanceToTarget)
        {           
            makeInvisible(true); // make arrow invisible
            inMotion = false; // stop moving it
            setPosition(xOrigin, yOrigin); // reset position at tower
        }       
    }
    
    // calculates traveled distance
    public double TraveledDistance()
    {
        return Math.sqrt(Math.pow((this.getX() -xOrigin),2)+Math.pow((this.getY()-yOrigin),2));       
    }
    
    // calculates distance necessary for travel
    public double computeRequiredDistance()
    {
        return Math.sqrt(Math.pow((XTarget -xOrigin),2)+Math.pow((YTarget-yOrigin),2));
    }
    
    // retrieves angle arrow needs to fly at
    public float getAngle(float originX,float originY, float TargetX,float TargetY)    
    {
        float angle = (float) Math.toDegrees(Math.atan2(TargetY-originY,TargetX-originX));
        
        // ensures angle is a positive number
        while (angle < 0)
        {
            angle+= 360;
        }  
            
        return angle;
    }
}
