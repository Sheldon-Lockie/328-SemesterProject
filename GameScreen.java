import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Keys;




public class GameScreen extends BaseScreen
{
    Button HelpButton;     // Top-Right Help Btn
    Button ArcherTowerBtn;    //AKA Unit1
    
    public static float MouseX;
    public static float MouseY;
    public boolean UnitPickedUp;
    public boolean ArcherTowerPickedUp;    //true whenever an object is picked up, and false once placed in game area
    /***********************************************************************/
    BaseActor GameArea;     //Placeholder for map area
    BaseActor UnitInfoArea; //Bottom of Screen Unit info area
    BaseActor UnitArea;     //Unit buy area (Right of screen)
    BaseActor ArcherTowerMouse;
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
        ArcherTowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit1_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit1_Highlighted.png")),1360,735,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit1 button was clicked",null);
                UnitPickedUp = true;
                ArcherTowerPickedUp = true;
                ArcherTowerMouse = new BaseActor(MouseX,MouseY,mainStage);
                ArcherTowerMouse.loadTexture("Assets/Img/Towers/ArcherTower.png");
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
                HoverTowerAtMousePosition();
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1350 && MouseY > 161)
                {
                    new ArcherTower(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    ArcherTowerPickedUp = false;
                    ArcherTowerMouse.remove();
                    
                }
            }
            
            //Gdx.app.log("X is",Float.toString(MouseX));
            //Gdx.app.log("Y is",Float.toString(MouseY));
        }
        EscCheck();
       
       
    
    }
    public void HoverTowerAtMousePosition()
    {
        ArcherTowerMouse.centerAtPosition(MouseX,MouseY);
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