package inputOutputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusicStrategy implements SoundStrategy {
    private Music bgMusic;

    public BackgroundMusicStrategy(String musicFile) {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal(musicFile));
        bgMusic.setLooping(true);
    }

    @Override
    public void playSound() {
        if (!bgMusic.isPlaying()) {
            bgMusic.play();
        }
    }

    @Override
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
    public void setVolume(float volume) {
        bgMusic.setVolume(volume);
    }
}