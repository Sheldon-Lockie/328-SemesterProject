import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;




public class GameScreen extends BaseScreen
{
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
        Pixmap pm = new Pixmap(Gdx.files.internal("Assets/Img/Cursors/CursorSword.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();
        ButtonCreation HelpButton =  new ButtonCreation();
        
        HelpButton.CreateButton(uiStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Help_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Help_Highlighted.png")),1300,850, new Function(){
            public void run()
            {
                Gdx.app.log("Help button was clicked",null);
            }
        });
        
    }
 
    public void update (float dt)
    {
     
    
    
    
    }
}