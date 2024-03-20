package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

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
    private boolean dialogOpen = true;
    private UIManager uiManager;

    private Player player;
    
	public BasePlanetScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager);         
		this.entityManager = entityManager;
		this.entityFactory = entityFactory;
        this.pcManager = pcManager;
        this.cManager = cManager;
        this.aiManager = aiManager;
        uiManager = new UIManager(stage, this);
	}
	
	// Game logic functions
	protected void commonRenders() {
		for (Entity e : entityManager.getEntityList()) {
        	if(e.getType().equals("player")) {
        		this.player = (Player) e;
        		break;
        	}
        }
		// Check collisions before drawing entities
	    for (Entity e : entityManager.getCollidableEntityList()) {
	        if (e.getVisible()) {
	            cManager.checkCollision(player, e, sceneManager);
	        }
	    }
	}
	protected void spaceRender(SpriteBatch batch) {
		commonRenders();
	    // Update player's position based on input or other logic
	    pcManager.update(Gdx.graphics.getDeltaTime());

	 // Check if the player has moved beyond the screen width
	    if (player.getPosX() > Gdx.graphics.getWidth()) {
	        screenSwitchCounter++;
	        System.out.printf("Scene switch counter: %d\n", screenSwitchCounter);

	        // Move player back to the left side of the screen
	        player.setPosX(-player.getWidth());

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
    	commonRenders();
    	
    	if(dialogOpen == false) {
    		// Update player's position based on input or other logic
    	    pcManager.update(Gdx.graphics.getDeltaTime());

    	    // Check if the player has moved beyond the screen width
    	    if (player.getPosX() > Gdx.graphics.getWidth()) {
    	        // Move player back to the left side of the screen
    	        player.setPosX(-player.getWidth());
    	        
    	    }
    	
	    // Draw entities after collision detection and player update
	    for (Entity e : entityManager.getEntityList()) {
	    	
	        if (e.getType().equals("player") || e.getType().equals("flag")) {
	            e.setVisible(true); // Ensure visibility is set to true before drawing
	            e.draw(batch);
	        }
//	        else if(e.getType().equals("asteroid")) {
//	        	 e.setVisible(true); // Ensure visibility is set to true before drawing
//		         e.draw(batch);
//		         aiManager.moveAIControlled();
//	        }
	        else {
	            e.setVisible(false);
	        }
	    }
    	}
	    
    }
    protected void renderStages() {
    	stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	    stage.draw();
    }
    // Dialog getters and setters
    protected boolean getDialogOpen() {
    	return dialogOpen;
    }
    protected void setDialogOpen(boolean dialogOpen) {
    	this.dialogOpen = dialogOpen;
    }
    // UI elements specific to planets only
    @Override
    protected void addButton(String text, float x, float y, Runnable action) {
        uiManager.addButton(text, x, y, action);
    }
    protected void addText(String text, SpriteBatch batch, Color color) {
        uiManager.addText(text, text, batch, color);
    }
    protected void showCustomDialog(String title, String message, Window.WindowStyle windowStyle) {
    	uiManager.addDialog(title, message, windowStyle);
    }
    protected abstract void showDialog();

}
