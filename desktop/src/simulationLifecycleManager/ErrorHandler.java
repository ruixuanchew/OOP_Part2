package simulationLifecycleManager;

import com.badlogic.gdx.Gdx;

/**
 * ErrorHandler is used to handle exceptions that occur when the game is started. It will
 * print a custom message and the error message to the console, and then exit the game.
 */
public class ErrorHandler {

    public void handleException(Exception e, String customMessage) {
        System.err.println(customMessage);
        System.err.println("An error occurred: " + e.getMessage());
        Gdx.app.exit(); // Exit the game if an error occurs
    }
}