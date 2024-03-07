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
import inputOutputManager.InputOutputManager;
import playerControllerManager.PlayerControllerManager;

public class EarthScene extends BasePlanetScene{
	private EntityManager entityManager;
    private PlayerControllerManager pcManager;
    private CollisionManager cManager;
    private AIControlManager aiManager;
    private TextureObject player;
    private TextureObject entity;
	private MapManager mapManager;
    
	public EarthScene(SceneManager sceneManager, EntityManager entityManager, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, pcManager, cManager, aiManager);
		this.entityManager = entityManager;
        this.pcManager = pcManager;
        this.cManager = cManager;
        this.cManager = new CollisionManager(sceneManager);
        this.aiManager = aiManager;

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Earth.tmx");

        initializeScene();
	}
	 private void initializeScene() {
	        this.player = new TextureObject("bucket.png", 150, 150, 200, true, false, new Vector2(0, 0), "player");
	        entityManager.add(this.player);

	        this.entity = new TextureObject("flag.png", 500, 150, 4, false, false, "flag");
	        entityManager.add(this.entity);
	        entityManager.addCollidableEntity(this.entity);

	        String buttonText = "End";
	        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
	                () -> sceneManager.showEndScene());
	    }

	@Override
	protected Color getBackgroundColor() {
		// TODO Auto-generated method stub
		return new Color(0, 0.5f, 0, 1);
	}

	@Override
	protected Color getMapBackground() {
	
		return null;
	}
	
	@Override
	public void render(float delta) {
	    super.render(delta);

		// call render method from MapManager to render the current map
		mapManager.getRenderer().setView(mapManager.getCamera());
		mapManager.getRenderer().render();
	    
	    SpriteBatch batch = new SpriteBatch();
	    
	    String text = "Earth";
	    
	    batch.begin();
	    super.addText(text, batch, Color.BLACK);
	    
	    // Update player's position based on input or other logic
	    pcManager.update(Gdx.graphics.getDeltaTime());

	    // Check if the player has moved beyond the screen width
	    if (player.getPosX() > Gdx.graphics.getWidth()) {
	        // Move player back to the left side of the screen
	        player.setPosX(-player.getWidth());
	        
	        // Update the positions of other entities accordingly
	        for (Entity e : entityManager.getEntityList()) {
	            // Adjust positions of entities excluding the player
	            if (!e.getType().equals("player")) {
	                // Example adjustment: Move the entity to a new random position within the screen width
	                e.setPosX(MathUtils.random(100, 400));
	                e.setPosY(MathUtils.random(150, 300));
	                // You can adjust Y position similarly or differently based on your game logic
	            }
	        }
	    }

	    // Check collisions before drawing entities
	    for (TextureObject e : entityManager.getCollidableEntityList()) {
	        if (e.getVisible()) {
	            cManager.checkCollision(player, e, sceneManager);
	        }
	    }

	    // Draw entities after collision detection and player update
	    for (Entity e : entityManager.getEntityList()) {
	        if (e.getType().equals("player") || e.getType().equals("flag")) {
	            e.setVisible(true); // Ensure visibility is set to true before drawing
	            e.draw(batch);
	        } else {
	            e.setVisible(false);
	        }
	    }

	    batch.end();
	}



}
