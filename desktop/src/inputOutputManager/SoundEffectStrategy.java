package inputOutputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffectStrategy implements SoundStrategy {
    private Sound soundEffect;

    public SoundEffectStrategy(String soundFile) {
        soundEffect = Gdx.audio.newSound(Gdx.files.internal(soundFile));
    }

    @Override
    public void playSound() {
        soundEffect.play();
    }

    @Override
    public void changeMusic(String musicFile) {
    }

    @Override
    public void setVolume(float volume) {
    }
}