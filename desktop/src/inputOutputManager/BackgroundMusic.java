package inputOutputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * BackgroundMusic is used to play the background music of the game.
 */

public class BackgroundMusic implements SoundPlayer {
    private Music bgMusic;

    public BackgroundMusic() {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Pixelland.mp3"));
        bgMusic.setLooping(true);
    }

    @Override
    public void play() {
        bgMusic.play();
    }

    @Override
    public void stop() {
        bgMusic.stop();
    }

    @Override
    public void setVolume(float volume) {
        bgMusic.setVolume(volume);
    }
}