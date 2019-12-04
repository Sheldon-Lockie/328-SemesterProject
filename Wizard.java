import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Wizard extends BaseActor
{   
    private int health;
    
    // pathing variables
    private boolean check1;
    private boolean check2;
    private boolean check3;
    private boolean check4;
    private boolean check5;
    
    private boolean path2_1;
    private boolean path2_2;
    private boolean path2_3;
    private boolean path2_4;
    
    public Wizard (float x, float y, Stage s, int path)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Enemies/WizardStatic.png");
        setSize(26,42);
        setBoundaryPolygon(8);

        health = 100;
        
        // pathing initialization
        check1 = false;
        check2 = false;
        check3 = false;
        check4 = false;
        check5= false;
        
        path2_1 = false;
        path2_2 = false;
        path2_3 = false;
        path2_4 = false;
        determinePath(path);
        
        // spped tuning
        setSpeed(100);
        setMaxSpeed(100);
        setDeceleration(0);
        setMotionAngle(0); // move towards player initially
       
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        applyPhysics(dt);
        
        followPath();
    }
    
    // will determine which path to take
    public void determinePath(int pathChoice)
    {
        switch(pathChoice)
        {
            case 1:
                path2_1 = true;
                break;
            case 2:
                path2_2 = true;
                break;
            case 3:
                path2_3 = true;
                break;
            case 4:
                path2_4 = true;
                break;
            default:
                path2_1 = true;
        }
        
        /*System.out.print("path2_1 = " + path2_1 + "\n");
        System.out.print("path2_2 = " + path2_2 + "\n");
        System.out.print("path2_3 = " + path2_3 + "\n");
        System.out.print("path2_4 = " + path2_4 + "\n");*/
    }
    
    // will send character along a set path
    public void followPath()
    {
        // Just in case check - boundary check
        if(getX() > 1350)
        {
            this.remove();
        }
        
        /*
         * The following are paths from the top spawn
         */
        
        /*
         * The following are paths from the middle spawn
         */
        if(path2_1 == true)
        {
            if(getX() > 370)
            {
                if(check1 == false)
                {
                    setMotionAngle(90);
                    check1 = true;
                }
                
                else if(check2 == false)
                {
                    if(getY() > 675)
                    {
                        setMotionAngle(15);
                        check2 = true;
                    }
                }
                
                else if(check3 == false)
                {
                    if(getX() > 800)
                    {
                        setMotionAngle(330);
                        check3 = true;
                    }
                }
                
                else if(check4 == false)
                {
                    if(getX() > 950)
                    {
                        setMotionAngle(270);
                        check4 = true;
                    }
                }
                
                else if(check5 == false)
                {
                    if(getY() < 530)
                    {
                        setMotionAngle(0);
                        check4 = true;
                    }
                }
            }
        }
        
        if(path2_2 == true)
        {
            if(getX() > 370)
            {
                if(check1 == false)
                {
                    setMotionAngle(90);
                    check1 = true;
                }
                
                else if(check2 == false)
                {
                    if(getY() > 675)
                    {
                        setMotionAngle(15);
                        check2 = true;
                    }
                }
                
                else if(check3 == false)
                {
                    if(getX() > 720)
                    {
                        setMotionAngle(270);
                        check3 = true;
                    }
                }
                
                else if(check4 == false)
                {
                    if(getY() < 550)
                    {
                        setMotionAngle(358);
                        check4 = true;
                    }
                }
            }
        }
        
        else if(path2_3 == true)
        {
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
                    if(getX() > 950)
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
        
        else if(path2_4 == true)
        {
            if(getX() > 370)
            {
                if(check1 == false)
                {
                    setMotionAngle(270);
                    check1 = true;
                }
                
                else if(check2 == false)
                {
                    if(getY() < 380)
                    {
                        setMotionAngle(355);
                        check2 = true;
                    }
                }
                
                else if(check3 == false)
                {
                    if(getX() > 860)
                    {
                        setMotionAngle(45);
                        check3 = true;
                    }
                }
                
                else if(check4 == false)
                {
                    if(getY() > 530)
                    {
                        setMotionAngle(0);
                        check4 = true;
                    }
                }
            }
        }
        
        /* 
         * The following are paths from the bottom spawn
         */
    }
                
    // will decrease the wizard health by the damage parameter
    public void decreaseHealth(int damage)
    {
        this.health -= damage;
        
        // indicates death
        if(this.health <= 0)
        {
            this.remove();
        }
    }
}
