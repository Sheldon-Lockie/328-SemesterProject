
/**
 * Brandon Foss
 * Button Class - Will create a button implementation
 */

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class ButtonCreation
{
    //Create a button with up and down textures, if given a function, create a lambda expression for respective button
    public static void CreateButton(Stage stage, Texture t1, Texture t2, int x, int y,int SizeX,int SizeY, Function f)
    {
        TextureRegionDrawable tUp = new TextureRegionDrawable(new TextureRegion(t1));
        TextureRegionDrawable tDown = new TextureRegionDrawable(new TextureRegion(t2));
        
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = tUp;
        buttonStyle.down = tDown;
        
        ButtonStyle hoverStyle = new ButtonStyle();
        hoverStyle.up = tDown;
        hoverStyle.down = tUp;
        
        Button button = new Button(buttonStyle);
        button.setPosition(x, y);
        button.setSize(SizeX,SizeY);
        
        
        if(f!= null)
        {
            button.addListener(    
                (Event e) -> 
                {
                    try
                    {   
                        InputEvent ie = (InputEvent)e;
                        if ( ((InputEvent)e).getType().equals(Type.enter) )
                            button.setStyle(hoverStyle);
                        else if ( ((InputEvent)e).getType().equals(Type.exit) )
                            button.setStyle(buttonStyle);

                        if ( ((InputEvent)e).getType().equals(Type.touchDown) )
                            f.run();
                    }
                    catch(Exception _e)
                    {   
                        return false;
                    }

                    return false;
            });  
        }
        
        else
        {
            button.addListener(    
                (Event e) -> 
                {
                    try
                    {
                        InputEvent ie = (InputEvent)e;
                        if ( ((InputEvent)e).getType().equals(Type.enter) )
                            button.setStyle(hoverStyle);
                        else if ( ((InputEvent)e).getType().equals(Type.exit) )
                            button.setStyle(buttonStyle);
                    }
                    
                    catch(Exception _e)
                    {
                        return false;
                    }
                    
                    return false;
            });  
        }
        
        stage.addActor(button);
        return;
    }
}
