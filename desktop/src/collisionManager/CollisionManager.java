package collisionManager;

import entityManager.Entity;
import sceneManager.SceneManager;

public class CollisionManager {
	private SceneManager sceneManager;

	public CollisionManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public void checkCollision(Entity player, Entity entity, SceneManager sceneManager) {
		float playerX = player.getPosX();
		float playerY = player.getPosY();
		float playerWidth = player.getWidth();
		float playerHeight = player.getHeight();

		float entityX = entity.getPosX();
		float entityY = entity.getPosY();
		float entityWidth = entity.getWidth();
		float entityHeight = entity.getHeight();

		// find dist between player & entity
		float distX = Math.abs(playerX - entityX);
		float distY = Math.abs(playerY - entityY);
		double distance = Math.sqrt((distX * distX) + (distY * distY));
		
		if (playerX < entityX + entityWidth &&
		        playerX + playerWidth > entityX &&
		        playerY < entityY + entityHeight &&
		        playerY + playerHeight > entityY) {
		        handleCollision(player, entity);
		    }
	}

	private void handleCollision(Entity player, Entity entity) {
		if (player.getPosX() > entity.getPosX()) {
			player.setPosX(player.getPosX() + 5);
		} else if (player.getPosX() < entity.getPosX()) {
			player.setPosX(player.getPosX() - 5);
		}
		if (player.getPosY() > entity.getPosY()) {
			player.setPosY(player.getPosY() + 1);
		} else if (player.getPosY() < entity.getPosY()) {
			player.setPosY(player.getPosY() - 1);
		}
		System.out.println("\u001B[31m" + "Collision detected between player and entity!" + "\u001B[0m");
		 // Check if the collided entity is a specific type (e.g., a flag)
		if (entity.getType().equals("flag")) {
			sceneManager.showAsteroidScene();
			player.setPosX(0);
		}
		if (entity.getType().equals("mercury")) {
			sceneManager.showMercuryScene();
			player.setPosX(0);
		}
		if (entity.getType().equals("venus")) {
			sceneManager.showVenusScene();
			player.setPosX(0);
		}
		if (entity.getType().equals("mars")) {
			sceneManager.showMarsScene();
			player.setPosX(0);
		}
		if (entity.getType().equals("jupiter")) {
			sceneManager.showJupiterScene();
			player.setPosX(0);
		}
		if (entity.getType().equals("saturn")) {
			sceneManager.showSaturnScene();
			player.setPosX(0);
		}
		if (entity.getType().equals("uranus")) {
			sceneManager.showUranusScene();
			player.setPosX(0);
		}
		if(entity.getType().equals("neptune")) {
			sceneManager.showNeptuneScene();
			player.setPosX(0);
		}
	}

}
