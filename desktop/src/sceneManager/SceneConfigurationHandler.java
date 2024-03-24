package sceneManager;

import java.util.Map;

import inputOutputManager.InputOutputManager;

public class SceneConfigurationHandler{
	private Map<SceneType, BaseScene> sceneMap;
	
	// Take map of scenes as values
    public SceneConfigurationHandler(Map<SceneType, BaseScene> sceneMap) {
    	this.sceneMap = sceneMap;
    }
    
    // Handle different cases for different scenes
	public void handleSceneConfig(SceneType sceneType, InputOutputManager ioManager) {
        switch (sceneType) {
            case EARTH_SCENE:
                handleEarthSceneConfig(ioManager);
                break;
            case EARTH_SCENE2:
            	handleEarthScene2Config(ioManager);
                break;
            case SPACE_SCENE:
                handleAsteroidSceneConfig(ioManager);
                break;
            case END_SCENE:
                handleEndSceneConfig(ioManager);
                break;
            case START_SCENE:
                handleStartSceneConfig(ioManager);
                break;
            case MERCURY_SCENE:
                handleMercurySceneConfig(ioManager);
                break;
            case VENUS_SCENE:
                handleVenusSceneConfig(ioManager);
                break;
            case INSTRUCTION_SCENE:
                handleInstructionSceneConfig(ioManager);
                break;
            default:
                // No specific configuration for other scene types
                break;
        }
    }
	// Different methods for different configurations for different scenes
    private void handleStartSceneConfig(InputOutputManager ioManager) {
        ioManager.setVolume(0.2f);
    }

    private void handleInstructionSceneConfig(InputOutputManager ioManager) {
        ioManager.setVolume(0.2f);
    }

    private void handleEndSceneConfig(InputOutputManager ioManager) {
        ioManager.changeMusic("death.mp3");
        ioManager.setVolume(0.5f);
    }

    private void handleAsteroidSceneConfig(InputOutputManager ioManager) {
        ioManager.changeMusic("space.mp3");
        ioManager.setVolume(0.2f);
    }

    private void handleMercurySceneConfig(InputOutputManager ioManager) {
        ioManager.changeMusic("perion.mp3");
        ioManager.setVolume(0.5f);
    }

    private void handleVenusSceneConfig(InputOutputManager ioManager) {
        ioManager.changeMusic("volcano.mp3");
        ioManager.setVolume(0.2f);
    }

    private void handleEarthSceneConfig(InputOutputManager ioManager) {
        ioManager.changeMusic("city.mp3");
        ioManager.setVolume(0.08f);
        // For loop through scene map values to set all scenes screen switch counter to 0
        for (BaseScene scene : sceneMap.values()) {
            if (scene instanceof BasePlanetScene) {
                BasePlanetScene planetScene = (BasePlanetScene) scene;
                planetScene.setScreenSwitchCounter(0);
            }
        }
    }
    private void handleEarthScene2Config(InputOutputManager ioManager) {
        ioManager.changeMusic("night.mp3");
        ioManager.setVolume(0.3f);
    }
}

