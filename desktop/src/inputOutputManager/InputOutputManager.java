package inputOutputManager;

/**
 * InputOutputManager class is responsible for managing the input and output of the game. It
 * initializes the SoundStrategy interface for game audio and InputHandler for user input and button events.
 */

public class InputOutputManager {
    private InputHandler inputHandler;
    private SoundStrategy backgroundMusicStrategy;
    private SoundStrategy soundEffectStrategy;

    public InputOutputManager() {
        this.backgroundMusicStrategy = new BackgroundMusicStrategy("Pixelland.mp3");
        this.soundEffectStrategy = new SoundEffectStrategy("oof.mp3");
        inputHandler = new InputHandler();
    }

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
