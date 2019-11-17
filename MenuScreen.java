import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

// This class will manage the menu screen
public class MenuScreen extends BaseScreen
{   
    // Button variables
    private int xPosition;
    private int yPosition;
    private int xSize;
    private int ySize;
    private ButtonCreation startButton;
    private ButtonCreation helpButton;
    private ButtonCreation settingsButton;
    private ButtonCreation exitButton;
    
    // Viewport variables
    private static int ViewportHeight;
    private static int ViewportWidth;
    
    // Background variables
    private BaseActor backgroundImage;
    private BaseActor gameTitle;
    private BaseActor swordTitle;
    
    // Game Manager variable
    private static GameManager manager;
    
    // Sword cursor variable
    private Pixmap pm; 
    
    public void initialize()
    {
        // Add background image
        backgroundImage = new BaseActor(0,0, mainStage);
        // Image found at https://wallpaperbro.com/purple-castle
        backgroundImage.loadTexture("Assets/Img/Menu/Background.jpg");
        backgroundImage.setSize(1600, 900);
        
        // Add game title
        gameTitle = new BaseActor(0,0, mainStage);
        gameTitle.loadTexture("Assets/Img/Menu/GameTitle-2.png");
        gameTitle.centerAtPosition(800, 450);
        gameTitle.moveBy(0, 300);
        
        // Add Sword into title
        swordTitle = new BaseActor(0, 0, mainStage);
        swordTitle.loadTexture("Assets/Img/Menu/Sword.png");  
        swordTitle.setSize(270, 270);
        swordTitle.centerAtPosition(960, 450);
        swordTitle.moveBy(0, 300);
        
        // Initialize all buttons
        ButtonCreator();
        
        // Set screen to 1600 by 900
        ViewportHeight = 900;
        ViewportWidth = 1600;
        
        // Instantiate game manager
        if(manager == null)
        {
            manager = new GameManager();
        }
        
        // Activate menu song if no other songs are playing
        if(manager.checkSongs() == false)
        {
            manager.playMenuMusic();
        }
        
        // otherwise end all other songs and then play menu music
        else
        {
            manager.endAllSongs();
            manager.playMenuMusic();
        }    
        
        // Sword cursor intialization
        pm = new Pixmap(Gdx.files.internal("Assets/Img/Cursors/CursorSword.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();
    }
 
    public void update (float dt)
    {
   
    }
    
    private void ButtonCreator()
    {
        // Initialize start button variables
        startButton = new ButtonCreation(); 
        xPosition = 675;
        yPosition = 500;
        xSize = 250;
        ySize = 100;
        startButton.CreateButton(mainStage,
            new Texture(Gdx.files.internal("Assets/Img/Menu/Buttons/PlayButton1.png")),
            new Texture(Gdx.files.internal("Assets/Img/Menu/Buttons/Playbutton2.png")),
            xPosition, yPosition,
            xSize, ySize,
            new Function()
            {
                public void run()
                {
                    manager.stopMenuMusic();
                    BaseGame.setActiveScreen(new GameScreen());
                }
            });    
            
        // Initialize help button variables
        helpButton = new ButtonCreation(); 
        xPosition = 525;
        yPosition = 350;
        xSize = 250;
        ySize = 100;
        startButton.CreateButton(mainStage,
            new Texture(Gdx.files.internal("Assets/Img/Menu/Buttons/HelpButton1.png")),
            new Texture(Gdx.files.internal("Assets/Img/Menu/Buttons/HelpButton2.png")),
            xPosition, yPosition,
            xSize, ySize,
            new Function()
            {
                public void run()
                {
                    BaseGame.setActiveScreen(new HelpScreen());
                }
            });    
            
        // Initialize settings button variables
        settingsButton = new ButtonCreation(); 
        xPosition = 825;
        yPosition = 350;
        xSize = 250;
        ySize = 100;
        startButton.CreateButton(mainStage,
            new Texture(Gdx.files.internal("Assets/Img/Menu/Buttons/SettingsButton1.png")),
            new Texture(Gdx.files.internal("Assets/Img/Menu/Buttons/SettingsButton2.png")),
            xPosition, yPosition,
            xSize, ySize,
            new Function()
            {
                public void run()
                {
                    BaseGame.setActiveScreen(new SettingsScreen());
                }
            });    
            
        // Initialize exit button variables
        exitButton = new ButtonCreation(); 
        xPosition = 675;
        yPosition = 200;
        xSize = 250;
        ySize = 100;
        startButton.CreateButton(mainStage,
            new Texture(Gdx.files.internal("Assets/Img/Menu/Buttons/ExitButton1.png")),
            new Texture(Gdx.files.internal("Assets/Img/Menu/Buttons/ExitButton2.png")),
            xPosition, yPosition,
            xSize, ySize,
            new Function()
            {
                public void run()
                {
                    manager.stopMenuMusic();
                    Gdx.app.exit();
                }
            });        
    }
}