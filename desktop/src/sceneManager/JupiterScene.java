package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.TextureObject;
import playerControllerManager.PlayerControllerManager;

public class JupiterScene extends BasePlanetScene{
	private EntityManager entityManager;
    private PlayerControllerManager pcManager;
    private CollisionManager cManager;
    private AIControlManager aiManager;
    private TextureObject player;
    private TextureObject entity;
    
	public JupiterScene(SceneManager sceneManager, EntityManager entityManager, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, pcManager, cManager, aiManager);
		this.entityManager = entityManager;
        this.pcManager = pcManager;
        this.cManager = cManager;
        
        this.cManager = new CollisionManager(sceneManager);
        this.aiManager = aiManager;
        
        initializeScene();
	}
	private void initializeScene() {
        String buttonText = "End";
        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
                () -> sceneManager.showEndScene());
    }
	

	@Override
	protected Color getBackgroundColor() {
		return new Color(0.7f, 0.6f, 0.5f, 1);
	}
	@Override
	protected Color getMapBackground() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void render(float delta) {
	    super.render(delta);
	    
	    SpriteBatch batch = new SpriteBatch();
	    
	    String text = "Jupiter";
	    
	    batch.begin();
	    super.addText(text, batch, Color.BLACK);
	    super.planetRender(batch);

	    batch.end();
	}
}