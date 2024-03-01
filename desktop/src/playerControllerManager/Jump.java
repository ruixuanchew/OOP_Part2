package playerControllerManager;

import com.badlogic.gdx.Gdx;

import entityManager.Entity;

/**
 * The `Jump` class manages the jumping behavior of player entities in a game.
 * It uses a `PlayerControllerManager` instance to get the list of player
 * entities, and updates their velocity based on the input from the keyboard. It
 * also applies gravity to the player entities to make them fall after the jump
 * is executed.
 */

public class Jump {
	private PlayerControllerManager pcManager;
	private boolean canJump = true;
	private final float GRAVITY = -150f; // The acceleration due to gravity (in m/s^2)
	private static final float JUMP_VELOCITY = 180.01f; // JUMP_VELOCITY = sqrt(-2 * GRAVITY * JUMP_HEIGHT) = 160.01f

	public Jump(PlayerControllerManager jump) {
		this.pcManager = jump;
	}

	// Makes the player entities jump if the space key is pressed and they are
	// allowed to jump.
	public void jump() {
		for (Entity entity : pcManager.getEntities().getEntityList()) {
			if (entity.isPlayer()) {
				if (pcManager.getMovement().SpaceKey() && canJump) {
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
		for (Entity entity : pcManager.getEntities().getEntityList()) {
			if (entity.isPlayer()) {
				entity.getVelocity().y += GRAVITY * Gdx.graphics.getDeltaTime();
			}
		}
	}

	public void dispose() {
	}
}
