// This class will create the game and set the active screen (menu)
public class MainGame extends BaseGame
{
    public void create()
    {
        super.create();
        setActiveScreen( new MenuScreen());
    }
    
}