package playerControllerManager;

import com.badlogic.gdx.Gdx;
import entityManager.Entity;
import entityManager.Player;

public class Jump implements PlayerBehavior{
    private PlayerControllerManager playerControllerManager;

    private final float GRAVITY = -150f; // The acceleration due to gravity (in m/s^2)
    private static final float JUMP_VELOCITY = 180.01f; // JUMP_VELOCITY = sqrt(-2 * GRAVITY * JUMP_HEIGHT) = 160.01f
    private boolean canJump = true;

    public Jump(PlayerControllerManager playerControllerManager) {
        this.playerControllerManager = playerControllerManager;
    }

    // Updates the velocity of the player entities based on the input from the
    // keyboard.

    // Abstract methods from PlayerBehavior
    @Override
    public void moveLeft() {

    }
    @Override
    public void moveRight(){

    }
    @Override
    public void moveUp(){


    }
    @Override
    public void moveDown(){


    }
    @Override
    // Makes the player entities jump if the space key is pressed and they are
    // allowed to jump.
    public void jump() {
        for (Entity entity : playerControllerManager.getEntities().getEntityList()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (playerControllerManager.getMovement().SpaceKey() && canJump) {
                    player.getVelocity().y += JUMP_VELOCITY; // adjust JUMP VELOCITY
                    canJump = false;
                }
            }
        }
    }
    // Applies gravity to the player entities, making them fall when they are not
    // jumping.
    public void applyGravity() {
        for (Entity entity : playerControllerManager.getEntities().getEntityList()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.getVelocity().y += GRAVITY * Gdx.graphics.getDeltaTime();
//                if (canJump) {
//                    player.getVelocity().y += GRAVITY * Gdx.graphics.getDeltaTime();
//                } else {
//                    player.getVelocity().y = 0;
//                }
            }
        }
    }
    
	// Resets the jump flag, allowing the player to jump again
	public void resetJump() {
		canJump = true;
	}
}
