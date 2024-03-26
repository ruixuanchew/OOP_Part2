package inputOutputManager;

/**
 * The SoundStrategy interface defines the methods that must be implemented by any class that wants to provide
 * sound strategies. This is part of the Strategy design pattern.
 */

public interface SoundStrategy {
    void playSound();
    void changeMusic(String musicFile);
    void setVolume(float volume);
}
