import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

// This class will begin the game
public class Launcher
{
    // This will create an instance of the game
    public static void main (String[] args)
    {
        Game myGame = new MainGame();
        LwjglApplicationConfiguration cfg =  new LwjglApplicationConfiguration();
        cfg.height = 900;
        cfg.width = 1600;
        cfg.title = "Final Stand";
                
        LwjglApplication launcher = new LwjglApplication (myGame, cfg);
    }
}