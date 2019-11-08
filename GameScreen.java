import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;




public class GameScreen extends BaseScreen
{
    Button HelpButton;     // Top-Right Help Btn
    Button ArcherTowerBtn;    //AKA Unit1
    
    public static float MouseX;
    public static float MouseY;
    public boolean UnitPickedUp;
    public boolean ArcherTowerPickedUp;    //true whenever an object is picked up, and false once placed in game area
    public boolean Unit2PickedUp;
    /***********************************************************************/
    BaseActor GameArea;     //Placeholder for map area
    BaseActor UnitInfoArea; //Bottom of Screen Unit info area
    BaseActor UnitArea;     //Unit buy area (Right of screen)
    BaseActor ArcherTowerMouse;
    BaseActor Unit2Mouse;
    public void initialize()
    {
        
        UnitPickedUp =  false;
        /*******************************************************************/
        /**************** Setting up UI Elements ***************************/
        GameArea = new BaseActor(0,161,mainStage);
        GameArea.loadTexture("Assets/Img/PlaceHolders/GameAreaPlaceHolder.png");
        UnitInfoArea =  new BaseActor(0,0,mainStage);
        UnitInfoArea.loadTexture("Assets/Img/PlaceHolders/UnitInfoPlaceHolder.png");
        UnitArea = new BaseActor(1350,0,mainStage);
        UnitArea.loadTexture("Assets/Img/PlaceHolders/UnitsPlaceHolder.png");
      
        /*******************************************************************/
        /*****************Sets Mouse Cursor*********************************/
        
        Pixmap pm = new Pixmap(Gdx.files.internal("Assets/Img/Cursors/CursorSword.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();
        /*******************************************************************/
        ButtonCreation HelpButton =  new ButtonCreation();
        ButtonCreation ArcherTowerBtn =  new ButtonCreation();
        ButtonCreation Unit2Btn =  new ButtonCreation();
        Unit2Btn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit2_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit2_Highlighted.png")),1480,737,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit2 button was clicked",null);
                UnitPickedUp = true;
                Unit2PickedUp = true;
                Unit2Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit2Mouse.loadTexture("Assets/Img/Towers/Unit2.png");
                Unit2Mouse.setSize(80,80);
                Unit2Mouse.setBoundaryPolygon(4);
            }
        });
        ArcherTowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit1_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit1_Highlighted.png")),1360,737,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit1 button was clicked",null);
                UnitPickedUp = true;
                ArcherTowerPickedUp = true;
                ArcherTowerMouse = new BaseActor(MouseX,MouseY,mainStage);
                ArcherTowerMouse.loadTexture("Assets/Img/Towers/ArcherTower.png");
                ArcherTowerMouse.setSize(80,80);
                ArcherTowerMouse.setBoundaryPolygon(4);

            }
        });
        HelpButton.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Help_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Help_Highlighted.png")),1300,850,32,32, new Function(){
            public void run()
            {
                Gdx.app.log("Help button was clicked",null);
            }
        });
                
    }
 
    public void update (float dt)
    {
       
        /*Slows down amount of polling, only updates when object is picked up */
        if (UnitPickedUp)
        {
            MouseX = Gdx.input.getX();
            MouseY = 900 - Gdx.input.getY();
            if (ArcherTowerPickedUp)
            {
                HoverTowerAtMousePosition(ArcherTowerMouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(ArcherTowerMouse) == true)
                {
                    new ArcherTower(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    ArcherTowerPickedUp = false;
                    ArcherTowerMouse.remove();
                    
                }
            }
            if (Unit2PickedUp)
            {
                HoverTowerAtMousePosition(Unit2Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit2Mouse) == true)
                {
                    new Unit2(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit2PickedUp = false;
                    Unit2Mouse.remove();
                    
                }
            }
            
            //Gdx.app.log("X is",Float.toString(MouseX));
            //Gdx.app.log("Y is",Float.toString(MouseY));
        }
        EscCheck(); //Checks if esc key has been hit, if hit returns to mainmenu (Eventually Level Selector)
       
       
    
    }
    /** Check for overlap when placing a new unit, false means cant be placed, true means can be placed **/
    public boolean CheckTowerPlaceMentOverlap(BaseActor ObjectBeingPlaced)
    {
         for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"ArcherTower"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedUnit2TowerActor : BaseActor.getList(mainStage,"Unit2"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedUnit2TowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        
        return true;
    }
    
    
    public void HoverTowerAtMousePosition(BaseActor TowerType)
    {
        TowerType.centerAtPosition(MouseX,MouseY);
    }
    public void EscCheck()
    {
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
        {
            //could put a prompt here if we want??
            BaseGame.setActiveScreen( new MenuScreen());
        }
    }
}
