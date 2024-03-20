package simulationLifecycleManager;

import com.badlogic.gdx.Gdx;
import sceneManager.SceneManager;
import entityManager.EntityManager;

/**
 * SimulationLifecycleManager is used to manage the lifecycle of the game. It provides methods to
 * start the game, end the game, and restart the game. It also contains an ErrorHandler to handle
 * exceptions that occur when the game is started.
 */
public class SimulationLifecycleManager {
    private ErrorHandler errorHandler;
    private SceneManager sceneManager;
    private EntityManager entityManager;

    public SimulationLifecycleManager(SceneManager sceneManager, EntityManager entityManager) {
        this.sceneManager = sceneManager;
        this.entityManager = entityManager;
        errorHandler = new ErrorHandler();
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void startGame() {
        try {
            sceneManager.showStartScene(); // show start scene when startGame method is called
        } catch (Exception e) { // error handling
            errorHandler.handleException(e, "Failed to start the game");
            Gdx.app.exit(); // Exit the game if an error occurs
        }
    }

    public void endGame() {
        Gdx.app.exit(); // exit game when endGame method is called
    }

    public void restartGame() {
        entityManager.resetEntities(); // reset entities to original position
        sceneManager.showEarthScene(); // revert back to game scene when restartGame is called
    }
}