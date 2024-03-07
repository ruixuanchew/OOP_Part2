package entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	private float posX, posY, speed;
	private Color colour;
	private boolean isPlayer; // check if entity is player
	private Vector2 velocity; // to determine where the entity is currently located on the screen
	private Vector2 initialPosition; // store initial position of entity
	private String type;
	private boolean isVisible;
	private Texture tex;
	
	abstract void update();
//	abstract void create(String tex, float posX, float posY, float speed, boolean isPlayer, boolean isVisible, Vector2 velocity, String type);

	// default 
	public Entity() {

	}

	// parameterized 
	public Entity(String tex,float posX, float posY, float speed, boolean isPlayer, boolean isVisible, String type) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.isPlayer = isPlayer;
		this.isVisible = isVisible;
		this.type = type;
		this.initialPosition = new Vector2(posX, posY);
		this.tex = new Texture(Gdx.files.internal(tex));
	}
	
	public Entity(String tex, float posX, float posY, float speed, boolean isPlayer, boolean isVisible, Vector2 velocity, String type) {
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.isPlayer = isPlayer;
		this.isVisible = isVisible;
		this.velocity = velocity;
		this.type = type;
		this.initialPosition = new Vector2(posX, posY);
		this.tex = new Texture(Gdx.files.internal(tex));
	}
	
	// Getter methods 
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
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public String getType() {
        return type;
    }
	
	public boolean getVisible() {
		return isVisible;
	}
	
	public Texture getTexture()
	{
		return tex;
	}
	
	public float getWidth() 
	{
		return tex.getWidth();
	}
	
	public float getHeight() 
	{
		return tex.getHeight();
	}
	
	// Setter methods
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

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}
	
	public void setTexture(Texture t)
	{
		tex = t;
	}
	
	// Other methods
	public void resetPosition() {
		posX = initialPosition.x;
		posY = initialPosition.y;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(tex,this.getPosX(),this.getPosY());
	}

	public void movement() {

	}

	public void dispose(SpriteBatch batch) {

	}

	public boolean isPlayer() {
		return isPlayer;
	}


}
