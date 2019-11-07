import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;



public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new MainGame();
        LwjglApplicationConfiguration cfg =  new LwjglApplicationConfiguration();
        //cfg.width =  LwjglApplicationConfiguration.getDesktopDisplayMode().width;
        //cfg.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
        cfg.height = 900;
        cfg.width = 1600;
        //cfg.fullscreen = true;
        cfg.title = "WIP";
        
       

        
        LwjglApplication launcher = new LwjglApplication (myGame, cfg);
    }
}