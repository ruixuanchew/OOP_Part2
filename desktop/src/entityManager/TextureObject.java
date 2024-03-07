package entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TextureObject extends Entity {
	
	private Texture tex;

	// default
	public TextureObject()
	{
		
	}

	// parameterized
//	public TextureObject(String tex, float posX, float posY, float speed, boolean isPlayer,boolean isVisible, String type)
//	{
//		super(posX, posY, speed, isPlayer, isVisible, type);
//		this.tex = new Texture(Gdx.files.internal(tex));
//	}
//	
//	public TextureObject(String tex, float posX, float posY, float speed, boolean isPlayer, boolean isVisible,Vector2 velocity, String type)
//	{
//		super(posX, posY, speed, isPlayer, isVisible, velocity, type);
//		this.tex = new Texture(Gdx.files.internal(tex));
//	}

	public Texture getTexture()
	{
		return tex;
	}
	
	public void setTexture(Texture t)
	{
		tex = t;
	}
	
	public float getWidth() {
		return tex.getWidth();
	}
	
	public float getHeight() {
		return tex.getHeight();
	}

	@Override
	public void draw(SpriteBatch batch)
	{
		batch.draw(tex,this.getPosX(),this.getPosY(),tex.getWidth(),tex.getHeight());
	}

	@Override
	public void movement() {

	}

	@Override
	public void update() {

	}

}
