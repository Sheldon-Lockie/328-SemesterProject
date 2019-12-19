/**
 * Creates visible health bar for enemies
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;
    
public class HealthBar extends BaseActor
{
    // player variables
    //private Player heroActor;
    private Wizard tmpWizard;
    private int playerHealth;
    
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
    
    private float sizeX, sizeY;
   

    /**
     * Constructor for objects of class HealthBar
     */
    public HealthBar(float x, float y, Stage s)
    {
        super(x,y,s);
        
        //heroActor = s.getRoot().findActor("hero");
        playerHealth = 0;
        
        // base actor initialization
        String[] health100File = {"assets/Img/UI Elements/Heart100.png"};
        heartHealth100 = loadAnimationFromFiles(health100File, 1, true);
        String[] health90File = {"assets/Img/UI Elements/Heart90.png"};
        heartHealth90 = loadAnimationFromFiles(health90File, 1, true);
        String[] health80File = {"assets/Img/UI Elements/Heart80.png"};
        heartHealth80 = loadAnimationFromFiles(health80File, 1, true);
        String[] health70File = {"assets/Img/UI Elements/Heart70.png"};
        heartHealth70 = loadAnimationFromFiles(health70File, 1, true);
        String[] health60File = {"assets/Img/UI Elements/Heart60.png"};
        heartHealth60 = loadAnimationFromFiles(health60File, 1, true);
        String[] health50File = {"assets/Img/UI Elements/Heart50.png"};
        heartHealth50 = loadAnimationFromFiles(health50File, 1, true);
        String[] health40File = {"assets/Img/UI Elements/Heart40.png"};
        heartHealth40 = loadAnimationFromFiles(health40File, 1, true);
        String[] health30File = {"assets/Img/UI Elements/Heart30.png"};
        heartHealth30 = loadAnimationFromFiles(health30File, 1, true);
        String[] health20File = {"assets/Img/UI Elements/Heart20.png"};
        heartHealth20 = loadAnimationFromFiles(health20File, 1, true);
        String[] health10File = {"assets/Img/UI Elements/Heart10.png"};
        heartHealth10 = loadAnimationFromFiles(health10File, 1, true);
        String[] health0File = {"assets/Img/UI Elements/Heart0.png"};
        heartHealth0 = loadAnimationFromFiles(health0File, 1, true);
        
        sizeX = 35;
        sizeY = 35;
        
        setSize(sizeX, sizeY);
        
        //followPlayer();
        
        setAnimation(heartHealth100); // default to 100 health
    }

    public void updateHealth()
    {
        this.toFront();
        playerHealth = tmpWizard.retrieveHealth();
        
        if(playerHealth > 90 && playerHealth <= 100)
        {
            setAnimation(heartHealth100);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 80 && playerHealth <= 90)
        {
            setAnimation(heartHealth90);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 70 && playerHealth <= 80)
        {
            setAnimation(heartHealth80);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 60 && playerHealth <= 70)
        {
            setAnimation(heartHealth70);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 50 && playerHealth <= 60)
        {
            setAnimation(heartHealth60);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 40 && playerHealth <= 50)
        {
            setAnimation(heartHealth50);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 30 && playerHealth <= 40)
        {
            setAnimation(heartHealth40);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 20 && playerHealth <= 30)
        {
            setAnimation(heartHealth30);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 10 && playerHealth <= 20)
        {
            setAnimation(heartHealth20);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth > 0 && playerHealth <= 10)
        {
            setAnimation(heartHealth10);
            setSize(sizeX, sizeY);
            this.toFront();
        }
        
        else if(playerHealth <= 0)
        {
            setAnimation(heartHealth0);
            setSize(sizeX, sizeY);
            this.toFront();
        } 
    } 
}
