package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;
import playerControllerManager.PlayerControllerManager;
import entityManager.Entity;
import entityManager.EntityManager;

public class MercuryScene extends BasePlanetScene {
	private EntityManager entityManager;
    private PlayerControllerManager pcManager;
    private CollisionManager cManager;
    private AIControlManager aiManager;
	private MapManager mapManager;

    public MercuryScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
    	super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);
		this.entityManager = entityManager;
        this.pcManager = pcManager;
        this.cManager = cManager;
        
        this.cManager = new CollisionManager(sceneManager, pcManager);
        this.aiManager = aiManager;

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Mercury.tmx");
        
        initializeScene();
	}
	 private void initializeScene() {
	        String buttonText = "End";
	        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
	                () -> sceneManager.showEndScene());
	  }

	@Override
	protected Color getBackgroundColor() {
		return new Color(0.2f, 0.2f, 0.2f, 1);
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
	    
	    String text = "Mercury";
	    
	    batch.begin();
	    
	    super.planetRender(batch);
	    super.addText(text, batch, Color.BLACK);
	    

	    batch.end();
	}
}
