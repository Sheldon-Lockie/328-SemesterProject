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
}
    
    


