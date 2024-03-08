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

	private final float GROUND_LEVEL = 150;
	private int screenWidth = Gdx.graphics.getWidth();

	private boolean canJump = true;
	private final float GRAVITY = -150f; // The acceleration due to gravity (in m/s^2)
	private static final float JUMP_VELOCITY = 180.01f; // JUMP_VELOCITY = sqrt(-2 * GRAVITY * JUMP_HEIGHT) = 160.01f

	public PlayerControllerManager(EntityManager em) {
		this.entities = em; // Initialize the entity variable
		this.inputHandler = new InputHandler(); // Initialize the variable to take in user input
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
	// Updates the velocity of the player entities based on the input from the
	// keyboard.
	public void move() {
		for (Entity entity : getEntities().getEntityList()) {
			if (entity.isPlayer()) {
				if (getMovement().LeftKey()) {

					entity.getVelocity().x = (-entity.getSpeed());

				} else if (getMovement().DownKey()) {

					entity.getVelocity().y = -entity.getSpeed();

				} else if (getMovement().UpKey()) {

					entity.getVelocity().y = entity.getSpeed();

				} else if (getMovement().RightKey()) {
					entity.getVelocity().x = entity.getSpeed();
				} else {

					entity.getVelocity().x = 0;
					// entity.getVelocity().y = 0;
				}
			}
		}
	}

	// Makes the player entities jump if the space key is pressed and they are
	// allowed to jump.
	public void jump() {
		for (Entity entity : getEntities().getEntityList()) {
			if (entity.isPlayer()) {
				if (getMovement().SpaceKey() && canJump) {
					entity.getVelocity().y += JUMP_VELOCITY; // adjust JUMP VELOCITY
					canJump = false;
				}
			}

		}
	}

	// Resets the jump flag, allowing the player to jump again
	public void resetJump() {
		canJump = true;
	}

	// Applies gravity to the player entities, making them fall when they are not
	// jumping.
	public void applyGravity() {
		for (Entity entity : getEntities().getEntityList()) {
			if (entity.isPlayer()) {
				entity.getVelocity().y += GRAVITY * Gdx.graphics.getDeltaTime();
			}
		}
	}



	public void update(float deltaTime) {
		move();
		jump();
		applyGravity();

		// Check if the player is on the ground
		for (Entity entity : getEntities().getEntityList()) {
			if (entity.isPlayer()) {
				entity.setPosX(entity.getPosX() + entity.getVelocity().x * deltaTime);
				entity.setPosY(entity.getPosY() + entity.getVelocity().y * deltaTime);

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
					resetJump();
				}

			}

		}

	}

	public void dispose() {
}

}
