package entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player implements Entity {

	private Texture texture;
	private float posX, posY, speed;
	private Vector2 velocity; // to determine where the entity is currently located on the screen
	private Vector2 initialPosition; // store initial position of entity
	private boolean isVisible;
	private String type;
	private int health;
	private int screenWidth = Gdx.graphics.getWidth();


	public Player(String tex, float posX, float posY, float speed, boolean isVisible, Vector2 velocity, String type, int health) {

		this.texture = new Texture(Gdx.files.internal(tex));
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.velocity = velocity;
		this.initialPosition = new Vector2(posX, posY);
		this.isVisible = isVisible;
		this.type = type;
		this.health = health;
		
	}
	
	// get methods
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public String getType() {
		return type;
	}
	
	public float getWidth() {
		return texture.getWidth();
	}
	
	public float getHeight() {
		return texture.getHeight();
	}
	
	public boolean getVisible() {
		return isVisible;
	}

	public float getSpeed() {
		return speed;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	// set methodss
	public void setPosX(float x) {
		this.posX = x;
	}
	
	public void setPosY(float y) {
		this.posY = y;
	}
	
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	public void setSpeed(int s) {
		speed = s;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	// player loses 10 health every time they take damag
	public void takeDamage() {
		health-=10;
	}

	public Rectangle getBoundingRectangle() {
		return new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());
	}
	
	// draw entities
	@Override 
	public void draw(SpriteBatch batch) {
		
		batch.draw(texture,posX,posY);
	}

	@Override 
	public void update() {
		
	}
	
	@Override 
	public void resetEntities() {
		
	}


	public void updatePosition(float deltaTime) {

		// player.setPosX(player.getPosX() + player.getVelocity().x * deltaTime);
		this.setPosX(this.getPosX() + this.getVelocity().x * deltaTime);
		this.setPosY(this.getPosY() + this.getVelocity().y * deltaTime);

		// System.out.println("Player's speed: " + this.getSpeed());
		checkScreenBoundaries();
		// setEndPlayerPosition();


	}

	private void checkScreenBoundaries() {
		// Check if the entity is at the left edge of the screen
		if (getPosX() <= 1) {
			getVelocity().x = 0; // Stop movement
			setPosX(0); // Reset position to the edge
		}

		// Check if the entity is at the top edge of the screen
		if (getPosY() >= Gdx.graphics.getHeight() - getHeight()) {
			getVelocity().y = 0; // Stop upward movement
			setPosY(Gdx.graphics.getHeight() - getHeight()); // Reset position to the top edge
		}

		// Check if the entity is at the bottom edge of the screen
		if (getPosY() <= 0) {
			getVelocity().y = 0; // Stop downward movement
			setPosY(0); // Reset position to the bottom edge
		}

	}
	public void setEndPlayerPosition() {
		if (getPosX() >= Gdx.graphics.getWidth() - getWidth()) {
			getVelocity().x = 0; // Stop movement
			setPosX(screenWidth - getWidth()); // Reset position to the edge
		}
	}
	@Override 
	public void dispose(SpriteBatch batch) {
		
	}
}
