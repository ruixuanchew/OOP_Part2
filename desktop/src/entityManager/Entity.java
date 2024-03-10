package entityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
	
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
	
	// set methods
	void setPosX(float x);
	
	void setPosY(float y);
	
	void setVisible(boolean visible);
	
}