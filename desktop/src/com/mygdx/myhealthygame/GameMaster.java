package com.mygdx.myhealthygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.EntityManager;
import entityManager.TextureObject;
import inputOutputManager.InputOutputManager;
import playerControllerManager.Direction;
import playerControllerManager.Jump;
import playerControllerManager.PlayerControllerManager;
import sceneManager.SceneManager;
import simulationLifecycleManager.SimulationLifecycleManager;

public class GameMaster extends Game {
	private InputOutputManager ioManager;
	private SceneManager sceneManager;
	private Direction movement;
	private Jump jump;
	private PlayerControllerManager pcManager;
	private EntityManager em;
	private SimulationLifecycleManager slManager;
	private AIControlManager aiManager;

	private CollisionManager cManager;
	private TextureObject player;

	@Override
	public void create() {
		// Initialize the InputOutputManager and Error Handler
		ioManager = new InputOutputManager();

		// Initialize the EntityManager
		em = new EntityManager();

		// add the players into an array
		player = new TextureObject("bucket.png", 150, 150, 200, true, new Vector2(0, 0));
		em.add(player);

		// add the entities into an array
		TextureObject entity = new TextureObject("droplet.png", 150, 150, 4, false);
		em.add(entity);

		// add entities to collidable list
		em.addCollidableEntity(entity);
		cManager = new CollisionManager();

		// Initialize the PlayerControllerManager, Jump,
		pcManager = new PlayerControllerManager(em);
		movement = new Direction(pcManager);
		jump = new Jump(pcManager);

		// Initialize SceneManager with game and entities
		sceneManager = new SceneManager(this, em);

		// Initialize SimulationLifecycleManager with SceneManager
		slManager = new SimulationLifecycleManager(sceneManager, em);

		// Initialize AIControlManager
		aiManager = new AIControlManager(em);

		// Start with start scene
		try {
			slManager.startGame();
			// throw new Exception("Test exception"); // testing error handling
		} catch (Exception e) {
			slManager.getErrorHandler().handleException(e, "Failed to start the game");
		}

	}

	@Override
	public void render() {
		super.render();
		ioManager.getBgMusic().play();
		ioManager.getBgMusic().setVolume(0.2f);
		// Check if gameScene is active then call game logic
		if (sceneManager.getGameSceneActive()) {
			for (TextureObject e : em.getCollidableEntityList()) {
				cManager.checkCollision(player, e);
			}
			pcManager.update(Gdx.graphics.getDeltaTime());
			em.moveAIControlled(aiManager);
		}
	}

	@Override
	public void dispose() {
		sceneManager.dispose();
		pcManager.dispose();
	}

}