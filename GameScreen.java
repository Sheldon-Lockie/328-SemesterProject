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
    public boolean Unit3PickedUp;
    public boolean Unit4PickedUp;
    public boolean Unit5PickedUp;
    public boolean Unit6PickedUp;
    public boolean Unit7PickedUp;
    public boolean Unit8PickedUp;
    public boolean Unit9PickedUp;
    public boolean Unit10PickedUp;
    public boolean Unit11PickedUp;
    public boolean Unit12PickedUp;
    public boolean Unit13PickedUp;
    public boolean Unit14PickedUp;
    
    /***********************************************************************/
    BaseActor GameArea;     //Placeholder for map area
    BaseActor UnitInfoArea; //Bottom of Screen Unit info area
    BaseActor UnitArea;     //Unit buy area (Right of screen)
    BaseActor ArcherTowerMouse;
    BaseActor Unit2Mouse;
    BaseActor Unit3Mouse;
    BaseActor Unit4Mouse;
    BaseActor Unit5Mouse;
    BaseActor Unit6Mouse;
    BaseActor Unit7Mouse;
    BaseActor Unit8Mouse;
    BaseActor Unit9Mouse;
    BaseActor Unit10Mouse;
    BaseActor Unit11Mouse;
    BaseActor Unit12Mouse;
    BaseActor Unit13Mouse;
    BaseActor Unit14Mouse;
    
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
        ButtonCreation Unit2TowerBtn =  new ButtonCreation();
        ButtonCreation Unit3TowerBtn =  new ButtonCreation();
        ButtonCreation Unit4TowerBtn =  new ButtonCreation();
        ButtonCreation Unit5TowerBtn =  new ButtonCreation();
        ButtonCreation Unit6TowerBtn =  new ButtonCreation();
        ButtonCreation Unit7TowerBtn =  new ButtonCreation();
        ButtonCreation Unit8TowerBtn =  new ButtonCreation();
        ButtonCreation Unit9TowerBtn =  new ButtonCreation();
        ButtonCreation Unit10TowerBtn =  new ButtonCreation();
        ButtonCreation Unit11TowerBtn =  new ButtonCreation();
        ButtonCreation Unit12TowerBtn =  new ButtonCreation();
        ButtonCreation Unit13TowerBtn =  new ButtonCreation();
        ButtonCreation Unit14TowerBtn =  new ButtonCreation();
        
        
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
        Unit2TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit2_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit2_Highlighted.png")),1480,737,110,110, new Function(){
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
    
        Unit3TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit3_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit3_Highlighted.png")),1360,624,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit3 button was clicked",null);
                UnitPickedUp = true;
                Unit3PickedUp = true;
                Unit3Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit3Mouse.loadTexture("Assets/Img/Towers/Unit3.png");
                Unit3Mouse.setSize(80,80);
                Unit3Mouse.setBoundaryPolygon(4);
            }
        }); 
         Unit4TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit4_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit4_Highlighted.png")),1480,624,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit4 button was clicked",null);
                UnitPickedUp = true;
                Unit4PickedUp = true;
                Unit4Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit4Mouse.loadTexture("Assets/Img/Towers/Unit4.png");
                Unit4Mouse.setSize(80,80);
                Unit4Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit5TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit5_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit5_Highlighted.png")),1360,511,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit5 button was clicked",null);
                UnitPickedUp = true;
                Unit5PickedUp = true;
                Unit5Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit5Mouse.loadTexture("Assets/Img/Towers/Unit5.png");
                Unit5Mouse.setSize(80,80);
                Unit5Mouse.setBoundaryPolygon(4);
            }
        }); 
         Unit6TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit6_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit6_Highlighted.png")),1480,511,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit6 button was clicked",null);
                UnitPickedUp = true;
                Unit6PickedUp = true;
                Unit6Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit6Mouse.loadTexture("Assets/Img/Towers/Unit6.png");
                Unit6Mouse.setSize(80,80);
                Unit6Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit7TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit7_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit7_Highlighted.png")),1360,398,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit7 button was clicked",null);
                UnitPickedUp = true;
                Unit7PickedUp = true;
                Unit7Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit7Mouse.loadTexture("Assets/Img/Towers/Unit7.png");
                Unit7Mouse.setSize(80,80);
                Unit7Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit8TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit8_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit8_Highlighted.png")),1480,398,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit8 button was clicked",null);
                UnitPickedUp = true;
                Unit8PickedUp = true;
                Unit8Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit8Mouse.loadTexture("Assets/Img/Towers/Unit8.png");
                Unit8Mouse.setSize(80,80);
                Unit8Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit9TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit9_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit9_Highlighted.png")),1360,285,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit9 button was clicked",null);
                UnitPickedUp = true;
                Unit9PickedUp = true;
                Unit9Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit9Mouse.loadTexture("Assets/Img/Towers/Unit9.png");
                Unit9Mouse.setSize(80,80);
                Unit9Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit10TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit10_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit10_Highlighted.png")),1480,285,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit10 button was clicked",null);
                UnitPickedUp = true;
                Unit10PickedUp = true;
                Unit10Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit10Mouse.loadTexture("Assets/Img/Towers/Unit10.png");
                Unit10Mouse.setSize(80,80);
                Unit10Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit11TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit11_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit11_Highlighted.png")),1360,172,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit11 button was clicked",null);
                UnitPickedUp = true;
                Unit11PickedUp = true;
                Unit11Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit11Mouse.loadTexture("Assets/Img/Towers/Unit11.png");
                Unit11Mouse.setSize(80,80);
                Unit11Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit12TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit12_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit12_Highlighted.png")),1480,172,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit12 button was clicked",null);
                UnitPickedUp = true;
                Unit12PickedUp = true;
                Unit12Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit12Mouse.loadTexture("Assets/Img/Towers/Unit12.png");
                Unit12Mouse.setSize(80,80);
                Unit12Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit13TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit13_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit13_Highlighted.png")),1360,59,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit13 button was clicked",null);
                UnitPickedUp = true;
                Unit13PickedUp = true;
                Unit13Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit13Mouse.loadTexture("Assets/Img/Towers/Unit13.png");
                Unit13Mouse.setSize(80,80);
                Unit13Mouse.setBoundaryPolygon(4);
            }
        }); 
        Unit14TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit14_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit14_Highlighted.png")),1480,59,110,110, new Function(){
            public void run()
            {
                Gdx.app.log("Unit14 button was clicked",null);
                UnitPickedUp = true;
                Unit14PickedUp = true;
                Unit14Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit14Mouse.loadTexture("Assets/Img/Towers/Unit14.png");
                Unit14Mouse.setSize(80,80);
                Unit14Mouse.setBoundaryPolygon(4);
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
            if (Unit3PickedUp)
            {
                HoverTowerAtMousePosition(Unit3Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit3Mouse) == true)
                {
                    new Unit3(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit3PickedUp = false;
                    Unit3Mouse.remove();
                    
                }
            }
             if (Unit4PickedUp)
            {
                HoverTowerAtMousePosition(Unit4Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit4Mouse) == true)
                {
                    new Unit4(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit4PickedUp = false;
                    Unit4Mouse.remove();
                    
                }
            }
             if (Unit5PickedUp)
            {
                HoverTowerAtMousePosition(Unit5Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit5Mouse) == true)
                {
                    new Unit5(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit5PickedUp = false;
                    Unit5Mouse.remove();
                    
                }
            }
             if (Unit6PickedUp)
            {
                HoverTowerAtMousePosition(Unit6Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit6Mouse) == true)
                {
                    new Unit6(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit6PickedUp = false;
                    Unit6Mouse.remove();
                    
                }
            }
             if (Unit7PickedUp)
            {
                HoverTowerAtMousePosition(Unit7Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit7Mouse) == true)
                {
                    new Unit7(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit7PickedUp = false;
                    Unit7Mouse.remove();
                    
                }
            }
             if (Unit8PickedUp)
            {
                HoverTowerAtMousePosition(Unit8Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit8Mouse) == true)
                {
                    new Unit8(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit8PickedUp = false;
                    Unit8Mouse.remove();
                    
                }
            }
             if (Unit9PickedUp)
            {
                HoverTowerAtMousePosition(Unit9Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit9Mouse) == true)
                {
                    new Unit9(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit9PickedUp = false;
                    Unit9Mouse.remove();
                    
                }
            }
             if (Unit10PickedUp)
            {
                HoverTowerAtMousePosition(Unit10Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit10Mouse) == true)
                {
                    new Unit10(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit10PickedUp = false;
                    Unit10Mouse.remove();
                    
                }
            }
            if (Unit11PickedUp)
            {
                HoverTowerAtMousePosition(Unit11Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit11Mouse) == true)
                {
                    new Unit11(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit11PickedUp = false;
                    Unit11Mouse.remove();
                    
                }
            }
            
            if (Unit12PickedUp)
            {
                HoverTowerAtMousePosition(Unit12Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit12Mouse) == true)
                {
                    new Unit12(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit12PickedUp = false;
                    Unit12Mouse.remove();
                    
                }
            }
            
            if (Unit13PickedUp)
            {
                HoverTowerAtMousePosition(Unit13Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit13Mouse) == true)
                {
                    new Unit13(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit13PickedUp = false;
                    Unit13Mouse.remove();
                    
                }
            }
            
            if (Unit14PickedUp)
            {
                HoverTowerAtMousePosition(Unit14Mouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 && CheckTowerPlaceMentOverlap(Unit14Mouse) == true)
                {
                    new Unit14(MouseX,MouseY,mainStage);
                    UnitPickedUp = false;
                    Unit14PickedUp = false;
                    Unit14Mouse.remove();
                    
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
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit2"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit3"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit4"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit5"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit6"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit7"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit8"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit9"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit10"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit11"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit12"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit13"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                return false;
                
            }
 
        }
        for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"Unit14"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
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
