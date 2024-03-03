package sceneManager;

import com.badlogic.gdx.Game;

import entityManager.EntityManager;
import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
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

    public SceneManager(Game game) {
        this.game = game;
        this.sceneSwitching = false;
    }

    public synchronized void initializeScenes(EntityManager entityManager, PlayerControllerManager pcManager, CollisionManager cManager, AIControlManager aiManager, SimulationLifecycleManager slManager) {
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
    }

    public synchronized void showEndScene() {
        setCurrentScene(endScene);
    }

    public synchronized void showAsteroidScene() {
        setCurrentScene(asteroidScene);
    }

    public synchronized void showMercuryScene() {
        setCurrentScene(mercuryScene);
    }

    public synchronized void showVenusScene() {
        setCurrentScene(venusScene);
    }

    public synchronized void showEarthScene() {
        setCurrentScene(earthScene);
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


