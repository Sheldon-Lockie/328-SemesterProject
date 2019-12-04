import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class RangeRectangle extends BaseActor
{
    public float TimeBetweenShots = .50f; //in seconds
    BaseActor RangeRectangle = this;
    public float Timer;
    public boolean TowerCanShoot;
    Stage MainStage;
    private AttackHelper attackHelper;
    float X;
    float Y;
    public RangeRectangle (float x, float y, Stage s,float SizeX,float SizeY)
    {
        super(x,y,s);
        MainStage = s;
        loadTexture("Assets/Img/Towers/Unit14.png");
        setOpacity(0);
        setSize(SizeX,SizeY);
        this.X =  GameScreen.MouseX;
        this.Y = GameScreen.MouseY;
        centerAtPosition(X,Y);
       
        setBoundaryPolygon(4);
        CheckForRange(s);
        this.TowerCanShoot = true;
        this.Timer = 0;
        
        
        attackHelper = new AttackHelper();
        
    }
    public void act(float dt)
    {
        super.act(dt);
        CheckForRange(MainStage);
        this.Timer += Gdx.graphics.getDeltaTime();
        if (Timer >= TimeBetweenShots)
        {
            this.TowerCanShoot =  true;
        }
        
        
    }
       
    public void CheckForRange(Stage s)
    {
       for (Wizard WizardHandler : attackHelper.getListWizard(s,"Wizard"))
       {
           if (WizardHandler.overlaps(RangeRectangle))
           {          
               if (this.TowerCanShoot)
               {
                   this.Timer  = 0;
                   //Gdx.app.log("An Archer Tower Shot",null);
                   this.TowerCanShoot = false;
                   new Arrow(this.X,this.Y,s,WizardHandler.getX(),WizardHandler.getY());
                   
                   WizardHandler.decreaseHealth(25);
               }             
           }          
       }
    }   
}
    
