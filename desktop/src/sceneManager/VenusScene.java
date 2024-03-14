package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;
import playerControllerManager.PlayerControllerManager;

public class VenusScene extends BasePlanetScene{
	private MapManager mapManager;
	private Random random;

	public VenusScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Venus.tmx");
        
        initializeScene();
	}
	 private void initializeScene() {
		for (int i = 0; i > 5; i++) {
            float posX = random.nextInt(Gdx.graphics.getWidth());
            float posY = random.nextInt(Gdx.graphics.getHeight());
            
			Entity enemy = entityFactory.createEntity("asteroid.png", posX, posY, 4, false, "asteroid");
			entityManager.add(enemy);
		}
	    //entityManager.addCollidableEntity(this.entity);

	    String buttonText = "End";
	    addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
	             () -> sceneManager.showEndScene());
	 }

	@Override
	protected Color getBackgroundColor() {
		return new Color(0.8f, 0.4f, 0, 1);
	}

	@Override
	protected Color getMapBackground() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void render(float delta) {
	    super.render(delta);

		// call render method from MapManager to render the current map
		mapManager.getRenderer().setView(mapManager.getCamera());
		mapManager.getRenderer().render();
	    
	    SpriteBatch batch = new SpriteBatch();
	    
	    String text = "Venus";
	    
	    batch.begin();
	    super.addText(text, batch, Color.BLACK);
	    super.planetRender(batch);

	    batch.end();
	}

}
