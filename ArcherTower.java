import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

// creates archer tower and arrow objects
public class ArcherTower extends BaseActor
{
    private RangeRectangle rangeRectangle;
    private int sizeX, sizeY;
        
    public ArcherTower (float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/ArcherTower.png");
        centerAtPosition(GameScreen.MouseX,GameScreen.MouseY);
        sizeX = 80;
        sizeY = 80;
        setSize(sizeX, sizeY);
        setBoundaryPolygon(4);
        CreateRangeRectangle(x,y,s);   
        this.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                GameScreen.enableOutlineCheck(true); // enables check in main loop to signal outline is present
                rangeRectangle.sendToFront(); // send outline to front            
                System.out.println("clicked tower");
            }
        });
    }
    
    public void act(float dt)
    {
        super.act(dt);
        this.toFront();
    }
    
    // creates rectangle for collisions and spawns arrows as necessary
    public void CreateRangeRectangle(float CenterX , float CenterY, Stage s)
    {
        rangeRectangle = new RangeRectangle(CenterX, CenterY, s, 600, 600);
    }
    
    // send rangeRectangle to background if called
    public void hideOutline()
    {
        rangeRectangle.sendToBack();
    }
}
    