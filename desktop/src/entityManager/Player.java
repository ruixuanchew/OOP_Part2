package entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Player implements Entity {

	private Texture texture;
	private float posX, posY, speed;
	private Vector2 velocity; // to determine where the entity is currently located on the screen
	private Vector2 initialPosition; // store initial position of entity
	private boolean isVisible;
	private String type;
	private int health;

	private Body pBody;
	public PolygonShape shape;
	
	public Player(World world, String tex, float posX, float posY, float speed, boolean isVisible, Vector2 velocity, String type, int health) {

		this.texture = new Texture(Gdx.files.internal(tex));
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.velocity = velocity;
		this.initialPosition = new Vector2(posX, posY);
		this.isVisible = isVisible;
		this.type = type;
		this.health = health;

		pBody = createBox(world, (int)posX, (int)posY, texture.getWidth(), texture.getHeight(), false);
		pBody.setUserData(this);
	}
	
	public float getPosX() {
		return posX;
	};
	
	public float getPosY() {
		return posY;
	};
	
	public String getType() {
		return type;
	};
	
	public float getWidth() {
		return texture.getWidth();
	};
	
	public float getHeight() {
		return texture.getHeight();
	};
	
	public boolean getVisible() {
		return isVisible;
	}

	public float getSpeed() {
		return speed;
	};
	
	public Vector2 getVelocity() {
		return velocity;
	};
	
	public Body getBody() {
		return pBody;
	};
	
	public void setPosX(float x) {
		this.posX = x;
	};
	
	public void setPosY(float y) {
		this.posY = y;
	};
	
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	public void setSpeed(int s) {
		speed = s;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	};
	
	public int getHealth() {
		return health;
	}
	
	public void takeDamage() {
		health--;
	}

	public Rectangle getBoundingRectangle() {
		return new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());
	}	
	
	@Override 
	public void draw(SpriteBatch batch) {

		int w = texture.getWidth();
		int h = texture.getHeight();
		
		batch.draw(texture, pBody.getPosition().x*32-w/2, pBody.getPosition().y*32-h/2, w, h);
	}

	@Override 
	public void update() {
		
	}
	
	@Override 
	public void resetEntities() {
		posX = initialPosition.x;
		posY = initialPosition.y;
	}
	
	@Override 
	public void dispose(SpriteBatch batch) {
		
	}
	
	@Override
	public Body createBox(World world, int x, int y, int width, int height, boolean isStatic)
	{
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2/32, height/2/32);
		Body pBody = createBody(world, shape, isStatic, x, y);
		
		return pBody;
	}

	@Override
	public Body createBody(World world, Shape shape, boolean staticobj, float x, float y)
	{
		Body body;
		BodyDef bdef = new BodyDef();
		
		if (staticobj)
		{
			bdef.type = BodyDef.BodyType.StaticBody;
		}
		else 
		{
			bdef.type = BodyDef.BodyType.DynamicBody;
			bdef.position.set(x/32, y/32);
			bdef.fixedRotation = true; 
		}
		
		body = world.createBody(bdef);
		body.createFixture(shape, 1.0f);
		shape.dispose();
		
		return body;
	}
}
