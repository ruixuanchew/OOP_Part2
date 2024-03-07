package sceneManager;

import com.badlogic.gdx.Game;

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
    private MarsScene marsScene;
    private JupiterScene jupiterScene;
    private SaturnScene saturnScene;
    private UranusScene uranusScene;
    private NeptuneScene neptuneScene;
    private BaseScene currentScene;
    private boolean sceneSwitching;
    private InputOutputManager ioManager;

    public SceneManager(Game game) {
        this.game = game;
        this.sceneSwitching = false;
    }

    public synchronized void initializeScenes(EntityManager entityManager, PlayerControllerManager pcManager, CollisionManager cManager, AIControlManager aiManager, SimulationLifecycleManager slManager, InputOutputManager ioManager) {
        this.ioManager = ioManager;
        startScene = new StartScene(this);
        endScene = new EndScene(this, slManager);
        asteroidScene = new AsteroidScene(this, entityManager, pcManager, cManager, aiManager);
        mercuryScene = new MercuryScene(this, entityManager, pcManager, cManager, aiManager);
        venusScene = new VenusScene(this, entityManager, pcManager, cManager, aiManager);
        earthScene = new EarthScene(this, entityManager, pcManager, cManager, aiManager);
        marsScene = new MarsScene(this, entityManager, pcManager, cManager, aiManager);
        jupiterScene = new JupiterScene(this, entityManager, pcManager, cManager, aiManager);
        saturnScene = new SaturnScene(this, entityManager, pcManager, cManager, aiManager);
        uranusScene = new UranusScene(this, entityManager, pcManager, cManager, aiManager);
        neptuneScene = new NeptuneScene(this, entityManager, pcManager, cManager, aiManager);
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
    }

    public synchronized void showVenusScene() {
        setCurrentScene(venusScene);
        ioManager.changeBackgroundMusic("Pixelland.mp3");
        ioManager.getBgMusic().setVolume(0.2f);
    }

    public synchronized void showEarthScene() {
        setCurrentScene(earthScene);
        ioManager.changeBackgroundMusic("city.mp3");
        ioManager.getBgMusic().setVolume(0.08f);
    }
    public synchronized void showMarsScene() {
        setCurrentScene(marsScene);
    }
    public synchronized void showJupiterScene() {
        setCurrentScene(jupiterScene);
    }
    public synchronized void showSaturnScene() {
        setCurrentScene(saturnScene);
    }

	public synchronized void showUranusScene() {
        setCurrentScene(uranusScene);
    }
    public synchronized void showNeptuneScene() {
        setCurrentScene(neptuneScene);
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
        marsScene.dispose();
        jupiterScene.dispose();
        saturnScene.dispose();
        uranusScene.dispose();
        neptuneScene.dispose();
    }
}


