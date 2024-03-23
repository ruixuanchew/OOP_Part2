package inputOutputManager;

/**
 * InputOutputManager class is responsible for managing the input and output of the game. It
 * initializes the sound player and input handler.
 */

public class InputOutputManager {

    //private BackgroundMusic bgMusic;
    private InputHandler inputHandler;
    private SoundStrategy backgroundMusicStrategy;
    private SoundStrategy soundEffectStrategy;

    public InputOutputManager() {
        //bgMusic = new BackgroundMusic("Pixelland.mp3");
        this.backgroundMusicStrategy = new BackgroundMusicStrategy("Pixelland.mp3");
        this.soundEffectStrategy = new SoundEffectStrategy("oof.mp3");
        inputHandler = new InputHandler();
    }

    /* public SoundPlayer getBgMusic() {
        return bgMusic;
    }

    public void changeBackgroundMusic(String musicFile) {
        if (bgMusic != null) {
            bgMusic.changeMusic(musicFile);
        }
    } */

    public void playMusic() {
        backgroundMusicStrategy.playSound();
    }

    public void playSoundEffect() {
        soundEffectStrategy.playSound();
    }

    public void changeMusic(String musicFile) {
        backgroundMusicStrategy.changeMusic(musicFile);
    }

    public void setVolume(float volume) {
        backgroundMusicStrategy.setVolume(volume);
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }
}
