import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class RangeRectangle extends BaseActor
{
    public float TimeBetweenShots = 1; //in seconds
    BaseActor RangeRectangle = this;
    public float Timer;
    public boolean TowerCanShoot;
    Stage MainStage;
    public RangeRectangle (float x, float y, Stage s,float SizeX,float SizeY)
    {
        super(x,y,s);
        MainStage = s;
        loadTexture("Assets/Img/Towers/Unit14.png");
        setOpacity(0);
        setSize(SizeX,SizeY);
        centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
        setBoundaryPolygon(4);
        CheckForRange(s);
        this.TowerCanShoot = true;
        this.Timer = 0;
        
        
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
       for (BaseActor WizardHandler : BaseActor.getList(s,"Wizard"))
       {
           if (WizardHandler.overlaps(RangeRectangle))
           {
               
               if (this.TowerCanShoot)
               {
                   this.Timer  = 0;
                   Gdx.app.log("An Archer Tower Shot",null);
                   this.TowerCanShoot = false;
                   WizardHandler.decreaseHealth(50);
               }
               
           }
           
       }
       
        
    }
}
    
