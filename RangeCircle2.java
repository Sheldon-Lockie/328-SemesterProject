/**
 * Range Circle - provides hit box range for mage tower
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Color;

public class RangeCircle2 extends BaseActor
{
    // tower variables
    private float TimeBetweenShots; //in seconds
    private BaseActor RangeCircle2 = this;
    private float Timer;
    private boolean TowerCanShoot;
    private AttackHelper attackHelper;
    private float X;
    private float Y;
    
    // arrow variables
    private FireBolt fireBolt1;
    private FireBolt fireBolt2;
    
    private boolean fireBolt1InUse;
    private boolean fireBolt2InUse;
    
    // extras
    private Wizard tmpWizard;
    private Wizard targetWizard;
    private Stage MainStage;
    private boolean check;
    private boolean check2;
    private int damage;
    
    /**
     * Constructor for objects of class RangeCircle2
     */
    public RangeCircle2(float x, float y, Stage s, float SizeX, float SizeY)
    {
        super(x,y,s);
        
        MainStage = s;
       
        loadTexture("Assets/Img/Towers/TowerOverlayTransparent.png");
        setOpacity(0);
        setSize(SizeX,SizeY);      
        this.X =  GameScreen.MouseX;
        this.Y = GameScreen.MouseY;
        centerAtPosition(X,Y);
        
        TimeBetweenShots = 0.85f;
       
        setBoundaryPolygon(8);
        toBack();
        
        this.TowerCanShoot = true;
        this.Timer = 0;
             
        attackHelper = new AttackHelper();
        
        // arrow initialization - only loads 2 arrows
        fireBolt1InUse = false;
        fireBolt2InUse = false;
        fireBolt1 = new FireBolt(this.X - 5, this.Y + 20, s);
        fireBolt2 = new FireBolt(this.X, this.Y, s);

        // extras
        tmpWizard = null;
        targetWizard = null;
        check = false;
        check2 = false;
        damage = 100; // tower does 25 damage
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
                shootFireBolt(targetWizard); // shoot at that enemy
                targetWizard = null;
                tmpWizard = null;
            }
        }
        
        if (Timer >= TimeBetweenShots)
        {
            //CheckForRange(MainStage);
            this.TowerCanShoot = true;
            fireBolt1InUse = false;
            fireBolt2InUse = false;
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
            
            //WizardHandler.setColor(Color.BLUE); // set to blue if in range
            
            // if a wizard is within range of tower
            if (WizardHandler.overlaps(RangeCircle2))
            {
                //WizardHandler.setColor(Color.GRAY);
                
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
            
            //tmpWizard.setColor(Color.GOLD); // set furthest forward to gold
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
    private void shootFireBolt(Wizard target)
    {
        if(this.TowerCanShoot)
        {
            this.Timer = 0.0f;
            this.TowerCanShoot = false;
            
            if(!fireBolt1InUse)
            {
                if(target.getX() > 0 && target.getX() < 1200)
                {
                    fireBolt1.shootFireBolt(this.X - 5, this.Y + 20, target.getX(), target.getY());
                    fireBolt1InUse = true;
                }
            }
            
            // should never make it here but just in case
            else if(!fireBolt2InUse)
            {
                if(target.getX() > 0 && target.getX() < 1200)
                {
                    fireBolt2.shootFireBolt(this.X, this.Y, target.getX(), target.getY());
                    fireBolt2InUse = true;
                }
            }
            
            target.decreaseHealth(damage);
        }
    } 
}
