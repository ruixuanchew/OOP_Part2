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
    private GameScene gameScene;
    private EndScene endScene;

    public SceneManager(Game game) {
        this.game = game;
    }
    
    // Added aggregation
    public void initializeScenes(EntityManager entityManager, PlayerControllerManager pcManager, CollisionManager cManager, AIControlManager aiManager, SimulationLifecycleManager slManager) {
        startScene = new StartScene(this);
        gameScene = new GameScene(this, entityManager, pcManager, cManager, aiManager);
        endScene = new EndScene(this, slManager);
    }

    public void showStartScene() {
        game.setScreen(startScene);
    }

    public void showEndScene() {
        game.setScreen(endScene);
    }

    public void showGameScene() {
        game.setScreen(gameScene);
    }

    public void dispose() {
        startScene.dispose();
        gameScene.dispose();
        endScene.dispose();
    }
}
