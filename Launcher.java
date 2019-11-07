import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;



public class Launcher
{
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