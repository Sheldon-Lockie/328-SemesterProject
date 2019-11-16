/**
 * This class will manage a lot of settings that we
 * want to act as global variables for other classes 
 * to utilize.
 */

// Imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameManager
{
    private static Music menuSong; // menu song
    private static Music levelSong1; // first song for game level
    private static float songVolume; // volume level for music
    private static float soundEffectVolume; // volume level for sound effects
    private static int difficulty; // level of difficulty a player will face
    private static int heroAttack; // whether the hero will auto attack or not
    private static int towerAttack; // which enemies a tower will target
    
    /**
     * Constructor for objects of class GameManager
     */
    public GameManager()
    {
        menuSong = Gdx.audio.newMusic(Gdx.files.internal(
            "Assets/Sounds/Songs/MainMenuTheme-Trimmed.mp3")); // The menu song
        levelSong1 = Gdx.audio.newMusic(Gdx.files.internal(
            "Assets/Sounds/Songs/MainMenuTheme-Trimmed.mp3")); // the first level song
        songVolume = 0.30f; // song volume is set to 50%
        soundEffectVolume = 0.30f; // volume is set to 50%
        difficulty = 2; // difficulty is challenging
        heroAttack = 1; // attack mechanism is manual    
        towerAttack = 1; // tower will target furthest enemey
    }
    
    /*
     * The following methods alter music
     */
    
    // starts menu music and sets it to loop
    public static void playMenuMusic()
    {
        menuSong.setVolume(songVolume);
        menuSong.play();
        menuSong.setLooping(true);
    }
    
    // pauses the menu music
    public static void pauseMenuMusic()
    {
        menuSong.pause();
    }
    
    // stop the menu music
    public static void stopMenuMusic()
    {
        menuSong.stop();
    }
    
    // starts level music and sets it to loop
    public static void playLevelMusic()
    {
        levelSong1.setVolume(songVolume);
        levelSong1.play();
        levelSong1.setLooping(true);
    }
    
    // pause the level music
    public static void pauseLevelMusic()
    {
        levelSong1.pause();
    }
    
    // stop the level music
    public static void stopLevelMusic()
    {
        levelSong1.stop();
    }
    
    // set the music volume level
    public static void setMusicVolume(float volume)
    {
        if((volume >= 0.0) && (volume <= 1.0))
        {
            songVolume = volume;
            menuSong.setVolume(songVolume);
            levelSong1.setVolume(songVolume);
        }
        
        else
        {
            System.out.print("Invalid volume value: " + volume + "\n");
        }
    }
    
    // set the sound effect volume level
    public static void setSoundEffectVolume(float volume)
    {
        if((volume >= 0.0) && (volume <= 1.0))
        {
            soundEffectVolume = volume;
        }
        
        else
        {
            System.out.print("Invalid volume value: " + volume + "\n");
        }
    }
    
    // retrieve music volume level
    public static float retrieveMusicVolume()
    {
        return songVolume;
    }
    
    // retrieve sound effect volume level
    public static float retrieveSoundEffectVolume()
    {
        return soundEffectVolume;
    }
    
    // check to see if any songs are playing
    public static boolean checkSongs()
    {
        // if any song is playing return true
        if((menuSong.isPlaying()) || (levelSong1.isPlaying()))
        {
            return true;
        }
        
        else
        {
            return false;
        }
    }
    
    // stops the playing of all songs
    public static void endAllSongs()
    {
        menuSong.stop();
        levelSong1.stop();
    }
    
    /*
     * The following methods alter other settings
     */
    
    // set difficulty level
    // 0 sets the difficulty to easy
    // 1 sets the difficulty to challenging
    // 2 sets the difficulty to impossible
    public static void setDifficulty(int difficultyLevel)
    {
        if((difficultyLevel >= 0) && (difficultyLevel <= 3))
        {
            difficulty = difficultyLevel;
        }
        
        else
        {
            System.out.print("difficult level invalid: " + difficultyLevel + "\n");
        }
    }
    
    // set hero attack preferences
    // 0 sets the hero to attack manually with space bar
    // 1 sets the hero to attacka automatically when within range
    public static void setHeroAttack(int heroPreference)
    {
        if((heroPreference >= 0) && (heroPreference <= 1))
        {
            heroAttack = heroPreference;
        }
        
        else
        {
            System.out.print("Hero attack level invalid: " + heroPreference + "\n");
        }
    } 
    
    // set tower attack preferences
    // 0 sets towers to attack the furthest enemy
    // 1 sets towers to attack the strongest enemy
    public static void setTowerAttack(int towerPreference)
    {
        if((towerPreference >= 0) && (towerPreference <= 1))
        {
            towerAttack = towerPreference;
        }
        
        else
        {
            System.out.print("tower attack level invalid: " + towerPreference + "\n");
        }
    } 
    
    // retrieve difficulty level
    public static int retrieveDifficulty()
    {
        return difficulty;
    }
    
    // retrieve hero attack preference
    public static int retrieveHeroPreference()
    {
        return heroAttack;
    }
    
    // retrieve tower attack preference
    public static int retrieveTowerPreference()
    {
        return towerAttack;
    }
}
