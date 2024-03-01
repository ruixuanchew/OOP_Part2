package sceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import entityManager.EntityManager;
import simulationLifecycleManager.SimulationLifecycleManager;

public class SceneManager {
	private Game game;
	private StartScene startScene;
	private GameScene gameScene;
	private EndScene endScene;
	private EntityManager entityManager;
	private SimulationLifecycleManager slManager;
	private boolean gameSceneActive = false;
	
	public SceneManager(Game game, EntityManager em) {
		this.game = game;
		this.entityManager = em;
		this.slManager = new SimulationLifecycleManager(this, entityManager);
		startScene = new StartScene(this);
		// Game scene accepts entity manager
		gameScene = new GameScene(this, entityManager);
		// End scene accepts simulation lifecycle manager
		endScene = new EndScene(this, slManager);
	}
	
	// sets screen for different scenes
	public void showStartScene() {
		game.setScreen(startScene);
		
	}
	public void showGameScene() {
		game.setScreen(gameScene);
		setGameSceneActive(true); // Change game scene boolean based on 
	}
	public void showEndScene() {
		game.setScreen(endScene);
		setGameSceneActive(false);
	}
    public void setGameSceneActive(boolean active) {
        gameSceneActive = active;
    }
    public boolean getGameSceneActive() {
        return gameSceneActive;
    }
	
	public void dispose() {
        startScene.dispose();
        gameScene.dispose();
        endScene.dispose();
    }

}
