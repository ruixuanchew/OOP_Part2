package playerControllerManager;

import entityManager.Entity;

/**
 * The `Direction` class manages the movement of player entities in a game. It
 * uses a `PlayerControllerManager` instance to get the list of player entities,
 * and updates their velocity based on the input from the keyboard.
 */

public class Direction {

	private PlayerControllerManager pcManager;

	public Direction(PlayerControllerManager movement) {
		// super(em);
		this.pcManager = movement;

	}

	// Updates the velocity of the player entities based on the input from the
	// keyboard.
	public void move() {
		for (Entity entity : pcManager.getEntities().getEntityList()) {
			if (entity.isPlayer()) {
				if (pcManager.getMovement().LeftKey()) {

					entity.getVelocity().x = (-entity.getSpeed());

				} else /*
                 * else if (pcManager.getMovement().DownKey()) {
                 *
                 * entity.getVelocity().y = -entity.getSpeed();
                 *
                 * } else if (pcManager.getMovement().UpKey()) {
                 *
                 * entity.getVelocity().y = entity.getSpeed();
                 *
                 * }
                 */ if (pcManager.getMovement().RightKey()) entity.getVelocity().x = entity.getSpeed();
                else {

					entity.getVelocity().x = 0;
					// entity.getVelocity().y = 0;
				}
			}
		}
	}

	public void dispose() {

	}

}
