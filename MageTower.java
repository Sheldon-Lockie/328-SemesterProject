/**
 * Mage Tower class - big damage slow speed
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class MageTower extends BaseActor
{
    private RangeCircle2 rangeCircle2;
    private int sizeX, sizeY;
    
    /**
     * Constructor for objects of class MageTower
     */
    public MageTower(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("Assets/Img/Towers/MageTowerNew.png");        
        sizeX = 70;
        sizeY = 120;        
        setSize(sizeX, sizeY);
        centerAtPosition2(x, y, sizeX, sizeY);
        setBoundaryPolygon(4);
        CreateRangeCircle2(x,y,s);
        
        this.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                GameScreen.enableOutlineCheck(true); // enables check in main loop to signal outline is present
                rangeCircle2.sendToFront(); // send outline to front            
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
    public void CreateRangeCircle2(float CenterX , float CenterY, Stage s)
    {
        rangeCircle2 = new RangeCircle2(CenterX, CenterY, s, 600, 600);
    }
    
    // send rangeRectangle to background if called
    public void hideOutline()
    {
        rangeCircle2.sendToBack();
    }
}
