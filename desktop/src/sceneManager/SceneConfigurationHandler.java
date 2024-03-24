package sceneManager;

import java.util.Map;

import inputOutputManager.InputOutputManager;

public class SceneConfigurationHandler{
	private Map<SceneType, BaseScene> sceneMap;
	
    public SceneConfigurationHandler(Map<SceneType, BaseScene> sceneMap) {
    	this.sceneMap = sceneMap;
    }

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

