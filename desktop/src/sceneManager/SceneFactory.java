package sceneManager;

import java.util.HashMap;
import java.util.Map;

import entityManager.EntityFactory;
import entityManager.EntityManager;
import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import playerControllerManager.PlayerControllerManager;
import simulationLifecycleManager.ErrorHandler;
import simulationLifecycleManager.SimulationLifecycleManager;
import simulationLifecycleManager.ErrorHandler;

public class SceneFactory {
    private Map<SceneType, BaseScene> sceneMap; // Declare scene map which stores scenes in list 
    private SceneManager sceneManager;
    private ErrorHandler errorHandler;

    public SceneFactory(SceneManager sceneManager) {
        this.sceneMap = new HashMap<>();
        this.sceneManager = sceneManager;
        this.errorHandler = new ErrorHandler();
    }
    
    public void initializeScenes(EntityManager entityManager, EntityFactory entityFactory,
            PlayerControllerManager pcManager, CollisionManager cManager, AIControlManager aiManager,
            SimulationLifecycleManager slManager) {
    	 	try {
    	        // Initialize scenes here
    	 		// SceneType must all be in SceneType Enum file
    	        sceneMap.put(SceneType.START_SCENE, new StartScene(sceneManager));
    	        sceneMap.put(SceneType.INSTRUCTION_SCENE, new InstructionScene(sceneManager));
    	        sceneMap.put(SceneType.EARTH_SCENE, new EarthScene(sceneManager, entityManager, entityFactory, pcManager, cManager,
    	                aiManager));
    	        sceneMap.put(SceneType.EARTH_SCENE2, new EarthScene2(sceneManager, entityManager, entityFactory, pcManager, cManager,
    	                aiManager));
    	        sceneMap.put(SceneType.SPACE_SCENE, new AsteroidScene(sceneManager, entityManager, entityFactory, pcManager, cManager,
    	                aiManager));
    	        sceneMap.put(SceneType.MERCURY_SCENE, new MercuryScene(sceneManager, entityManager, entityFactory, pcManager, cManager,
    	                aiManager));
    	        sceneMap.put(SceneType.VENUS_SCENE, new VenusScene(sceneManager, entityManager, entityFactory, pcManager, cManager,
    	                aiManager));
    	        sceneMap.put(SceneType.END_SCENE, new EndScene(sceneManager, slManager));
    	    } catch (Exception e) {
    	        // Handle scene initialization errors
    	        errorHandler.handleException(e, "Error initializing scenes: " + e.getMessage());
    	    }
    }

    public BaseScene createScene(SceneType sceneType) {
        return sceneMap.get(sceneType);
    }
    // Get the scene list
    public Map<SceneType, BaseScene> getSceneList() {
    	return sceneMap;
    }
    public void disposeScene(SceneType sceneType) {
        BaseScene scene = sceneMap.get(sceneType);
        if (scene != null) {
            scene.dispose();
            sceneMap.remove(sceneType);
        }
    }
}
