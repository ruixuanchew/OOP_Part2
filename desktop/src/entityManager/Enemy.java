package entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy implements Entity {

	private Texture texture;
	private float posX, posY, speed;
	private boolean isVisible;
	private String type;
	
	private SpriteBatch batch;
	
	public Enemy(String tex, float posX, float posY, float speed, boolean isVisible, String type) {

		this.texture = new Texture(Gdx.files.internal(tex));
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		this.isVisible = isVisible;
		this.type = type;
		
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
	
	@Override 
	public void dispose(SpriteBatch batch) {
		
	}

}
