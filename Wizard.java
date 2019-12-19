import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Wizard extends BaseActor
{   
    private Animation walkingAnimation;
    private float frameDuration;
    
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
    private boolean path_backwards;
    
    private boolean normalStatus;

    
    // extras for fighting
    private Player heroActor;
    private boolean canFight;
    private float fightTimer;
    private boolean engagedFlag;
    private float fightDelay;
    private float savedAngle;
    private boolean doubleChecky;
    private boolean savedCheck1;
    private boolean savedCheck2;
    private boolean savedCheck3;
    private boolean savedCheck4;
    private boolean savedCheck5;
    private boolean savedCheck6;
    private int damage;
    
    public Wizard (float x, float y, Stage s, int path, boolean normal)
    {
        super(x,y,s);
        
        frameDuration = 0.2f;
        String[] walkingFiles = {"Assets/Img/Enemies/WizardWalkBase.png",
            "Assets/Img/Enemies/WizardWalk1.png"};
        walkingAnimation = loadAnimationFromFiles(walkingFiles, frameDuration, true);
        setAnimation(walkingAnimation);
        
        setSize(30,41);
        setBoundaryPolygon(8);

        health = 100;
        
        // pathing initialization
        check1 = false;
        check2 = false;
        check3 = false;
        check4 = false;
        check5 = false;
        check6 = false;
        
        savedCheck1 = check1;
        savedCheck2 = check2;
        savedCheck3 = check3;
        savedCheck4 = check4;
        savedCheck5 = check5;
        savedCheck6 = check6;
        
        path1_1 = false;
        path1_2 = false;

        path2_1 = false;
        path2_2 = false;
        path2_3 = false;
        path2_4 = false;
        path3_1 = false;
        path3_2 = false;
        path_backwards = false;
        
        normalStatus = normal;

        determinePath(path);
        
        // extras
        canFight = true;
        fightTimer = 0.0f;
        engagedFlag = false;
        heroActor = s.getRoot().findActor("hero");
        fightDelay = 1.5f;
        savedAngle = 0.0f;
        doubleChecky = false;
        damage = 5;
        
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
        
        if(!canFight)
        {
            fightTimer += dt;
            
            if(fightTimer >= fightDelay)
            {
                fightTimer = 0.0f;
                canFight = true;
            }
        }
        
        followPath();
    }
    
    public boolean returnSpecialCase()
    {
        return normalStatus;
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
            case 20:
                path_backwards = true;
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
    
    public void fight()
    {
        // remove health from hero if possible
        if(canFight)
        {
            setSpeed(0);
            
            heroActor.removeHealth(damage);
            canFight = false;
            fightTimer = 0.0f;
            System.out.print("Hero health is: " + heroActor.retrieveHealth() + "\n");
        }
    }
    
    // will send character along a set path
    public void followPath()
    {   
        // Just in case check - boundary check
        if(getX() > 1205)
        {
            this.remove();
        }
        
        if(engagedFlag == true)
        {    
            setMotionAngle(savedAngle);
            fight();
        }
        
        else if(doubleChecky == true)
        {
            doubleChecky = false;
            setMotionAngle(savedAngle);
            setSpeed(100);
        }
        
        /*
         * The following are paths from the top spawn
         */
        else if(this.path1_1 == true)
        {
            setSpeed(100);
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
            setSpeed(100);
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
            setSpeed(100);
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
            setSpeed(100);
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
            setSpeed(100);
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
            setSpeed(100);
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
            setSpeed(100);
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
            setSpeed(100);
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
        
        // for other class purposes
        else if(this.path_backwards == true)
        {
            setSpeed(100);
            setMotionAngle(180);
        }
    }
                
    // will decrease the wizard health by the damage parameter
    public void decreaseHealth(int damage)
    {
        this.health -= damage;
        
        // indicates death
        if(this.health <= 0)
        {
            GameScreen.Currency++;
            this.remove();
        }
    }
    
    // will decrease the wizard health by the damage parameter
    public boolean decreaseHealthFromHero(int damage)
    {
        this.health -= damage;
        
        // indicates death
        if(this.health <= 0)
        {
            this.remove();
            return true; // he died           
        }
        
        else
        {
            return false; // still alive
        }
    }
    
    // keeps wizard engaged with hero
    public void engaged()
    {
        engagedFlag = true;
        doubleChecky = true;
        savedCheck1 = check1;
        savedCheck2 = check2;
        savedCheck3 = check3;
        savedCheck4 = check4;
        savedCheck5 = check5;
        savedCheck6 = check6;
        savedAngle = getMotionAngle();
        
        if(savedCheck6 == true)
        {
            check6 = false;
        }
        
        else if(savedCheck5 == true)
        {
            check5 = false;
        }
        
        else if(savedCheck4 == true)
        {
            check4 = false;
        }
        
        else if(savedCheck3 == true)
        {
            check3 = false;
        }
        
        else if(savedCheck2 == true)
        {
            check2 = false;
        }
        
        else if(savedCheck1 == true)
        {
            check1 = false;
        }
    }
    
    // disengage from hero
    public void disengage()
    {
        engagedFlag = false;
    }
    
    public void despawn()
    {
        this.remove();
    }
}
