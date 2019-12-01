import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Input.Keys;

// the player controlled hero
public class Player extends BaseActor
{
    Animation north;
    Animation south;
    Animation east;
    Animation west;
    
    float facingAngle;
    
    private float frameDuration;
    private boolean noMovementCheck;
    
    
    public Player (float x, float y, Stage s)
    {
        super(x,y,s);
        
        setName("hero");
        
        setSize(20, 68);
        
        frameDuration = 0.2f;
        
        String[] northFiles = {"Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeftBase.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft1.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft2.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft3.png"};
        north = loadAnimationFromFiles(northFiles, frameDuration, true);
        String[] southFiles = {"Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeftBase.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft1.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft2.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft3.png"};
        south = loadAnimationFromFiles(southFiles, frameDuration, true);
        String[] eastFiles = {"Assets/Img/Player/RightAnimations/Walking/PlayerWalkRightBase.png",
                "Assets/Img/Player/RightAnimations/Walking/PlayerWalkRight1.png",
                "Assets/Img/Player/RightAnimations/Walking/PlayerWalkRight2.png",
                "Assets/Img/Player/RightAnimations/Walking/PlayerWalkRight3.png"};
        east = loadAnimationFromFiles(eastFiles, frameDuration, true);
        String[] westFiles = {"Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeftBase.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft1.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft2.png",
                "Assets/Img/Player/LeftAnimations/Walking/PlayerWalkLeft3.png"};
        west = loadAnimationFromFiles(westFiles, frameDuration, true);
               
        // default to facing south
        setAnimation(south);
        facingAngle = 270;
        
        // initializing rest of variables
        noMovementCheck = false;
        
        // set after animation established
        setBoundaryPolygon(8);

        setAcceleration(200);
        setMaxSpeed(200);
        setDeceleration(700);
        
        setSize(80, 91);
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        // pause animation when character not moving
        if(getSpeed() == 0 )
        {
            setAnimationPaused(true);
        }
        
        else
        {
            setAnimationPaused(false);

            // set direction animation
            float angle = getMotionAngle();
            
            // set north east (right)
            if (angle >= 45 && angle < 90)
            {
                facingAngle = 90;
                setAnimation(east);
                setSize(80, 91);
            }
            
            // set north west (left)
            if (angle >= 90 && angle <= 135)
            {
                facingAngle = 90;
                setAnimation(north);
                setSize(80, 91);
            }
            
            // set west movement
            else if (angle > 135 && angle < 225)
            {
                facingAngle = 180;
                setAnimation(west);
                setSize(80, 91);
            }
            
            // set south west movement
            else if (angle >= 225 && angle <= 270)
            {
                facingAngle = 270;
                setAnimation(south);
                setSize(80, 91);
            }
            
            
            // set south east movement (right)
            else if (angle > 270 && angle <= 315)
            {
                facingAngle = 270;
                setAnimation(east);
                setSize(80, 91);
            }
            
            // set east movement
            else
            {
                facingAngle = 0;
                setAnimation(east);
                setSize(80, 91);
            }
        }
        
        // moves hero if button is pressed and it's allowed
        if(!noMovementCheck)
        {
            // hero movement controls
            if (Gdx.input.isKeyPressed(Keys.LEFT)) 
                accelerateAtAngle(180);
            if (Gdx.input.isKeyPressed(Keys.RIGHT)) 
                accelerateAtAngle(0);
            if (Gdx.input.isKeyPressed(Keys.UP)) 
                accelerateAtAngle(90);
            if (Gdx.input.isKeyPressed(Keys.DOWN)) 
                accelerateAtAngle(270);
        }

        // alignCamera();
        boundToWorld();
        applyPhysics(dt);
    }

    public float getFacingAngle()
    {
        return facingAngle;
    }   
}
