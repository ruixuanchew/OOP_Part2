package entityManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class EntityFactory {
	
	public Entity createPlayer(World world, String tex, float posX, float posY, float speed, boolean isVisible, Vector2 velocity, String type, int health) {
        
		Entity player = new Player(world, tex, posX, posY, speed, isVisible, velocity, type, health);
		return player;
    }

    public Entity createObject(World world, String tex, float posX, float posY, boolean isVisible, String type, boolean isCollidable) {
        
    	Entity object = new Object(world, tex, posX, posY, isVisible, type, isCollidable);
		return object;
    }
    

    public Entity createEnemy(World world, String tex, float posX, float posY, float speed, boolean isVisible, String type) {
    	
    	Entity enemy = new Enemy(world, tex, posX, posY, speed, isVisible, type);
		return enemy;
    }

}