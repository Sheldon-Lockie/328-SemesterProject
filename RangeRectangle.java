import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Color;

// class to define the range rectangle around archer tower
public class RangeRectangle extends BaseActor
{
    // tower variables
    private float TimeBetweenShots; //in seconds
    private BaseActor RangeRectangle = this;
    private float Timer;
    private boolean TowerCanShoot;
    private AttackHelper attackHelper;
    private float X;
    private float Y;
    
    // arrow variables
    private Arrow arrow1;
    private Arrow arrow2;
    
    private boolean arrow1InUse;
    private boolean arrow2InUse;
    
    // extras
    private Wizard tmpWizard;
    private Wizard targetWizard;
    private Stage MainStage;
    private boolean check;
    private boolean check2;
    private int damage;
    
    public RangeRectangle (float x, float y, Stage s,float SizeX,float SizeY)
    {
        super(x,y,s);
        
        MainStage = s;
        
        //loadTexture("Assets/Img/Towers/Unit14.png");
        loadTexture("Assets/Img/Towers/TowerOverlayTransparent.png");
        setOpacity(90);
        setSize(SizeX,SizeY);      
        this.X =  GameScreen.MouseX;
        this.Y = GameScreen.MouseY;
        centerAtPosition(X,Y);
        
        TimeBetweenShots = 0.50f;
       
        setBoundaryPolygon(8);
        toBack();
        
        this.TowerCanShoot = true;
        this.Timer = 0;
             
        attackHelper = new AttackHelper();
        
        // arrow initialization - only loads 2 arrows
        arrow1InUse = false;
        arrow2InUse = false;
        arrow1 = new Arrow(this.X, this.Y, s);
        arrow2 = new Arrow(this.X, this.Y, s);

        // extras
        tmpWizard = null;
        targetWizard = null;
        check = false;
        check2 = false;
        damage = 25; // tower does 25 damage
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        this.Timer += dt;
        
        // indicates tower can't shoot yet
        if(this.TowerCanShoot == false)
        {
            // do nothing
        }
        
        // search for enemies and shoot
        else
        {
            targetWizard = selectTarget(); // find furthest target
                
            if(targetWizard != null)
            {
                shootArrow(targetWizard); // shoot at that enemy
                targetWizard = null;
                tmpWizard = null;
            }
        }
        
        if (Timer >= TimeBetweenShots)
        {
            //CheckForRange(MainStage);
            this.TowerCanShoot = true;
            arrow1InUse = false;
            arrow2InUse = false;
            Timer = 0.0f;
        }       
    }
    
    // will make outline visible
    public void sendToFront()
    {
        this.setOpacity(90); // make this viewable
        this.toFront();
    }
    
    // will make outline invisible
    public void sendToBack()
    {
        this.setOpacity(0); // make it non viewable
        this.toBack();
    }
    
    // selects furthest target in range
    private Wizard selectTarget()
    {      
        check = false;
        check2 = false;
        
        for (Wizard WizardHandler : attackHelper.getListWizard(MainStage,"Wizard"))
        {
            if((check == false) && (WizardHandler != null))
            {
                tmpWizard = WizardHandler;
                check = true;
            }  
            
            WizardHandler.setColor(Color.BLUE); // set to blue if in range
            
            // if a wizard is within range of tower
            if (WizardHandler.overlaps(RangeRectangle))
            {
                WizardHandler.setColor(Color.GRAY);
                
                // if the wizard is further right but less than 720
                if((WizardHandler.getX() > tmpWizard.getX()) && (WizardHandler.getX() < 720))
                {
                    // check absolute value of y and compare
                    if((Math.abs((int)WizardHandler.getY() - 500)) > (Math.abs((int)tmpWizard.getY() - 500)))
                    {
                       // indicates new wizard is further ahead so replace
                       tmpWizard = WizardHandler;        
                       check2 = true;
                    }
                }
                
                // same check but different range
                else if((WizardHandler.getX() > tmpWizard.getX()) && (WizardHandler.getX() >= 720))
                {
                    // check absolute value of y and compare
                    if((Math.abs((int)WizardHandler.getY() - 500)) < (Math.abs((int)tmpWizard.getY() - 500)))
                    {
                       // indicates new wizard is further ahead so replace
                       tmpWizard = WizardHandler;
                       check2 = true;
                    }
                }
                
                // otherwise assign as value
                else
                {
                    if(check2 == false)
                    {
                        tmpWizard = WizardHandler;
                        check2 = true;
                    }
                }                                
            }   
            
            tmpWizard.setColor(Color.GOLD); // set furthest forward to gold
        }
        
        if(check2 == false)
        {
            tmpWizard = null;
        }
      
        check = false;
        check2 = false;
        return tmpWizard; 
    }
    
    // triggers arrow to shoot
    private void shootArrow(Wizard target)
    {
        if(this.TowerCanShoot)
        {
            this.Timer = 0.0f;
            this.TowerCanShoot = false;
            
            if(!arrow1InUse)
            {
                if(target.getX() > 0 && target.getX() < 1200)
                {
                    arrow1.shootArrow(this.X, this.Y, target.getX(), target.getY());
                    arrow1InUse = true;
                }
            }
            
            // should never make it here but just in case
            else if(!arrow2InUse)
            {
                if(target.getX() > 0 && target.getX() < 1200)
                {
                    arrow2.shootArrow(this.X, this.Y, target.getX(), target.getY());
                    arrow2InUse = true;
                }
            }
            
            target.decreaseHealth(damage);
        }
    } 
}
    
