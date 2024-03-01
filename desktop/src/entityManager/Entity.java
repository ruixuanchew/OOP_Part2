package entityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	private float posX, posY, speed;
	private Color colour;
	private boolean isPlayer; // check if entity is player
	private Vector2 velocity; // to determine where the entity is currently located on the screen
	private Vector2 initialPosition; // store initial position of entity

	abstract void update();
	public abstract float getWidth();
	public abstract float getHeight();

	// default constructor
	public Entity() {

	}

	// parameterized constructor
	public Entity(float posX, float posY, float speed, boolean isPlayer) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.isPlayer = isPlayer;
		this.initialPosition = new Vector2(posX, posY);
	}
	
	public Entity(float posX, float posY, float speed, boolean isPlayer, Vector2 velocity) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.isPlayer = isPlayer;
		this.velocity = velocity;
		this.initialPosition = new Vector2(posX, posY);
	}
	
	public void resetPosition() {
		posX = initialPosition.x;
		posY = initialPosition.y;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public float getSpeed() {
		return speed;
	}

	public Color getColour() {
		return colour;
	}

	public void setPosX(float x) {
		posX = x;
	}

	public void setPosY(float y) {
		posY = y;
	}

	public void setSpeed(int s) {
		speed = s;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void draw(ShapeRenderer shape) {

	}

	public void draw(SpriteBatch batch) {

	}

	public void movement() {

	}

	public void dispose(SpriteBatch batch) {

	}

	public boolean isPlayer() {
		return isPlayer;
	}


}
