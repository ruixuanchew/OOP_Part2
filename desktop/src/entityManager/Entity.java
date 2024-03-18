package entityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public interface Entity {	
	
	Body createBox(World world, int x, int y, int width, int height, boolean isStatic);
	Body createBody(World world, Shape shape, boolean staticobj, float x, float y);
	
	public void draw(SpriteBatch batch);
	public void update();
	public void resetEntities();
	public void dispose(SpriteBatch batch);
	
	// get methods
	float getPosX();
	
	float getPosY();
	
	String getType();
	
	float getWidth();

	float getHeight();
	
	boolean getVisible();
	
	Body getBody();
	
	// set methods
	void setPosX(float x);
	
	void setPosY(float y);
	
	void setVisible(boolean visible);

	
}