import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;




public class MenuScreen extends BaseScreen
{
    Button ExitButton;
    Button SettingsButton;
    Button PlayButton;
    private boolean ExitHover;
    private boolean PlayHover;
    private boolean SettingsHover;
    private boolean PlayToggle;
    private boolean ExitToggle;
    private boolean SettingsToggle;
    private static int ViewportHeight = 900;
    private static int ViewportWidth = 1600;
    
    public void initialize()
    {
        BaseActor gameTitle = new BaseActor(0,0, mainStage);
        gameTitle.loadTexture("assets/GameTitle.png");
        gameTitle.centerAtPosition(800,450);
        gameTitle.moveBy(0,300);
        
        
        PlayHover = false;
        SettingsHover =  false;
        ExitHover =  false;
        PlayToggle =  false;
        SettingsToggle = false;
        ExitToggle = false;
        PlayButton = new Button(700,550,uiStage);
        PlayButton.loadTexture("Assets/Img/Buttons/Play_Unhighlighted.png");
        SettingsButton = new Button(700,400,uiStage);
        SettingsButton.loadTexture("Assets/Img/Buttons/Settings_Unhighlighted.png");
        ExitButton = new Button(700,250,uiStage);
        ExitButton.loadTexture("Assets/Img/Buttons/Exit_Unhighlighted.png");
        Pixmap pm = new Pixmap(Gdx.files.internal("Assets/Img/Cursors/CursorSword.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();
    }
 
    public void update (float dt)
    {
           //Gdx.app.log("Mouse X location is ",Float.toString(Gdx.input.getX()));
           //Gdx.app.log("Mouse Y location is ",Float.toString(Gdx.input.getY()));
            if (Gdx.input.getX() > 700 && Gdx.input.getX() < 900)
            {
                if (Gdx.input.getY() > 275.0f && Gdx.input.getY() < 350.0f)
                {
                    PlayHover = true;
                    if (PlayToggle == false)
                    {
                        PlayToggle = true;
                        //Gdx.app.log("Mouse X location is ",Float.toString(Gdx.input.getX()));
                        //Gdx.app.log("Mouse Y location is ",Float.toString(Gdx.input.getY()));
                        //Gdx.app.log("The Play Button is being hovered over",null);
                        PlayButton.remove();
                        PlayButton = new Button(700,550,uiStage);
                        PlayButton.loadTexture("Assets/Img/Buttons/Play_Highlighted.png");
                        
                   }
                   if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
                   {
                        //Gdx.app.log("Play Button Clicked",null);
                        /****************************************************************/
                        /********************This is where next scene is called**********/ 
                        
                        BaseGame.setActiveScreen(new GameScreen());
                        /***************************************************************/
                   }
                   
                }
                else
                {
                    PlayToggle =  false;
                    PlayHover = false;
                    PlayButton.remove();
                    PlayButton = new Button(700,550,uiStage);
                    PlayButton.loadTexture("Assets/Img/Buttons/Play_Unhighlighted.png");     
                }
                if (Gdx.input.getY() > 425.0f && Gdx.input.getY() < 500.0f)
                {
                    SettingsHover = true;
                    if (SettingsToggle == false)
                    {
                        SettingsToggle = true;
                        //Gdx.app.log("Mouse X location is ",Float.toString(Gdx.input.getX()));
                        //Gdx.app.log("Mouse Y location is ",Float.toString(Gdx.input.getY()));
                        //Gdx.app.log("The Play Button is being hovered over",null);
                        SettingsButton.remove();
                        SettingsButton = new Button(700,400,uiStage);
                        SettingsButton.loadTexture("Assets/Img/Buttons/Settings_Highlighted.png");
                    }
                     if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
                     {    
                         BaseGame.setActiveScreen( new SettingsScreen());
                         //Gdx.app.log("Settings Button Clicked",null);
                     }
                   
                }
                else
                {
                    SettingsToggle =  false;
                    SettingsHover = false;
                    SettingsButton.remove();
                    SettingsButton = new Button(700,400,uiStage);
                    SettingsButton.loadTexture("Assets/Img/Buttons/Settings_Unhighlighted.png");     
                }
                if (Gdx.input.getY() > 575.0f && Gdx.input.getY() < 650.0f)
                {
                    ExitHover = true;
                    if (ExitToggle == false)
                    {
                        ExitToggle = true;
                        //Gdx.app.log("Mouse X location is ",Float.toString(Gdx.input.getX()));
                        //Gdx.app.log("Mouse Y location is ",Float.toString(Gdx.input.getY()));
                        //Gdx.app.log("The Exit Button is being hovered over",null);
                        ExitButton.remove();
                        ExitButton = new Button(700,250,uiStage);
                        ExitButton.loadTexture("Assets/Img/Buttons/Exit_Highlighted.png");
                        
                        
                   }
                   if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
                   {
                        Gdx.app.log("Exit Button Clicked",null);
                        Gdx.app.exit();
                   }
                   
                }
                else
                {
                    ExitToggle =  false;
                    ExitHover = false;
                    ExitButton.remove();
                    ExitButton = new Button(700,250,uiStage);
                    ExitButton.loadTexture("Assets/Img/Buttons/Exit_Unhighlighted.png");     
                }
                //Gdx.app.log("The button  is being hovered over",null);
           }
           else
           {
               PlayToggle =  false;
               PlayHover = false;
               PlayButton.remove();
               PlayButton = new Button(700,550,uiStage);
               PlayButton.loadTexture("Assets/Img/Buttons/Play_Unhighlighted.png");
               SettingsToggle =  false;
               SettingsHover = false;
               SettingsButton.remove();
               SettingsButton = new Button(700,400,uiStage);
               SettingsButton.loadTexture("Assets/Img/Buttons/Settings_Unhighlighted.png"); 
               ExitToggle =  false;
               ExitHover = false;
               ExitButton.remove();
               ExitButton = new Button(700,250,uiStage);
               ExitButton.loadTexture("Assets/Img/Buttons/Exit_Unhighlighted.png");
            
           }
            
    }
    
    
}