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
import entityManager.Player;
import entityManager.EntityFactory;
import playerControllerManager.PlayerControllerManager;

public abstract class BasePlanetScene extends BaseScene {
	protected EntityManager entityManager;
	protected EntityFactory entityFactory;
	protected PlayerControllerManager pcManager;
	protected CollisionManager cManager;
	protected AIControlManager aiManager;
    private int screenWidth = Gdx.graphics.getWidth();
    protected int screenSwitchCounter = 0;

    private Player player;
    
	public BasePlanetScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager);         
		this.entityManager = entityManager;
		this.entityFactory = entityFactory;
        this.pcManager = pcManager;
        this.cManager = cManager;
        this.aiManager = aiManager;
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
	protected void spaceRender(SpriteBatch batch) {
		for (Entity e : entityManager.getEntityList()) {
        	if(e instanceof Player) {
        		this.player = (Player) e;
        		break;
        	}
        }
	    
	    // Update player's position based on input or other logic
	    pcManager.update(Gdx.graphics.getDeltaTime());
	    for (Entity e : entityManager.getCollidableEntityList()) {
	        if (e.getVisible()) {
	            cManager.checkCollision(player, e, sceneManager);
	        }
	    }

	 // Check if the player has moved beyond the screen width
	    if (player.getPosX() > Gdx.graphics.getWidth()) {
	        screenSwitchCounter++;
	        System.out.printf("Scene switch counter: %d\n", screenSwitchCounter);

	        // Move player back to the left side of the screen
	        player.setPosX(-player.getWidth());

	        // Update the positions of other entities accordingly
	        for (Entity e : entityManager.getEntityList()) {
	            // Adjust positions of entities excluding the player
	            if (!(e instanceof Player)) {
	                // Randomly set x-position outside the screen width
	                e.setPosX(MathUtils.random(100, 400));
	                e.setPosY(MathUtils.random(150, 300));
	            }
	        }
	    }
	    

	    // Draw entities after collision detection and player update
	    for (Entity e : entityManager.getEntityList()) {
	        if (e instanceof Player) {
	            e.setVisible(true); // Ensure visibility is set to true before drawing
	            e.draw(batch);
	        } else if (e.getType().equals("mercury") || e.getType().equals("venus") || e.getType().equals("mars")
	                || e.getType().equals("jupiter") || e.getType().equals("saturn") || e.getType().equals("uranus") || e.getType().equals("neptune")) {
	        	e.setVisible(false);
	            if (screenSwitchCounter == 2 && e.getType().equals("mercury")) {
	                e.setVisible(true);
	                e.draw(batch);
	            }
	            else if(screenSwitchCounter == 4 && e.getType().equals("venus")) {
	            	e.setVisible(true);
	                e.draw(batch);
	            }
	            else if(screenSwitchCounter == 6 && e.getType().equals("mars")) {
	            	e.setVisible(true);
	                e.draw(batch);
	            }
	            else if(screenSwitchCounter == 8 && e.getType().equals("jupiter")) {
	            	e.setVisible(true);
	                e.draw(batch);
	            }
	            else if(screenSwitchCounter == 10 && e.getType().equals("uranus")) {
	            	e.setVisible(true);
	                e.draw(batch);
	            }
	            else if(screenSwitchCounter == 12 && e.getType().equals("neptune")) {
	            	e.setVisible(true);
	                e.draw(batch);
	            }
	        }
	        else if(e.getType().equals("asteroid")){
	        	// To make asteroid AI controlled
	        	aiManager.moveAIControlled();
	        	e.setVisible(true);
                e.draw(batch);
	        }
	        else {
	            e.setVisible(false);
	        }
	    }
	}
    protected void planetRender(SpriteBatch batch) {
    	for (Entity e : entityManager.getEntityList()) {
        	if(e.getType().equals("player")) {
        		this.player = (Player) e;
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
	    for (Entity e : entityManager.getCollidableEntityList()) {
	        if (e.getVisible()) {
	            cManager.checkCollision(player, e, sceneManager);
	        }
	    }

	    // Draw entities after collision detection and player update
	    for (Entity e : entityManager.getEntityList()) {
	    	
	        if (e.getType().equals("player") || e.getType().equals("flag")) {
	            e.setVisible(true); // Ensure visibility is set to true before drawing
	            e.draw(batch);
	        }
	        else if(e.getType().equals("asteroid")) {
	        	 e.setVisible(true); // Ensure visibility is set to true before drawing
		         e.draw(batch);
		         aiManager.moveAIControlled();
	        }
	        else {
	            e.setVisible(false);
	        }
	    }

    }
	

}
