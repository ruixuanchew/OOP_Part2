package entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Entity {
	
	private Texture texture;

	// default
	public Enemy()
	{
		
	}

	// parameterized
	public Enemy(String tex, float posX, float posY, float speed, boolean isPlayer,boolean isVisible, String type)
	{
		super(tex, posX, posY, speed, isPlayer, isVisible, type);
		texture = new Texture(Gdx.files.internal(tex));
	}
	
	// enemy specific methods (tbc)

	@Override
	public void update() 
	{

	}

}
