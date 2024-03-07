package entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Object extends Entity {
	
	private Texture texture;

	// default
	public Object()
	{
		
	}

	// parameterized
	public Object(String tex, float posX, float posY, float speed, boolean isPlayer,boolean isVisible, String type)
	{
		super(tex, posX, posY, speed, isPlayer, isVisible, type);
		texture = new Texture(Gdx.files.internal(tex));
	}

	// object specific methods (tbc)
	
	@Override
	public void update() 
	{

	}

}
