package collisionManager;

import entityManager.EntityManager;
import entityManager.Player;
import entityManager.Entity;
import inputOutputManager.InputOutputManager;
import playerControllerManager.PlayerControllerManager;
import simulationLifecycleManager.SimulationLifecycleManager;
import sceneManager.*;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

public class CollisionManager implements CollisionInterface {
	private SceneManager sceneManager;
	private PlayerControllerManager playerControllerManager;
	private EntityManager entityManager;
	private InputOutputManager ioManager;
	private SimulationLifecycleManager slManager;
	// List for observers for Collision Observer
	private List<CollisionObserver> observers = new ArrayList<>();

	public CollisionManager(SceneManager sceneManager, 
			PlayerControllerManager playerControllerManager, EntityManager entityManager, InputOutputManager ioManager, SimulationLifecycleManager slManager) {
		this.sceneManager = sceneManager;
		this.playerControllerManager = playerControllerManager;
		this.entityManager = entityManager;
		this.ioManager = ioManager;
		this.slManager = slManager;
		// Since observer is calling to slManager 
		// If slManager instance of collision observer
		if (slManager instanceof CollisionObserver) {
			// Add to the list the collision observer in the instance of sl Manager
            observers.add((CollisionObserver) slManager);
        }
	}
	// Register the observer as observer and add to list
	public void registerObserver(CollisionObserver observer) {
        observers.add(observer);
    }
	// Notify Observers when there is collision 
	 private void notifyObservers() {
		 // For range of observers list
	        for (CollisionObserver observer : observers) {
	        	// Call observer interface onCollisionOccurred();
	            observer.onCollisionOccurred();
	        }
	    }
	
	//function to check collision between player and entity
	public void checkCollision(Entity player, Entity entity) {
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
		
		//check if player and entity have an intersection point
		if (playerX < entityX + entityWidth &&
		        playerX + playerWidth > entityX &&
		        playerY < entityY + entityHeight &&
		        playerY + playerHeight > entityY) {
		        handleCollision(player, entity);
		    }
	}
	
	//function to handle collision
	private void handleCollision(Entity player, Entity entity) {
		//check x coordinate of player and entity, if player is to the right/more than entity, shift player to right
		if (player.getPosX() > entity.getPosX()) {
			player.setPosX(player.getPosX() + 5);
		//check x coordinate of player and entity, if player is to the left/less than entity, shift player to left
		} else if (player.getPosX() < entity.getPosX()) {
			player.setPosX(player.getPosX() - 5);
		}
		//check y coordinate of player and entity, if player is to higher/more than entity, shift player up
		if (player.getPosY() > entity.getPosY()) {
			player.setPosY(player.getPosY() + 1);
		//check y coordinate of player and entity, if player is to lower/lower than entity, shift player down
		} else if (player.getPosY() < entity.getPosY()) {
			player.setPosY(player.getPosY() - 1);
		}
		System.out.println("\u001B[31m" + "Collision detected between player and entity!" + "\u001B[0m");
		
		// check if collided entity is an enemy
		if (entity.getType().equals("asteroid") || entity.getType().equals("fireball") || entity.getType().equals("rock")) {	
			entityManager.remove(entity); 
			Player playerObject = (Player) player; //downcast player to player tag to access function
			playerObject.takeDamage();
			ioManager.playSoundEffect();

			//swap to end scene if player hp = 0
			if (playerObject.getHealth() <= 0) {
				// call notify observers function when there is a collision + switch scene
				notifyObservers();
				sceneManager.setCurrentScene(SceneType.END_SCENE);
			}
		}
		//check if collided entity is flag, show end scene if venus else asteroid scene
		if (entity.getType().equals("flag")) {
			 BaseScene currentScene = sceneManager.getCurrentScene();
		        if (currentScene instanceof VenusScene) {
		        	// call notify observers function when there is a collision + switch scene
		        	notifyObservers();
		        	sceneManager.setCurrentScene(SceneType.END_SCENE);;
		        }
				else {
					// call notify observers function when there is a collision + switch scene
					notifyObservers();
					sceneManager.setCurrentScene(SceneType.SPACE_SCENE);
					player.setPosX(0);
		        }
				if (currentScene instanceof EarthScene) {
					// call notify observers function when there is a collision + switch scene
					notifyObservers();
					sceneManager.setCurrentScene(SceneType.EARTH_SCENE2);
					player.setPosX(20);
					player.setPosY(150);
			}
		}
		//check if collided entity is mercury planet
		if (entity.getType().equals("mercury")) {
			// call notify observers function when there is a collision + switch scene
			notifyObservers();
			sceneManager.setCurrentScene(SceneType.MERCURY_SCENE);
			player.setPosX(0);
		}
		//check if collided entity is venus planet
		if (entity.getType().equals("venus")) {
			// call notify observers function when there is a collision + switch scene
			notifyObservers();
			sceneManager.setCurrentScene(SceneType.VENUS_SCENE);
			player.setPosX(0);
		}
	}

	// for checking collision with TiledMap object layers
	public void checkCollisionWithTiledMap(Player player, MapManager mapManager) {
		// check if player collided with building object
		for (RectangleMapObject rectangleObject : mapManager.getObject().getByType(RectangleMapObject.class)) {
			Rectangle rectangle = rectangleObject.getRectangle();

			if (player.getBoundingRectangle().overlaps(rectangle)) {
				// if player collides with object
				float playerLeft = player.getPosX();
				float playerRight = player.getPosX() + player.getWidth();
				float playerBottom = player.getPosY();
				float playerTop = player.getPosY() + player.getHeight();

				float buildingLeft = rectangle.x;
				float buildingRight = rectangle.x + rectangle.width;
				float buildingBottom = rectangle.y;
				float buildingTop = rectangle.y + rectangle.height;

				// Find the closest side and adjust position accordingly
				float leftOverlap = playerRight - buildingLeft;
				float rightOverlap = buildingRight - playerLeft;
				float bottomOverlap = playerTop - buildingBottom;
				float topOverlap = buildingTop - playerBottom;

				// Find the minimum overlap value
				float minOverlap = Math.min(Math.min(leftOverlap, rightOverlap), Math.min(bottomOverlap, topOverlap));

				// Resolve collision based on the minimum overlap
				if (minOverlap == leftOverlap) {
					player.setPosX(playerLeft - leftOverlap);
					player.getVelocity().x *= 0.2f;
				} else if (minOverlap == rightOverlap) {
					player.setPosX(playerLeft + rightOverlap);
					player.getVelocity().x *= 0.2f;
				} else if (minOverlap == bottomOverlap) {
					player.setPosY(playerBottom - bottomOverlap);
					player.getVelocity().y *= 0.2f;
				} else if (minOverlap == topOverlap) {
					player.setPosY(playerBottom + topOverlap);
					player.getVelocity().y *= 0.2f;
				}
			}
		}
		// check if player collided with lava
		for (RectangleMapObject rectangleObject : mapManager.getLavaObject().getByType(RectangleMapObject.class)) {
			Rectangle rectangle = rectangleObject.getRectangle();

			if (player.getBoundingRectangle().overlaps(rectangle)) {
				// if player collides with lava, reset player's position to the start of the map
				player.setPosX(0);
				player.setPosY(0);
				ioManager.playSoundEffect(); // sound effect for collision
				Player playerObject = (Player) player; //downcast player to player tag to access function
				playerObject.takeDamage(); // player takes damage from collision

				// Check if the current scene is EarthScene2
				BaseScene currentScene = sceneManager.getCurrentScene();
				if (currentScene instanceof EarthScene2) {
					// If it is, switch back to EarthScene
					sceneManager.setCurrentScene(SceneType.EARTH_SCENE);
				}

				//swap to end scene if player hp = 0
				if (playerObject.getHealth() <= 0) {
					sceneManager.setCurrentScene(SceneType.END_SCENE);
				}
			}
		}
	}
}
