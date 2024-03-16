package sceneManager;

import com.badlogic.gdx.Game;

import entityManager.EntityFactory;
import entityManager.EntityManager;
import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import inputOutputManager.InputOutputManager;
import playerControllerManager.PlayerControllerManager;
import simulationLifecycleManager.SimulationLifecycleManager;

public class SceneManager {
    private Game game;
    private StartScene startScene;
    private EndScene endScene;
    private AsteroidScene asteroidScene;
    private MercuryScene mercuryScene;
    private VenusScene venusScene;
    private EarthScene earthScene;
    private BaseScene currentScene;
    private boolean sceneSwitching;
    private InputOutputManager ioManager;
    private UIManager uiManager;

    public SceneManager(Game game) {
        this.game = game;
        this.sceneSwitching = false;
    }

    public synchronized void initializeScenes(EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager, CollisionManager cManager, 
    		AIControlManager aiManager, SimulationLifecycleManager slManager, InputOutputManager ioManager) {
        this.ioManager = ioManager;
        startScene = new StartScene(this);
        endScene = new EndScene(this, slManager);
        asteroidScene = new AsteroidScene(this, entityManager, entityFactory, pcManager, cManager, aiManager);
        mercuryScene = new MercuryScene(this, entityManager, entityFactory, pcManager, cManager, aiManager);
        venusScene = new VenusScene(this, entityManager, entityFactory, pcManager, cManager, aiManager);
        earthScene = new EarthScene(this, entityManager, entityFactory, pcManager, cManager, aiManager);
    }

    public synchronized void showStartScene() {
        setCurrentScene(startScene);
        ioManager.getBgMusic().setVolume(0.2f);
    }

    public synchronized void showEndScene() {
        setCurrentScene(endScene);
    }

    public synchronized void showAsteroidScene() {
        setCurrentScene(asteroidScene);
        ioManager.changeBackgroundMusic("space.mp3");
        ioManager.getBgMusic().setVolume(0.2f);
    }

    public synchronized void showMercuryScene() {
        setCurrentScene(mercuryScene);
        ioManager.changeBackgroundMusic("perion.mp3");
        ioManager.getBgMusic().setVolume(0.5f);
    }

    public synchronized void showVenusScene() {
        setCurrentScene(venusScene);
        ioManager.changeBackgroundMusic("volcano.mp3");
        ioManager.getBgMusic().setVolume(0.5f);
    }

    public synchronized void showEarthScene() {
        setCurrentScene(earthScene);
        ioManager.changeBackgroundMusic("city.mp3");
        ioManager.getBgMusic().setVolume(0.08f);
    }

    public synchronized BaseScene getCurrentScene() {
        return currentScene;
    }

    private void setCurrentScene(BaseScene scene) {
        if (!sceneSwitching) {
            sceneSwitching = true;
            game.setScreen(scene);
            currentScene = scene;
            sceneSwitching = false;
        }
    }

    public synchronized void dispose() {
        startScene.dispose();
        endScene.dispose();
        asteroidScene.dispose();
        mercuryScene.dispose();
        venusScene.dispose();
        earthScene.dispose();
    }
}


