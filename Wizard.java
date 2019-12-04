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
    private boolean check6;
    
    private boolean path1_1;
    private boolean path1_2;

    private boolean path2_1;
    private boolean path2_2;
    private boolean path2_3;
    private boolean path2_4;
    private boolean path3_1;
    private boolean path3_2;

    
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
        check5 = false;
        check6 = false;
        
        path1_1 = false;
        path1_2 = false;

        path2_1 = false;
        path2_2 = false;
        path2_3 = false;
        path2_4 = false;
        path3_1 = false;
        path3_2 = false;

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
            case 5:
                path1_1 = true;
                break;
            case 6:
                path1_2 = true;
                break;
            case 7:
                path3_1 = true;
                break;
            case 8:
                path3_2 = true;
                break;
            default:
                System.out.print("Spawn Error!\n");
                path2_1 = true;
        }
        
        /*System.out.print("path1_1 = " + path1_1 + "\n");
        System.out.print("path1_2 = " + path1_2 + "\n");

    
        System.out.print("path2_1 = " + path2_1 + "\n");
        System.out.print("path2_2 = " + path2_2 + "\n");
        System.out.print("path2_3 = " + path2_3 + "\n");
        System.out.print("path2_4 = " + path2_4 + "\n");
        
        System.out.print("path3_1 = " + path3_1 + "\n");
        System.out.print("path3_2 = " + path3_2 + "\n");

        System.out.print("\n\n");*/
    
    }
    
    // will send character along a set path
    public void followPath()
    {
        // Just in case check - boundary check
        if(getX() > 1205)
        {
            this.remove();
        }
        
        /*
         * The following are paths from the top spawn
         */
        if(this.path1_1 == true)
        {
            if(this.check1 == false)
            {
                setMotionAngle(270);
                this.check1 = true;
            }
             
            else if(this.check2 == false)
            {
               if(this.getY() < 660)
               {   
                    setMotionAngle(15);
                    this.check2 = true;
               }
            }
                
            else if(this.check3 == false)
            {
                if(this.getX() > 850)
                {
                    setMotionAngle(330);
                    this.check3 = true;
                }
            }
                
            else if(this.check4 == false)
            {
                if(this.getX() > 950)
                {
                    setMotionAngle(270);
                    this.check4 = true;
                }
            }
                
            else if(this.check5 == false)
            {
                if(this.getY() < 530)
                {
                    setMotionAngle(0);
                    this.check5 = true;
                }
            }
        }
        
        else if(this.path1_2 == true)
        {
             if(this.check1 == false)
             {
                 setMotionAngle(270);
                 this.check1 = true;
             }
                
             else if(this.check2 == false)
             {
                 if(this.getY() < 660)
                 {
                     setMotionAngle(15);
                     this.check2 = true;
                 }
             }
               
             else if(this.check3 == false)
             {
                 if(this.getX() > 720)
                 {
                     setMotionAngle(270);
                     this.check3 = true;
                 }
             }
             
             else if(this.check4 == false)
             {
                 if(this.getY() < 565)
                 {
                     setMotionAngle(358);
                     this.check4 = true;
                 }
             }      
                
             else if(this.check5 == false)
             {
                 if(this.getX() > 950)
                 {
                     setMotionAngle(350);
                     this.check5 = true;
                 }
             }
             
             else if(this.check6 == false)
            {
                if(this.getY() < 530)
                {
                    setMotionAngle(0);
                    this.check6 = true;
                }
            }
        }
        
        
        /*
         * The following are paths from the middle spawn
         */
        else if(this.path2_1 == true)
        {
            if(this.getX() > 350)
            {
                if(this.check1 == false)
                {
                    setMotionAngle(90);
                    this.check1 = true;
                }
                
                else if(this.check2 == false)
                {
                    if(this.getY() > 660)
                    {
                        setMotionAngle(15);
                        this.check2 = true;
                    }
                }
                
                else if(this.check3 == false)
                {
                    if(this.getX() > 850)
                    {
                        setMotionAngle(330);
                        this.check3 = true;
                    }
                }
                
                else if(this.check4 == false)
                {
                    if(this.getX() > 950)
                    {
                        setMotionAngle(270);
                        this.check4 = true;
                    }
                }
                
                else if(this.check5 == false)
                {
                    if(this.getY() < 530)
                    {
                        setMotionAngle(0);
                        this.check4 = true;
                    }
                }
            }
            
            else
            {
                setMotionAngle(12);
            }
        }
        
        else if(this.path2_2 == true)
        {
            if(this.getX() > 350)
            {
                if(this.check1 == false)
                {
                    setMotionAngle(90);
                    this.check1 = true;
                }
                
                else if(this.check2 == false)
                {
                    if(this.getY() > 660)
                    {
                        setMotionAngle(15);
                        this.check2 = true;
                    }
                }
                
                else if(this.check3 == false)
                {
                    if(this.getX() > 720)
                    {
                        setMotionAngle(270);
                        this.check3 = true;
                    }
                }
                
                else if(this.check4 == false)
                {
                    if(this.getY() < 565)
                    {
                        setMotionAngle(358);
                        this.check4 = true;
                    }
                }
                
                else if(this.check5 == false)
                {
                    if(this.getX() > 950)
                    {
                        setMotionAngle(350);
                        this.check5 = true;
                    }
                }
                
                else if(this.check6 == false)
                {
                    if(this.getY() < 530)
                    {
                        setMotionAngle(0);
                        this.check6 = true;
                    }
                }
            }
            
            else
            {
                setMotionAngle(12);
            }
        }
        
        else if(this.path2_3 == true)
        {
            if(this.getX() > 350)
            {
                if(this.check1 == false)
                {
                    setMotionAngle(270);
                    this.check1 = true;
                }
                
                else if(this.check2 == false)
                {
                    if(this.getY() < 380)
                    {
                        setMotionAngle(0);
                        this.check2 = true;
                    }
                }
                
                else if(this.check3 == false)
                {
                    if(this.getX() > 720)
                    {
                        setMotionAngle(90);
                        this.check3 = true;
                    }
                }
                
                else if(this.check4 == false)
                {
                    if(this.getY() > 565)
                    {
                        setMotionAngle(358);
                        this.check4 = true;
                    }
                }
                
                else if(this.check5 == false)
                {
                    if(this.getX() > 950)
                    {
                        setMotionAngle(350);
                        this.check5 = true;
                    }
                }
                
                else if(this.check6 == false)
                {
                    if(this.getY() < 530)
                    {
                        setMotionAngle(0);
                        this.check6 = true;
                    }
                }
            }
            
            else
            {
                setMotionAngle(12);
            }
        }
        
        else if(this.path2_4 == true)
        {
            if(this.getX() > 350)
            {
                if(this.check1 == false)
                {
                    setMotionAngle(270);
                    this.check1 = true;
                }
                
                else if(this.check2 == false)
                {
                    if(this.getY() < 380)
                    {
                        setMotionAngle(355);
                        this.check2 = true;
                    }
                }
                
                else if(this.check3 == false)
                {
                    if(this.getX() > 860)
                    {
                        setMotionAngle(45);
                        this.check3 = true;
                    }
                }
                
                else if(this.check4 == false)
                {
                    if(this.getY() > 530)
                    {
                        setMotionAngle(0);
                        this.check4 = true;
                    }
                }
            }
            
            else
            {
                setMotionAngle(12);
            }
        }
        
        /* 
         * The following are paths from the bottom spawn
         */
        
        else if(this.path3_1 == true)
        {
            if(this.check1 == false)
                {
                    setMotionAngle(90);
                    this.check1 = true;
                }
                
                else if(this.check2 == false)
                {
                    if(this.getY() > 380)
                    {
                        setMotionAngle(0);
                        this.check2 = true;
                    }
                }
                
                else if(this.check3 == false)
                {
                    if(this.getX() > 720)
                    {
                        setMotionAngle(90);
                        this.check3 = true;
                    }
                }
                
                else if(this.check4 == false)
                {
                    if(this.getY() > 565)
                    {
                        setMotionAngle(358);
                        this.check4 = true;
                    }
                }
                
                else if(this.check5 == false)
                {
                    if(this.getX() > 900)
                    {
                        setMotionAngle(350);
                        this.check5 = true;
                    }
                }
                
                else if(this.check6 == false)
                {
                    if(this.getY() < 530)
                    {
                        setMotionAngle(0);
                        this.check6 = true;
                    }
                }
        }
        
        else if(this.path3_2 == true)
        {
            if(this.check1 == false)
            {
                setMotionAngle(90);
                this.check1 = true;
            }
                
            else if(this.check2 == false)
            {
                if(this.getY() > 380)
                {
                    setMotionAngle(355);
                    this.check2 = true;
                }
            }
                
            else if(this.check3 == false)
            {
                if(this.getX() > 860)
                {
                    setMotionAngle(45);
                    this.check3 = true;
                }
            }
                
            else if(this.check4 == false)
            {
                if(this.getY() > 530)
                {
                    setMotionAngle(0);
                    this.check4 = true;
                }
            }
        }
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
    
    public void despawn()
    {
        this.remove();
    }
}
