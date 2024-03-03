package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.TextureObject;
import playerControllerManager.PlayerControllerManager;

public abstract class BasePlanetScene extends BaseScene {
	private EntityManager entityManager;
    private PlayerControllerManager pcManager;
    private CollisionManager cManager;
    private AIControlManager aiManager;
    private TextureObject player;
    private TextureObject entity;
    private int screenWidth = Gdx.graphics.getWidth();
    
	public BasePlanetScene(SceneManager sceneManager, EntityManager entityManager, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager);         
		this.entityManager = entityManager;
        this.pcManager = pcManager;
        this.cManager = cManager;
        
        this.cManager = new CollisionManager(sceneManager);
        this.aiManager = aiManager;
        this.sceneManager = sceneManager;
	}

	protected void addText(String text, SpriteBatch batch, Color color) {
    	BitmapFont planetTxt = new BitmapFont();
    	planetTxt.setColor(color);
    	GlyphLayout layout = new GlyphLayout(planetTxt, text);
        float textWidth = layout.width;
        float textHeight = layout.height;

        float screenWidth = Gdx.graphics.getWidth();
        float x = (screenWidth - textWidth) / 2; // Center the text horizontally

        float y = Gdx.graphics.getHeight() - textHeight;
        
    	planetTxt.draw(batch, text, x, y);
    }
    protected void planetRender(SpriteBatch batch) {
    	for (Entity e : entityManager.getEntityList()) {
        	if(e.getType().equals("player")) {
        		this.player = (TextureObject) e;
        		break;
        	}
        }
	    
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

    }
	

}
