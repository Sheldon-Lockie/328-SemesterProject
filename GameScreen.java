import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

// class containing the main game loop and features
public class GameScreen extends BaseScreen
{
    Button HelpButton;     // Top-Right Help Btn
    Button ArcherTowerBtn;    //AKA Unit1
    
    public static float MouseX;
    public static float MouseY;
        
    /**********************Bools for unit selection*****************************/
    public boolean UnitPickedUp;
    public boolean ArcherTowerPickedUp;    //true whenever an object is picked up, and false once placed in game area
    public boolean MageTowerPickedUp;
    public boolean Unit3PickedUp;
    public boolean Unit4PickedUp;
    public boolean Unit5PickedUp;
    public boolean Unit6PickedUp;
    
    /***********************************************************************/
    BaseActor GameArea;     //Placeholder for map area
    BaseActor UnitInfoArea; //Bottom of Screen Unit info area
    BaseActor UnitArea;     //Unit buy area (Right of screen)   
    /********** Selectively updated positions for each type of unit**********/
    BaseActor ArcherTowerMouse;
    BaseActor MageTowerMouse;
    BaseActor Unit3Mouse;
    BaseActor Unit4Mouse;
    BaseActor Unit5Mouse;
    BaseActor Unit6Mouse;
    /**********************************************************************/
    
    // Game Manager
    private static GameManager manager;
    
    // extras
    private boolean lock1;
    private float timer1;
    private boolean gameOver;
    private boolean exitLock;
    private float exitTimer;
    private static boolean outlineCheck;
    private boolean RewardLock;
    
    // spawn variables
    private int numOfEnemies;
    private boolean spawnLock;
    private int spawnDelay;
    private float spawnTimer;
    private int counter;
    private boolean endOfWave;
    private int spawn1Selection;
    private int spawn2Selection;
    private int spawn3Selection;
    
    // helper
    private AttackHelper attackHelper;
    
    // ui stuff
    private CastleHealth castle;
    public static int Currency = 10000;
    
    // ui labels 
    private Label playerHealthLabel; 
    private Label castleHealthLabel;
    private Label currencyLabel;
    private Label waveLabel;
    
    // ui helpers 
    private int playerHealth;
    private float castleHealth;
    private int waveCounter;
    private BaseActor playerHealthIcon;
    private BaseActor castleHealthIcon;
    private BaseActor currencyIcon; 
    
    // baseactor helpers
    private Animation heartHealth100;
    private Animation heartHealth90;
    private Animation heartHealth80;
    private Animation heartHealth70;
    private Animation heartHealth60;
    private Animation heartHealth50;
    private Animation heartHealth40;
    private Animation heartHealth30;
    private Animation heartHealth20;
    private Animation heartHealth10;
    private Animation heartHealth0;
    
    //Cost of towers;
    public int RangeCost = 50;
    public int MageCost = 100;
    
    // sound effects
    Sound coinDrop;
    
    public void initialize()
    {
        
        BaseActor.setWorldBounds(1350, 900);
        setBooleans();      //sets any bools that need to be set on start
        InitUIElements();   //adds elements that are the background, e.x. Background of units/unit info/map img
        InitButtons();      //creates all the initial buttons that are needed (help btn, unit selection btn)
        //Currency = 100;
        RewardLock = true;
        /*******************************************************************/
        /*****************Sets Mouse Cursor*********************************/
        
        Pixmap pm = new Pixmap(Gdx.files.internal("Assets/Img/Cursors/CursorSword.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();
        
        
        // Initialize music
        // Instantiate game manager
        if(manager == null)
        {
            manager = new GameManager();
        }
        
        // Activate menu song if no other songs are playing
        if(manager.checkSongs() == false)
        {
            manager.playLevelMusic();
        }
        
        // otherwise end all other songs and then play menu music
        else
        {
            manager.endAllSongs();
            manager.playLevelMusic();
        }   
        
        // extras
        lock1 = false;
        timer1 = 0.0f;
        gameOver = false;
        exitLock = false;
        exitTimer = 0.0f;
        outlineCheck = false;
        
        numOfEnemies = 5;
        
        // start spawning enemies
        spawnEnemies();
        spawnLock = true;
        spawnTimer = 0.0f;
        spawnDelay = 5; // 5 seconds after each wave
        counter = 0;
        endOfWave = false;
        waveCounter = 0;
        spawn1Selection = 1;
        spawn2Selection = 1;
        spawn3Selection = 1;
        
        // helpers
        attackHelper = new AttackHelper();
        
        // ui stuff
        castle = new CastleHealth();
              
        // adding listener for tower purposes
        
        mainStage.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                // send rectangles to background
                if(outlineCheck == false)
                {
                    for (ArcherTower TowerHandler : attackHelper.getListArcherTower(mainStage,"ArcherTower"))
                    {
                        TowerHandler.hideOutline();
                    }
                    
                    for(MageTower MageHandler : attackHelper.getListMageTower(mainStage, "MageTower"))
                    {
                        MageHandler.hideOutline();
                    }
                }
                
                else
                {
                    outlineCheck = false;
                }
                
                //System.out.println("clicked stage");
            }
        });
        CreateBounds();
        
                // ui helper initialization //FDAFDA
        playerHealth = 0;
        castleHealth = 0;
        Currency = 0;
        waveCounter = 0;
        
        // ui initialization 
        playerHealthLabel = new Label(" x " + playerHealth, BaseGame.labelStyle); 
        castleHealthLabel = new Label(" x " + castleHealth, BaseGame.labelStyle);
        currencyLabel = new Label(" x " + Currency, BaseGame.labelStyle);
        waveLabel = new Label("Wave: " + waveCounter, BaseGame.labelStyle);
        
        playerHealthIcon = new BaseActor(0, 0, uiStage);
        //playerHealthIcon.loadTexture("assets/Img/UI Elements/Heart100.png");
        castleHealthIcon = new BaseActor(0, 0, uiStage);
        castleHealthIcon.loadTexture("assets/Img/UI Elements/Heart100.png");
        castleHealthIcon.setSize(60, 60);
        currencyIcon = new BaseActor(0, 0, uiStage);
        currencyIcon.loadTexture("assets/Img/UI Elements/Coin.png");
        currencyIcon.setSize(60, 60);
        
        //uiTable.setDebug(true);
        uiTable.pad(750, 700, 10, 10);
        uiTable.left();
        uiTable.add(castleHealthIcon).left(); //.width(80);
        uiTable.add(castleHealthLabel).left().width(160);
        uiTable.add().width(100);
        uiTable.add(currencyIcon).left().width(80);
        uiTable.add(currencyLabel).left(); //.width(200);
        uiTable.row();
        uiTable.add(waveLabel).colspan(5).width(300);
        
        // base actor initialization
        String[] health100File = {"assets/Img/UI Elements/Heart100.png"};
        heartHealth100 = castleHealthIcon.loadAnimationFromFiles(health100File, 1, true);
        String[] health90File = {"assets/Img/UI Elements/Heart90.png"};
        heartHealth90 = castleHealthIcon.loadAnimationFromFiles(health90File, 1, true);
        String[] health80File = {"assets/Img/UI Elements/Heart80.png"};
        heartHealth80 = castleHealthIcon.loadAnimationFromFiles(health80File, 1, true);
        String[] health70File = {"assets/Img/UI Elements/Heart70.png"};
        heartHealth70 = castleHealthIcon.loadAnimationFromFiles(health70File, 1, true);
        String[] health60File = {"assets/Img/UI Elements/Heart60.png"};
        heartHealth60 = castleHealthIcon.loadAnimationFromFiles(health60File, 1, true);
        String[] health50File = {"assets/Img/UI Elements/Heart50.png"};
        heartHealth50 = castleHealthIcon.loadAnimationFromFiles(health50File, 1, true);
        String[] health40File = {"assets/Img/UI Elements/Heart40.png"};
        heartHealth40 = castleHealthIcon.loadAnimationFromFiles(health40File, 1, true);
        String[] health30File = {"assets/Img/UI Elements/Heart30.png"};
        heartHealth30 = castleHealthIcon.loadAnimationFromFiles(health30File, 1, true);
        String[] health20File = {"assets/Img/UI Elements/Heart20.png"};
        heartHealth20 = castleHealthIcon.loadAnimationFromFiles(health20File, 1, true);
        String[] health10File = {"assets/Img/UI Elements/Heart10.png"};
        heartHealth10 = castleHealthIcon.loadAnimationFromFiles(health10File, 1, true);
        String[] health0File = {"assets/Img/UI Elements/Heart0.png"};
        heartHealth0 = castleHealthIcon.loadAnimationFromFiles(health0File, 1, true);
        
        // sounds
        coinDrop = Gdx.audio.newSound(Gdx.files.internal("Assets/Sounds/CoinDrop.mp3"));
    }
 
    // updates ui elements //FDAFDA
    public void updateUI()
    {       
        castleHealth = (int)castle.getHealth();
        castleHealthLabel.setText(" x " + (int)castleHealth);
        currencyLabel.setText(" x " + Currency);
        
        if(castleHealth <= 100)
        {
            // castle at top health
            if(castleHealth > 90)
            {
                castleHealthIcon.setAnimation(heartHealth100);
            }
            
            // castle is between 80 and 90
            else if(castleHealth <= 90 && castleHealth > 80)
            {
                castleHealthIcon.setAnimation(heartHealth90);
            }
            
            // castle is between 70 and 80
            else if(castleHealth <= 80 && castleHealth > 70)
            {
                castleHealthIcon.setAnimation(heartHealth80);
            }
            
            // castle is between 60 and 70
            else if(castleHealth <= 70 && castleHealth > 60)
            {
                castleHealthIcon.setAnimation(heartHealth70);
            }
            
            // castle is between 50 and 60
            else if(castleHealth <= 60 && castleHealth > 50)
            {
                castleHealthIcon.setAnimation(heartHealth60);
            }
            
            // castle is between 40 and 50
            else if(castleHealth <= 50 && castleHealth > 40)
            {
                castleHealthIcon.setAnimation(heartHealth50);
            }
            
            // castle is between 30 and 40
            else if(castleHealth <= 40 && castleHealth > 30)
            {
                castleHealthIcon.setAnimation(heartHealth40);
            }
            
            // castle is between 20 and 30
            else if(castleHealth <= 30 && castleHealth > 20)
            {
                castleHealthIcon.setAnimation(heartHealth30);
            }
            
            // castle is between 10 and 20
            else if(castleHealth <= 20 && castleHealth > 10)
            {
                castleHealthIcon.setAnimation(heartHealth20);
            }
            
            // castle is between 0 and 10
            else if(castleHealth <= 10 && castleHealth > 0)
            {
                castleHealthIcon.setAnimation(heartHealth10);
            }
            
            else if(castleHealth <= 0)
            {
                castleHealthIcon.setAnimation(heartHealth0);
            }
        }
        
        if(waveCounter == 0)
        {
            int tmp = 1;
            waveLabel.setText("Wave: " + tmp);           
        }
        
       else
       {
           waveLabel.setText("Wave: " + waveCounter);
       }        
    }
    
    public void update (float dt)
    {
        EscCheck(); //Checks if esc key has been hit, if hit returns to mainmenu (Eventually Level Selector)
        
        updateUI();
        
        // performs the following while the game is running
        if(!gameOver)
        {
            PickAndPlaceManager();  // handles picking up unit from buy area + placing it back down (Doesn't create btn for unit info yet)
            spawnEnemies(); // will run spawn method each frame
            checkEnemies(); // checks if enemies overlap castle
            checkGameStatus(); // checks if game is over
            
            /*
             * check any timers
             */ 
            if(lock1 == true)
            {
                timer1 += dt;
            }
            
            if(spawnLock == true)
            {
                if(endOfWave == true)
                {
                    spawnTimer += dt;
                    if (RewardLock == true)
                    {
                        coinDrop.play(0.2f);
                        Currency += 100;
                        RewardLock = false;
                        Gdx.app.log("The Player has this much money at the end of the round",Integer.toString(Currency));
                    }
                    
                    if(spawnTimer >= spawnDelay)
                    {
                        spawnLock = false;
                        spawnTimer = 0.0f;
                        endOfWave = false;
                        waveCounter++;
                        RewardLock = true;
                        spawn1Selection = spawnSelector1();
                        spawn2Selection = spawnSelector2();
                        spawn3Selection = spawnSelector3();
                    }
                }
            }
        }
        
        // game over sequence of actions
        else if(gameOver == true)
        {
            exitTimer += dt;
            exitToMainMenu();
        }
    }
    
    // change outline check status
    public static void enableOutlineCheck(boolean status)
    {
        outlineCheck = status;
    }
    
    // method to return to the main menu
    public void exitToMainMenu()
    {
        if(exitLock == false)
        {
            exitLock = true;
            BaseActor losingMessage = new BaseActor(0, 0, uiStage);
            losingMessage.loadTexture("Assets/Img/PlaceHolders/YouDied.png");
            losingMessage.setSize(900, 217);
            losingMessage.centerAtPosition(700, 630);
            losingMessage.setOpacity(0);
            losingMessage.addAction(Actions.fadeIn(1));
            GameArea.setOpacity(30);
            
        }
            
        // exits after 8 seconds
        if(exitTimer >= 8)
        {
            manager.endAllSongs();
            manager.stopLevelMusic();
            MainGame.setActiveScreen(new MenuScreen());
        }
    }
    
    public void setBooleans()
    {
        UnitPickedUp =  false;
    }
    
    public void InitUIElements()
    {
         /*******************************************************************/
        /**************** Setting up UI Elements ***************************/
        GameArea = new BaseActor(0,161,mainStage);
        GameArea.loadTexture("Assets/Img/Level/LevelBackground.png");
        UnitInfoArea =  new BaseActor(0,0,mainStage);
        UnitInfoArea.loadTexture("Assets/Img/PlaceHolders/UnitInfoPlaceHolder.png");
        UnitArea = new BaseActor(1350,0,mainStage);
        UnitArea.loadTexture("Assets/Img/PlaceHolders/UnitPlaceHolder.png");
        
        new Player(1100, 490, mainStage); // spawn player in
    }
    public void InitButtons()
    {
        /*******************************************************************/
        ButtonCreation HelpButton =  new ButtonCreation();
        ButtonCreation ArcherTowerBtn =  new ButtonCreation();
        ButtonCreation Unit2TowerBtn =  new ButtonCreation();
        ButtonCreation Unit3TowerBtn =  new ButtonCreation();
        ButtonCreation Unit4TowerBtn =  new ButtonCreation();
        ButtonCreation Unit5TowerBtn =  new ButtonCreation();
        ButtonCreation Unit6TowerBtn =  new ButtonCreation();
        
        
        
        ArcherTowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit1_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit1_Highlighted.png")),1360,622,110,225, new Function(){
            public void run()
            {
                if( Currency - RangeCost >= 0)
                {
                    Currency -= RangeCost;
                    //Gdx.app.log("The Currency the player has is:",Integer.toString(Currency));
                    UnitPickedUp = true;
                    ArcherTowerPickedUp = true;
                    ArcherTowerMouse = new BaseActor(MouseX,MouseY,mainStage);
                    ArcherTowerMouse.loadTexture("Assets/Img/Towers/ArcherTowerNew.png");
                    ArcherTowerMouse.setSize(70,120);
                    ArcherTowerMouse.setBoundaryPolygon(4);
                    
                }
                else
                {
                    //Gdx.app.log("The Player cant afford this:",null);
                    //Gdx.app.log("The Currency the player has is:",Integer.toString(Currency));
                }
                
            }
        });
        
        Unit2TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Unit2_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Unit2_Highlighted.png")),1480,622,110,225, new Function(){
            public void run()
            {
                if( Currency - MageCost >= 0)
                {
                    Currency -= MageCost;
                    Gdx.app.log("The Currency the player has is:",Integer.toString(Currency));
                    //Gdx.app.log("Unit2 button was clicked",null);
                    UnitPickedUp = true;
                    MageTowerPickedUp = true;
                    MageTowerMouse = new BaseActor(MouseX,MouseY,mainStage);
                    MageTowerMouse.loadTexture("Assets/Img/Towers/MageTowerNew.png");
                    MageTowerMouse.setSize(70,120);
                    MageTowerMouse.setBoundaryPolygon(4);
                }
                else
                {
                    //Gdx.app.log("The Player cant afford this:",null);
                    //Gdx.app.log("The Currency the player has is:",Integer.toString(Currency));
                }
            }
            
        });
        Unit3TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/UnitPlaceHolder_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/UnitPlaceHolder_Highlighted.png")),1360,394,110,225, new Function(){
            public void run()
            {/*
                Gdx.app.log("Unit3 button was clicked",null);
                UnitPickedUp = true;
                Unit3PickedUp = true;                
                Unit3Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit3Mouse.loadTexture("Assets/Img/Towers/Unit3.png");
                Unit3Mouse.setSize(80,80);
                Unit3Mouse.setBoundaryPolygon(4);
                */
            }
        }); 
         Unit4TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/UnitPlaceHolder_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/UnitPlaceHolder_Highlighted.png")),1480,394,110,225, new Function(){
            public void run()
            {
                /*
                Gdx.app.log("Unit4 button was clicked",null);
                UnitPickedUp = true;
                Unit4PickedUp = true;                
                Unit4Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit4Mouse.loadTexture("Assets/Img/Towers/Unit4.png");
                Unit4Mouse.setSize(80,80);
                Unit4Mouse.setBoundaryPolygon(4);
                */
            }
        }); 
        Unit5TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/UnitPlaceHolder_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/UnitPlaceHolder_Highlighted.png")),1360,170,110,225, new Function(){
            public void run()
            {
                /*
                Gdx.app.log("Unit5 button was clicked",null);
                UnitPickedUp = true;
                Unit5PickedUp = true;                
                Unit5Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit5Mouse.loadTexture("Assets/Img/Towers/Unit5.png");
                Unit5Mouse.setSize(80,80);
                Unit5Mouse.setBoundaryPolygon(4);
                */
            }
        });          Unit6TowerBtn.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/UnitPlaceHolder_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/UnitPlaceHolder_Highlighted.png")),1480,170,110,225, new Function(){
            public void run()
            {
                /*
                Gdx.app.log("Unit6 button was clicked",null);
                UnitPickedUp = true;
                Unit6PickedUp = true;                
                Unit6Mouse = new BaseActor(MouseX,MouseY,mainStage);
                Unit6Mouse.loadTexture("Assets/Img/Towers/Unit6.png");
                Unit6Mouse.setSize(80,80);
                Unit6Mouse.setBoundaryPolygon(4);
                */
            }
        }); 
        
        /*HelpButton.CreateButton(mainStage, new Texture(Gdx.files.internal("Assets/Img/Buttons/Help_Unhighlighted.png")),new Texture (Gdx.files.internal("Assets/Img/Buttons/Help_Highlighted.png")),1300,850,32,32, new Function(){
            public void run()
            {
                Gdx.app.log("Help button was clicked",null);
            }
        });*/
       
        
    }
    public void PickAndPlaceManager()
    {
        if (UnitPickedUp)
        {
            MouseX = Gdx.input.getX();
            MouseY = 900 - Gdx.input.getY();
            if (ArcherTowerPickedUp)
            {
                HoverTowerAtMousePosition(ArcherTowerMouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860)
                {
                    if (CheckTowerPlaceMentOverlap(ArcherTowerMouse) == true)
                    {
                        new ArcherTower(MouseX,MouseY,mainStage);
                        UnitPickedUp = false;
                        ArcherTowerPickedUp = false;
                        ArcherTowerMouse.remove();
                    }
                }
            }
            if (MageTowerPickedUp)
            {
                HoverTowerAtMousePosition(MageTowerMouse);
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && MouseX < 1315 &&MouseX >25 && MouseY > 190 && MouseY <860 )
                {
                    if (CheckTowerPlaceMentOverlap(MageTowerMouse) == true)
                    {
                        new MageTower(MouseX,MouseY,mainStage);
                        UnitPickedUp = false;
                        MageTowerPickedUp = false;
                        MageTowerMouse.remove();
                    }
                    
                }
            }
            /*
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
            }*/
            
            
            
            //Gdx.app.log("X is",Float.toString(MouseX));
            //Gdx.app.log("Y is",Float.toString(MouseY));
        }
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
        
         for (BaseActor PlacedArcherTowerActor : BaseActor.getList(mainStage,"NoPlacementArea"))
        {
            if (ObjectBeingPlaced.overlaps(PlacedArcherTowerActor) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                Gdx.app.log("Cant be placed here",null);
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
        /*
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
        */
        
        return true;
    }
    
    
    public void HoverTowerAtMousePosition(BaseActor TowerType)
    {
        TowerType.centerAtPosition(MouseX,MouseY);
    }
    
    // method to spawn enemies
    public void spawnEnemies()
    {
        lock1 = true;

        // checks to see if wave has ended once all enemies spawn in
        if(spawnLock)
        {
            endOfWave = attackHelper.waveCheck(mainStage);
            
        }
        
        // spawns enemies if not locked
        else if(!spawnLock)
        {  
            // spawns for first 2 waves
            if(waveCounter < 3)
            {
                if(timer1 >= 0.30f)
                {
                    new Wizard(15, 500, mainStage, spawn1Selection, true);
                    timer1 = 0.0f;
                    counter++;
                }
                
                // spawned all enemies
                if(counter > numOfEnemies)
                {
                    spawnLock = true;
                    
                    // increase enemies for next wave
                    numOfEnemies += ((int)(numOfEnemies / 2));
                    counter = 0;
                }
            }
            
            // spawns for waves 3 and 4
            else if(waveCounter > 2 && waveCounter < 5)
            {
                if(timer1 >= 0.30f)
                {
                    new Wizard(15, 500, mainStage, spawn1Selection, true);
                    new Wizard(350, 880, mainStage, spawn2Selection, true);
                    timer1 = 0.0f;
                    counter++;
                }
                
                // spawned all enemies
                if(counter > numOfEnemies)
                {
                    spawnLock = true;
                    
                    // increase enemies for next wave
                    numOfEnemies += ((int)(numOfEnemies / 2));
                    counter = 0;
                }
            }
            
            // spawns for waves after 4
            else
            {
                if(timer1 >= 0.30f)
                {
                    new Wizard(15, 500, mainStage, spawn1Selection, true);
                    new Wizard(350, 880, mainStage, spawn2Selection, true);
                    new Wizard(350, 172, mainStage, spawn3Selection, true);
                    timer1 = 0.0f;
                    counter++;
                }
                
                // spawned all enemies
                if(counter > numOfEnemies)
                {
                    spawnLock = true;
                    
                    // increase enemies for next wave
                    numOfEnemies += ((int)(numOfEnemies / 2));
                    counter = 0;
                }
            }
        }
    }
    
    public int spawnSelector1()
    {
        int spawnChoice = 0;
        
        spawnChoice = MathUtils.random(1, 4); 
        
        if(spawnChoice >= 1 && spawnChoice <= 4)
        {
            return spawnChoice;
        }
     
        else
        {
            spawnChoice = 1;
            return spawnChoice;
        }
    }
    
    public int spawnSelector2()
    {
        int spawnChoice = 0;
        
        spawnChoice = MathUtils.random(5, 6); 
        
        if(spawnChoice >= 5  && spawnChoice <= 6)
        {
            return spawnChoice;
        }
     
        else
        {
            spawnChoice = 5;
            return spawnChoice;
        }
    }
    
    public int spawnSelector3()
    {
        int spawnChoice = 0;
        
        spawnChoice = MathUtils.random(7, 8);
        
        if(spawnChoice >= 7 && spawnChoice <= 8)
        {
            return spawnChoice;
        }
     
        else
        {
            spawnChoice = 7;
            return spawnChoice;
        }
    }
    
    public void checkEnemies()
    {
        for (Wizard WizardHandler : attackHelper.getListWizard(mainStage,"Wizard"))
       {
           if (WizardHandler.getX() > 1200)
           {
               castle.reduceHealth(10f); // wizards hit for 10 damage
               WizardHandler.despawn();
           }
       }
    }
         
    public void checkGameStatus()
    {
        if(castle.getHealth() <= 0)
        {
            gameOver = true;
            System.out.print("Health = " + castle.getHealth() + "\n");
            System.out.print("GAME OVER! Castle health reached 0\n");
        }
    }
    
    public void EscCheck()
    {
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
        {
            //could put a prompt here if we want??
            manager.stopLevelMusic();
            BaseGame.setActiveScreen( new MenuScreen());
        }
    }
    public void CreateBounds()
    {
        BaseActor Num1 = new NoPlacementArea(0,465,mainStage,500,175);
        Num1.setRotation(10);
        BaseActor Num2 = new NoPlacementArea(450,161,mainStage,750,225);
        Num2.setRotation(90);
        BaseActor Num3 = new NoPlacementArea(250,560,mainStage,225,135);
        Num3.setRotation(60);
        BaseActor Num4 = new NoPlacementArea(475,350,mainStage,390,40);
        Num4.setRotation(90);
        BaseActor Num5 = new NoPlacementArea(475,700,mainStage,375,40);
        Num5.setRotation(17);
        BaseActor Num6 = new NoPlacementArea(925,510,mainStage,315,90);
        Num6.setRotation(3);
        BaseActor Num7 = new NoPlacementArea(775,325,mainStage,475,170);
        Num7.setRotation(90);
        BaseActor Num8 = new NoPlacementArea(475,375,mainStage,200,80);
        Num8.setRotation(343);
        BaseActor Num9 = new NoPlacementArea(800,300,mainStage,300,80);
        Num9.setRotation(30);
        BaseActor Num10 = new NoPlacementArea(800,575,mainStage,100,10);
        Num10.setRotation(0);
        BaseActor Num11 = new NoPlacementArea(840,800,mainStage,150,50);
        Num11.setRotation(325);
        BaseActor Num12 = new NoPlacementArea(920,740,mainStage,200,50);
        Num12.setRotation(300);
        
    }
    
    
}
