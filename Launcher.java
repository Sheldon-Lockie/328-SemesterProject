import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

// This class create an instance of the game
public class Launcher
{
    private static Game myGame;
    public static void main (String[] args)
    {
        myGame = new MainGame();
        LwjglApplicationConfiguration cfg =  new LwjglApplicationConfiguration();
        cfg.height = 900;
        cfg.width = 1600;
        cfg.title = "Final Stand";      
        LwjglApplication launcher = new LwjglApplication (myGame, cfg);
    }
}