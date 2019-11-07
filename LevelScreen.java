import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;




public class LevelScreen extends BaseScreen
{
    public void initialize()
    {
       Pixmap pm = new Pixmap(Gdx.files.internal("Assets/Img/Cursors/CursorSword.png"));
       Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
       pm.dispose();
    }
 
    public void update (float dt)
    {
     
    }
    
    
}
