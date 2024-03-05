package inputOutputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * BackgroundMusic is used to play the background music of the game.
 */

public class BackgroundMusic implements SoundPlayer {
    private Music bgMusic;

    public BackgroundMusic(String musicFile) {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal(musicFile));
        bgMusic.setLooping(true);
    }

    public void changeMusic(String musicFile) {
        if (bgMusic.isPlaying()) {
            bgMusic.stop();
        }
        bgMusic.dispose();
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal(musicFile));
        bgMusic.setLooping(true);
        bgMusic.play();
    }

    @Override
    public void play() {
        if (!bgMusic.isPlaying()) {
            bgMusic.play();
        }
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