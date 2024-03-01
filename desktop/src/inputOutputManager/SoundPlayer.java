package inputOutputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * SoundPlayer is used for output of the game's music and sound effects. It provides methods to play,
 * stop and adjust volume of the game's sounds.
 */

public interface SoundPlayer {

    void play();
    void stop();
    void setVolume(float volume);
}