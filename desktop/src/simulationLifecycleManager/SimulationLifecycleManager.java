package simulationLifecycleManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import entityManager.Entity;
import entityManager.Player;
import sceneManager.SceneManager;
import sceneManager.SceneType;
import entityManager.EntityManager;
import collisionManager.CollisionObserver;

/**
 * SimulationLifecycleManager is used to manage the lifecycle of the game. It provides methods to
 * start the game, end the game, and restart the game. It also contains an ErrorHandler to handle
 * exceptions that occur when the game is started.
 */
public class SimulationLifecycleManager implements CollisionObserver{
    private ErrorHandler errorHandler;
    private SceneManager sceneManager;
    private EntityManager entityManager;
    private Preferences preferences;

    public SimulationLifecycleManager(SceneManager sceneManager, EntityManager entityManager) {
        this.sceneManager = sceneManager;
        this.entityManager = entityManager;
        errorHandler = new ErrorHandler();
        preferences = Gdx.app.getPreferences("GamePreferences");
        
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void startGame() {
        try {
        	sceneManager.switchToScene(SceneType.START_SCENE); // show start scene when startGame method is called
        } catch (Exception e) { // error handling
            errorHandler.handleException(e, "Failed to start the game");
            Gdx.app.exit(); // Exit the game if an error occurs
        }
    }

    public void endGame() {
        Gdx.app.exit(); // exit game when endGame method is called
    }
    
    // Save game state whenever a scene switch happens
    public void saveGameState() {
    	Entity player = sceneManager.getPlayer();

        if (player instanceof Player) {
            Player playerEntity = (Player) player;
            preferences.putInteger("PlayerHealth", playerEntity.getHealth());
        }
        preferences.flush(); // Write preferences to disk
    }

    public void restartGame() {
        Entity player = sceneManager.getPlayer();

        if (player instanceof Player) {
            Player playerEntity = (Player) player;
            // Set the player's health to full when the game is restarted
            playerEntity.setHealth(100);
            player.setPosX(0);
            player.setPosY(0);
        }
        entityManager.resetEntities(); // reset entities to original position
        sceneManager.setCurrentScene(SceneType.EARTH_SCENE); // revert back to game scene when restartGame is called
    }

	@Override
	public void onCollisionOccurred() {
		saveGameState();
		
	}
}