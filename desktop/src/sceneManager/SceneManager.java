package sceneManager;

import java.util.List;

import com.badlogic.gdx.Game;

import entityManager.Entity;
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
    private Entity player;

    public SceneManager(Game game) {
        this.game = game;
        this.sceneSwitching = false;
    }

    public synchronized void initializeScenes(EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager, CollisionManager cManager, 
    		AIControlManager aiManager, SimulationLifecycleManager slManager, InputOutputManager ioManager) {
        this.ioManager = ioManager;
        startScene = new StartScene(this);
        earthScene = new EarthScene(this, entityManager, entityFactory, pcManager, cManager, aiManager);
        asteroidScene = new AsteroidScene(this, entityManager, entityFactory, pcManager, cManager, aiManager);
        mercuryScene = new MercuryScene(this, entityManager, entityFactory, pcManager, cManager, aiManager);
        venusScene = new VenusScene(this, entityManager, entityFactory, pcManager, cManager, aiManager);
        endScene = new EndScene(this, slManager);
    }

    public synchronized void showStartScene() {
        setCurrentScene(startScene);
        ioManager.setVolume(0.2f);
    }

    public synchronized void showEndScene() {
        setCurrentScene(endScene);
        ioManager.changeMusic("death.mp3");
        ioManager.setVolume(0.5f);
    }

    public synchronized void showAsteroidScene() {
        setCurrentScene(asteroidScene);
        ioManager.changeMusic("space.mp3");
        ioManager.setVolume(0.2f);
    }

    public synchronized void showMercuryScene() {
        setCurrentScene(mercuryScene);
        ioManager.changeMusic("perion.mp3");
        ioManager.setVolume(0.5f);
    }

    public synchronized void showVenusScene() {
        setCurrentScene(venusScene);
        ioManager.changeMusic("volcano.mp3");
        ioManager.setVolume(0.2f);
    }

    public synchronized void showEarthScene() {
        setCurrentScene(earthScene);
        earthScene.loadFirstMap();
        ioManager.changeMusic("city.mp3");
        ioManager.setVolume(0.08f);
     // Reset the screenSwitchCounter to 0 for all instances of BasePlanetScene
        for (BaseScene scene : List.of(earthScene, asteroidScene, mercuryScene, venusScene)) {
            if (scene instanceof BasePlanetScene) {
                BasePlanetScene planetScene = (BasePlanetScene) scene;
                planetScene.setScreenSwitchCounter(0);
            }
        }
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

    // make Player a global variable that all scenes can access
    public void setPlayer(Entity player) {
        this.player = player;
    }

    public Entity getPlayer() {
        return this.player;
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


