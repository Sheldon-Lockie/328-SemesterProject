/**
 * This class provides methods to towers and the hero
 * for proper referencign of the enemy characters
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AttackHelper
{
    // Initialization
    public AttackHelper()
    {
        // no initialization
    }
    
    // retrieves a list of all wizard actors to reference their methods
    public static ArrayList<Wizard> getListWizard(Stage stage, String className)
    {
        ArrayList<Wizard> list = new ArrayList<Wizard>();

        Class theClass = null;
        try
        {  theClass = Class.forName(className);  }
        catch (Exception error)
        {  error.printStackTrace();  }

        for (Actor a : stage.getActors())
        {
            if ( theClass.isInstance( a ) )
                list.add( (Wizard)a );
        }
        
        return list;
    }
    
    // retrieves a list of all archer tower actors to reference their methods
    public static ArrayList<ArcherTower> getListArcherTower(Stage stage, String className)
    {
        ArrayList<ArcherTower> list = new ArrayList<ArcherTower>();

        Class theClass = null;
        try
        {  theClass = Class.forName(className);  }
        catch (Exception error)
        {  error.printStackTrace();  }

        for (Actor a : stage.getActors())
        {
            if ( theClass.isInstance( a ) )
                list.add( (ArcherTower)a );
        }
        
        return list;
    }
    
    // returns whether the wave has ended or not
    public static boolean waveCheck(Stage s)
    {
        boolean status = false; 
        
        ArrayList<Wizard> wizardList = new ArrayList<Wizard>();
        wizardList = getListWizard(s, "Wizard");
        //System.out.print(getListWizard(s, "Wizard").size());
      
        // checks to see if any wizards exist  
        if(getListWizard(s, "Wizard").size() != 0)
        {
            //status = false;  // wave isn't over
            
            for (Wizard WizardHandler : getListWizard(s,"Wizard"))
            {
                // wizard has special case as false - normal wizard
                if(WizardHandler.returnSpecialCase() == true)
                {
                    status = false; // wave isn't over
                    //System.out.print("wave isn't over\n");
                    return status;
                }
                
                else
                {
                    status = true; 
                }
            }
            
            //System.out.print("Still wizards on map\n");
            return status;
        }
        
        // otherwise it indicates that no more players are left on the map
        else
        {
            status = true;
            //System.out.print("No characters on the map\n");
            return status;
        }
    }
        
}
    
    


