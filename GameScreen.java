import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;




public class GameScreen extends BaseScreen
{
    Button HelpButton;
    BaseActor GameArea;
    BaseActor UnitInfoArea;
    BaseActor UnitArea;
    public void initialize()
    {
      
        GameArea = new BaseActor(0,161,mainStage);
        GameArea.loadTexture("Assets/Img/PlaceHolders/GameAreaPlaceHolder.png");
        UnitInfoArea =  new BaseActor(0,0,mainStage);
        UnitInfoArea.loadTexture("Assets/Img/PlaceHolders/UnitInfoPlaceHolder.png");
        UnitArea = new BaseActor(1350,0,mainStage);
        UnitArea.loadTexture("Assets/Img/PlaceHolders/UnitsPlaceHolder.png");
        HelpButton = new Button(650,550,uiStage);
        HelpButton.loadTexture("Assets/Img/Buttons/Help_Unhighlighted.png");
        HelpButton.setSize(32,32);
        Pixmap pm = new Pixmap(Gdx.files.internal("Assets/Img/Cursors/CursorSword.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();
        
    }
 
    public void update (float dt)
    {
     
    }
    
    
}
