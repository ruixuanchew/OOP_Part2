package playerControllerManager;
import entityManager.Entity;
import entityManager.Player;
public class Movement implements PlayerBehavior {
    private PlayerControllerManager playerControllerManager;

    public Movement(PlayerControllerManager playerControllerManager) {
        this.playerControllerManager = playerControllerManager;
    }

    // Updates the velocity of the player entities based on the input from the
    // keyboard.

    // Abstract methods from PlayerBehavior
    @Override
    public void moveLeft() {
        for (Entity entity : playerControllerManager.getEntities().getEntityList()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (playerControllerManager.getMovement().LeftKey()) {
                	//System.out.println("Moving left!");
                    player.getVelocity().x = -player.getSpeed();
                }
                else if (!playerControllerManager.getMovement().RightKey()) {
                    player.getVelocity().x = 0;
                }
            }
        }
    }
    @Override
    public void moveRight(){
        for (Entity entity : playerControllerManager.getEntities().getEntityList()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (playerControllerManager.getMovement().RightKey()) {

                    player.getVelocity().x = player.getSpeed();
                    //System.out.println("Moving right!");
                }
                else if (!playerControllerManager.getMovement().LeftKey()) {
                    player.getVelocity().x = 0;
                }

            }
        }
    }
    @Override
    public void moveUp(){
        for (Entity entity : playerControllerManager.getEntities().getEntityList()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (playerControllerManager.getMovement().UpKey()) {
                    player.getVelocity().y = player.getSpeed();
                }
                else if (!playerControllerManager.getMovement().DownKey()) {
                    player.getVelocity().y = 0;
                }
            }
        }

    }
    @Override
    public void moveDown(){
        for (Entity entity : playerControllerManager.getEntities().getEntityList()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (playerControllerManager.getMovement().DownKey()) {
                    player.getVelocity().y = -player.getSpeed();
                }
                else if (!playerControllerManager.getMovement().UpKey()) {
                    player.getVelocity().y = 0;
                }
            }
        }

    }

    @Override
    public void jump() {

    }
}
