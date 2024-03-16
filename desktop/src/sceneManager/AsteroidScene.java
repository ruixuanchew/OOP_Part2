package sceneManager;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import aiControlManager.AIControlManager;
import playerControllerManager.PlayerControllerManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;
import collisionManager.CollisionManager;

public class AsteroidScene extends BasePlanetScene{
    private int screenWidth = Gdx.graphics.getWidth();
	private MapManager mapManager;
	private int screenSwitchCounter = 0;
	private Random random;

	public AsteroidScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager ) {
		super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);
		
		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("space.tmx");

        initializeScene();
	}
	 private void initializeScene() {
		int x = MathUtils.random(100, 400);
		int y = MathUtils.random(150, 300);
		 
		Random random = new Random();
		 
		for (int i = 0; i < 3; i++) {
		    float posX = random.nextInt(Gdx.graphics.getWidth());
		    float posY = random.nextInt(Gdx.graphics.getHeight());
		    
			Entity enemy = entityFactory.createEntity("asteroids.png", posX, posY, 4, false, "asteroid");
			entityManager.add(enemy);
			entityManager.addCollidableEntity(enemy);
		}
	     
		 String[] planetNames = {"mercury","venus"};
		 for(int i = 0; i < planetNames.length; i++) {
			 String name = planetNames[i];
			 String fileName = planetNames[i] + ".png";
			 Entity planet = entityFactory.createEntity(fileName, x, y, false, name);
			 entityManager.add(planet);
			 entityManager.addCollidableEntity(planet);
		 }
	        
	        String buttonText = "End";
	        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
	                () -> sceneManager.showEndScene());
	  }

	@Override
	protected Color getBackgroundColor() {
		return new Color(0, 0, 0, 1);
	}

	  @Override
	    public void render(float delta) {
		  super.render(delta);

		  // call render method from MapManager to render the current map
		  mapManager.getRenderer().setView(mapManager.getCamera());
		  mapManager.getRenderer().render();

	        SpriteBatch batch = new SpriteBatch();
	        String text = "Space";

	        batch.begin();
		    super.addText(text, batch, Color.WHITE);
		    super.spaceRender(batch);
		    batch.end();
	  }
	@Override
	protected void showDialog() {
		// TODO Auto-generated method stub
		
	}
}
