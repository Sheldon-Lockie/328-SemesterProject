/**
 * This will act as overlay for player to attack enemies
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Sound;

public class PlayerOverlay extends BaseActor
{
    // attack variables
    private float timeBetweenAttacks;
    private float Timer;
    private boolean heroCanAttack;
    
    // extras
    private Wizard tmpWizard;
    private Stage MainStage;
    private boolean check;
    private boolean check2;
    private int damage;
    private Player heroActor;
    private AttackHelper attackHelper;
    private int counter;
    private int counter2;
    private Wizard checkWizard;
    private boolean enemyDied;
    
    private Sound swordSwingSound;
    private float soundTimer;
    
    /**
     * Constructor for objects of class PlayerOverlay
     */
    public PlayerOverlay(float x, float y, Stage s,float SizeX,float SizeY)
    {
        super(x, y, s);
        
        MainStage = s;
        
        heroActor = s.getRoot().findActor("hero");        
        loadTexture("Assets/Img/Player/PlayerOverlay.png");
        setOpacity(0);
        setSize(SizeX, SizeY);
        centerAtActor(heroActor);
        moveBy(-15.0f, 0.0f);
        
        // variable initialization
        timeBetweenAttacks = 0.30f;
        setBoundaryRectangle();
        heroCanAttack = true;
        
        // extra initialization
        tmpWizard = null;
        checkWizard = null;
        check = false;
        check2 = false;
        damage = 50; // hero does 50 damage         
        attackHelper = new AttackHelper();   
        counter = 0;
        counter2 = 0;
        enemyDied = false;
        
        swordSwingSound = Gdx.audio.newSound(Gdx.files.internal("Assets/Sounds/Sword.mp3"));
        soundTimer = 0.0f;
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        // move box with hero
        centerAtActor(heroActor);
        moveBy(-15.0f, 0.0f);
        
        Timer += dt;
        if(Timer >= timeBetweenAttacks)
        {
            heroCanAttack = true;
        }
        
        soundTimer += dt;
        
        // find and attack enemies if hero isn't knocked out
        if(heroActor.knockedOutStatus() == false)
        {
            performAttack(); // attack
            selectTargets(); // then select new target if needed
        }
        
        else
        {
            if(tmpWizard != null)
            {
                tmpWizard.disengage();
                heroActor.engaged(false, tmpWizard);
                tmpWizard = null;
            }
        }
    }
    
    public void performAttack()
    {
        if(heroCanAttack)
        {
            if(tmpWizard != null)
            {
                enemyDied = tmpWizard.decreaseHealthFromHero(damage);
                
                // play if timer reaches limit
                if(soundTimer > 1.5f)
                {
                    swordSwingSound.play(0.1f);     
                    soundTimer = 0.0f;
                }
                
                if(enemyDied == true)
                {
                    counter = 0;
                    heroActor.engaged(false, tmpWizard);
                }
            }
            
            heroCanAttack = false;
            Timer = 0.0f;
        }
    }
    
    // select up to 3 enemies
    public void selectTargets()
    {
        check = false;
        check2 = false;
        counter2 = 0;
        
        for(Wizard WizardHandler : attackHelper.getListWizard(MainStage,"Wizard"))
        {
            // if enemy is within range
            if(WizardHandler.overlaps(this))
            {
                counter2++;
                // if engaged with 3 or less enemies attack them
                if(counter < 1)
                {
                    tmpWizard = WizardHandler;
                    
                    if(tmpWizard != checkWizard)
                    {
                        attack();
                        heroCanAttack = false; // pause before next hit
                        counter++;
                    }
                }
            }
        }
        
        // if no one in range then counter is 0 and not engaged
        if(counter2 == 0)
        {
            if(tmpWizard != null)
            {
                tmpWizard.disengage();
                tmpWizard = null;
            }
            
            heroActor.engaged(false, tmpWizard);
            counter = 0;
        }
        
        counter2 = 0;        
    }
    
    public void attack()
    {
        System.out.print("engaged enemy\n"); 
        
        checkWizard = tmpWizard;
        tmpWizard.engaged(); // lock wizard into combat
        heroActor.engaged(true, tmpWizard); // lock hero into combat animation
    }
    
    // when enemy dies decrease enemy engagement count
    public void enemyDied()
    {
        if(counter > 0)
        {
            counter--;
        }
        
        if(counter < 0)
        {
            counter = 0;
        }
    }
}
