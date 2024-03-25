package sceneManager;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Game;

import entityManager.Entity;
import entityManager.EntityFactory;
import entityManager.EntityManager;
import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import inputOutputManager.InputOutputManager;
import playerControllerManager.PlayerControllerManager;
import simulationLifecycleManager.SimulationLifecycleManager;
import simulationLifecycleManager.ErrorHandler;

public class SceneManager {
    private Game game;
    private SceneFactory sceneFactory;
    private BaseScene currentScene;
    private boolean sceneSwitching;
    private InputOutputManager ioManager;
    private SceneConfigurationHandler configHandler;
    private Entity player;
    private Map<SceneType, BaseScene> sceneMap;
    private ErrorHandler errorHandler;

    public SceneManager(Game game) {
        this.game = game;
        this.sceneFactory = new SceneFactory(this); // Declare scene factory
        this.sceneSwitching = false;
        this.errorHandler = new ErrorHandler();
    }

    public synchronized void initializeScenes(EntityManager entityManager, EntityFactory entityFactory,
            PlayerControllerManager pcManager, CollisionManager cManager, AIControlManager aiManager,
            SimulationLifecycleManager slManager, InputOutputManager ioManager) {
        this.ioManager = ioManager;
        // Initialize Scenes in Scene Factory 
        sceneFactory.initializeScenes(entityManager, entityFactory, pcManager, cManager, aiManager, slManager);
    }

    public synchronized BaseScene getCurrentScene() {
        return currentScene;
    }

    // set the current scene, get sceneType from ENUM 
    public synchronized void setCurrentScene(SceneType sceneType) {
    	try {
    		// call scene factory to create scene passing sceneType as value (enum value)
            currentScene = sceneFactory.createScene(sceneType);
            this.sceneMap = sceneFactory.getSceneList(); // Get scene list from scene factory
            // Get specific scenes individual configuration when scene switches
            this.configHandler = new SceneConfigurationHandler(this.sceneMap); 
            configHandler.handleSceneConfig(sceneType, ioManager);
            if (!sceneSwitching) { 
                sceneSwitching = true;
                game.setScreen(currentScene); // Same game scene to current scene
                sceneSwitching = false;
            }
        } catch (Exception e) {
            // Handle the exception using ErrorHandler
            errorHandler.handleException(e, "Error setting current scene: ");
        }
    }

    public synchronized void switchToScene(SceneType sceneType) {
        setCurrentScene(sceneType);
    }
    
    // reset the scene when player restarts the game
    public synchronized void resetScene() {
    	for (BaseScene scene : sceneMap.values()) {
                scene.resetScene(); 
        }
    }
    
    public synchronized void dispose() {
        if (currentScene != null) {
            currentScene.dispose();
        }
    }
}
