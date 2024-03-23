package playerControllerManager;
import com.badlogic.gdx.Gdx;

import entityManager.Entity;
import entityManager.Player;
import entityManager.EntityManager;
import inputOutputManager.InputHandler;
import sceneManager.*;

/**
 * The `PlayerControllerManager` class manages the input and movement of player
 * entities in a game. It uses an `InputOutputManager` instance to get the input
 * from the keyboard and game controllers, and a `Direction` and `Jump` instance
 * to manage the movement and jumping behavior of the player entities. It also
 * checks if the player entities are on the ground, and resets their position if
 * they fall below the ground level.
 */

public class PlayerControllerManager{
	private InputHandler inputHandler;
	private EntityManager entities;
	private Movement playerMovement;
	private Jump playerJump;
	private SceneManager sceneManager;
	// private int screenWidth = Gdx.graphics.getWidth();
//	private final float GROUND_LEVEL = -25;
//	private boolean canJump = true;


	public PlayerControllerManager(EntityManager em, SceneManager sceneManager) {
		this.entities = em; // Initialize the entity variable
		this.inputHandler = new InputHandler(); // Initialize the variable to take in user input
		playerMovement = new Movement(this); // Initialize the movement variable
		playerJump = new Jump(this); // Initialize the jump variable
		this.sceneManager = sceneManager;
	}
	// to retrieve user input from methods
	public InputHandler getMovement() {
		return inputHandler;
	}

	public void setMovement(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	// to retrieve a list of entities from the entity managers
	public EntityManager getEntities() {
		return entities;
	}

	public void setEntities(EntityManager entities) {
		this.entities = entities;
	}
	// Resets the jump flag, allowing the player to jump again
//		public void resetJump() {
//			canJump = true;
//		}


	public void update(float deltaTime) {

		if (sceneManager == null) {
			Gdx.app.log("PlayerControllerManager", "SceneManager is null!");
			return;
		}

		BaseScene currentScene = sceneManager.getCurrentScene();
		if (currentScene instanceof EarthScene || currentScene instanceof EarthScene2) {
			// Update the player's velocity based on the input from the keyboard
			playerMovement.moveLeft();
			playerMovement.moveRight();
			playerJump.jump();
			playerJump.applyGravity();

		} else if (currentScene instanceof VenusScene || currentScene instanceof MercuryScene || currentScene instanceof AsteroidScene) {
			// Update the player's velocity based on the input from the keyboard
			playerMovement.moveLeft();
			playerMovement.moveRight();
			playerMovement.moveUp();
			playerMovement.moveDown();
		}
		for (Entity entity : getEntities().getEntityList()) {
			if (entity instanceof Player) {
				Player player = (Player) entity;
				player.updatePosition(deltaTime);
			}
		}
	}
		// Check if the player is on the ground
//		for (Entity entity : getEntities().getEntityList()) {
//			if (entity instanceof Player) {
//
//				Player player = (Player) entity;
//				player.updatePosition(deltaTime);
//				//player.setPosX(player.getPosX() + player.getVelocity().x * deltaTime);
//				//player.setPosY(player.getPosY() + player.getVelocity().y * deltaTime);
//
//				// Check if the entity is at the left edge of the screen
//				if (player.getPosX() <= 1) {
//					player.getVelocity().x = 0; // Stop movement
//					player.setPosX(0); // Reset position to the edge
//				}
//
//				// Check if the entity is at the top edge of the screen
//				if (player.getPosY() >= Gdx.graphics.getHeight() - player.getHeight()) {
//					player.getVelocity().y = 0; // Stop upward movement
//					player.setPosY(Gdx.graphics.getHeight() - player.getHeight()); // Reset position to the top edge
//				}
//
//				// Check if the entity is at the bottom edge of the screen
//				if (player.getPosY() <= 0) {
//					player.getVelocity().y = 0; // Stop downward movement
//					player.setPosY(0); // Reset position to the bottom edge
//				}
//
//				if (player.getVelocity().y < 0) {
//					//player.setPosY(GROUND_LEVEL);
//					// player.setVelocity(new Vector2(player.getVelocity().x, 0));
//					playerJump.resetJump();
//					//System.out.println("Player is on the ground!");
//				}
//
//				// Print the player's velocity
//				System.out.println("Player's velocity: " + player.getVelocity());
//
//			}
//
//		}
//
//	}
//
//	public void setEndPlayerPosition() {
//		for (Entity entity : getEntities().getEntityList()) {
//			if (entity instanceof Player) {
//				Player player = (Player) entity;
//				if (player.getPosX() >= screenWidth - player.getWidth()) {
//					player.getVelocity().x = 0; // Stop movement
//					player.setPosX(screenWidth - player.getWidth()); // Reset position to the edge
//				}
//			}
//		}
//
//	}

	public void dispose() {

	}

}
