import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;




public class GameScreen extends BaseScreen
{
    Button HelpButton;
    BaseActor GameArea;
    BaseActor UnitInfoArea;
    BaseActor UnitArea;
    public void initialize()
    {
        HelpButton = new Button(600,550,uiStage);
        HelpButton.loadTexture("Assets/Img/Buttons/Help_Unhighlighted.png");
        HelpButton.setSize(32,32);
        GameArea = new BaseActor(0,100,mainStage);
        GameArea.loadTexture("Assets/Img/PlaceHolders/GameAreaPlaceHolder.png");
        UnitInfoArea =  new BaseActor(0,0,mainStage);
        UnitInfoArea.loadTexture("Assets/Img/PlaceHolders/UnitInfoPlaceHolder.png");
        UnitArea = new BaseActor(700,100,mainStage);
        UnitArea.loadTexture("Assets/Img/PlaceHolders/UnitsPlaceHolder.png");
    }
 
    public void update (float dt)
    {
     
    }
    
    
}
