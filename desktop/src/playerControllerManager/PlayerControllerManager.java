package playerControllerManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import entityManager.Entity;
import entityManager.EntityManager;
import inputOutputManager.InputHandler;

/**
 * The `PlayerControllerManager` class manages the input and movement of player
 * entities in a game. It uses an `InputOutputManager` instance to get the input
 * from the keyboard and game controllers, and a `Direction` and `Jump` instance
 * to manage the movement and jumping behavior of the player entities. It also
 * checks if the player entities are on the ground, and resets their position if
 * they fall below the ground level.
 */

public class PlayerControllerManager {
	private InputHandler inputHandler;
	private EntityManager entities;
	private Direction direction;
	private Jump jump;

	private final float GROUND_LEVEL = 150;
	private int screenWidth = Gdx.graphics.getWidth();

	public PlayerControllerManager(EntityManager em) {
		this.entities = em; // Initialize the entity variable
		this.inputHandler = new InputHandler(); // Initialize the variable to take in user input
		direction = new Direction(this);
		jump = new Jump(this);

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

	public void update(float deltaTime) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_CYAN = "\u001B[36m";
		final String ANSI_Green = "\u001B[32m";

		direction.move();
		jump.jump();
		jump.applyGravity();

		// Check if the player is on the ground
		for (Entity entity : getEntities().getEntityList()) {
			if (entity.isPlayer()) {
				entity.setPosX(entity.getPosX() + entity.getVelocity().x * deltaTime);
				entity.setPosY(entity.getPosY() + entity.getVelocity().y * deltaTime);
//				System.out.println(String.format(ANSI_CYAN + "Player is at %.2f,%.2f position" + ANSI_RESET,
//						entity.getPosX(), entity.getPosY()));

				// Check if the entity is at the left edge of the screen
				if (entity.getPosX() <= 1) {
					entity.getVelocity().x = 0; // Stop movement
					entity.setPosX(0); // Reset position to the edge
				}
				if (entity.getPosX() >= screenWidth - entity.getWidth()) {
					entity.getVelocity().x = 0; // Stop movement
				}

				if (entity.getPosY() <= GROUND_LEVEL) {
					entity.setPosY(GROUND_LEVEL);
					entity.setVelocity(new Vector2(entity.getVelocity().x, 0));
					jump.resetJump();
				}

			} else {
//				System.out.println(String.format(ANSI_Green + "Entity is at %.2f, %.2f position" + ANSI_RESET,
//						entity.getPosX(), entity.getPosY()));
			}

		}

	}

	public void dispose() {
		direction.dispose();
		jump.dispose();
	}

}
