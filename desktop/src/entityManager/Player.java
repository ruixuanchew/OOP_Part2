package entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
	
	private Texture texture;

	// default
	public Player()
	{
		
	}

	// parameterized
	public Player(String tex, float posX, float posY, float speed, boolean isPlayer,boolean isVisible, Vector2 velocity, String type)
	{
		super(tex, posX, posY, speed, isPlayer, isVisible, velocity, type);
		texture = new Texture(Gdx.files.internal(tex));
	}
	
	// player specific methods (tbc)

	@Override
	public void update() 
	{

	}

}
