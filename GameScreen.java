import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Keys;




public class GameScreen extends BaseScreen
{
    Button HelpButton;
    Button ArcherTower;
    boolean HelpToggle;
    boolean HelpHover;
    BaseActor GameArea;
    BaseActor UnitInfoArea;
    BaseActor UnitArea;
    public void initialize()
    {
        HelpToggle = false;
        HelpHover =  false;
        GameArea = new BaseActor(0,161,mainStage);
        GameArea.loadTexture("Assets/Img/PlaceHolders/GameAreaPlaceHolder.png");
        UnitInfoArea =  new BaseActor(0,0,mainStage);
        UnitInfoArea.loadTexture("Assets/Img/PlaceHolders/UnitInfoPlaceHolder.png");
        UnitArea = new BaseActor(1350,0,mainStage);
        UnitArea.loadTexture("Assets/Img/PlaceHolders/UnitsPlaceHolder.png");
        HelpButton = new Button(1300,850,uiStage);
        HelpButton.loadTexture("Assets/Img/Buttons/Help_Unhighlighted.png");
        HelpButton.setSize(32,32);
        ArcherTower =  new Button(1360,735,uiStage);
        ArcherTower.loadTexture("Assets/Img/Buttons/Unit1_Unhighlighted.png");
        ArcherTower.setSize(110,110);
        
        
        Pixmap pm = new Pixmap(Gdx.files.internal("Assets/Img/Cursors/CursorSword.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();
        /*ButtonCreation HelpButton =  new ButtonCreation();
        
        HelpButton.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Help_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Help_Highlighted.png")),1300,850, new Function(){
            public void run()
            {
                Gdx.app.log("Help button was clicked",null);
            }
        });*/
        
    }
 
    public void update (float dt)
    {
        HelpButtonCheck();
        EscCheck();
       
    
    }
    public void EscCheck()
    {
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
        {
            //could put a prompt here if we want??
            BaseGame.setActiveScreen( new MenuScreen());
        }
    }
    public void HelpButtonCheck()
    {
      if (Gdx.input.getX() > 1300 && Gdx.input.getX() < 1350)
            {
                if (Gdx.input.getY() > 20.0f && Gdx.input.getY() < 50.0f)
                {
                    HelpHover = true;
                    if (HelpToggle == false)
                    {
                        HelpToggle = true;
                        //Gdx.app.log("Mouse X location is ",Float.toString(Gdx.input.getX()));
                        //Gdx.app.log("Mouse Y location is ",Float.toString(Gdx.input.getY()));
                        //Gdx.app.log("The Play Button is being hovered over",null);
                        HelpButton.remove();
                        HelpButton = new Button(1300,850,uiStage);
                        
                        HelpButton.loadTexture("Assets/Img/Buttons/Help_Highlighted.png");
                        HelpButton.setSize(32,32);
                   }
                   if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
                   {
                        Gdx.app.log("Help Button Was Clicked",null);
                        /****************************************************************/
                        /********************This is where next scene is called**********/ 
                        
                        
                        /***************************************************************/
                   }
                   
                }
                else
                {
                    HelpToggle =  false;
                    HelpHover = false;
                    HelpButton.remove();
                    HelpButton = new Button(1300,850,uiStage);
                    HelpButton.loadTexture("Assets/Img/Buttons/Help_Unhighlighted.png");     
                    HelpButton.setSize(32,32);
                }  
               
        
    }
    else
                {
                    HelpToggle =  false;
                    HelpHover = false;
                    HelpButton.remove();
                    HelpButton = new Button(1300,850,uiStage);
                    HelpButton.loadTexture("Assets/Img/Buttons/Help_Unhighlighted.png");     
                    HelpButton.setSize(32,32);
                }  
    
}
}
