package inputOutputManager;

/**
 * InputOutputManager class is responsible for managing the input and output of the game. It
 * initializes the sound player and input handler.
 */

public class InputOutputManager {

    private BackgroundMusic bgMusic;
    private InputHandler inputHandler;

    public InputOutputManager() {
        bgMusic = new BackgroundMusic("Pixelland.mp3");
        inputHandler = new InputHandler();
    }

    public SoundPlayer getBgMusic() {
        return bgMusic;
    }

    public void changeBackgroundMusic(String musicFile) {
        if (bgMusic != null) {
            bgMusic.changeMusic(musicFile);
        }
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }
}
