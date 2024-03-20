package inputOutputManager;

public interface SoundStrategy {
    void playSound();
    void changeMusic(String musicFile);
    void setVolume(float volume);
}
