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

import java.util.ArrayList;
import java.util.List;


public abstract class BasePlanetScene extends BaseScene {
	protected EntityManager entityManager;
	protected EntityFactory entityFactory;
	protected PlayerControllerManager pcManager;
	protected CollisionManager cManager;
	protected AIControlManager aiManager;
    private int screenWidth = Gdx.graphics.getWidth();
    protected int screenSwitchCounter = 0;
    protected int currentScene = -1; // counter to know when to render scene
    protected boolean dialogOpen = true;
    protected UIManager uiManager;

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
	protected void performCollisionChecks() {
		List<Entity> entityListCopy = new ArrayList<>(entityManager.getEntityList());
		for (Entity e : entityListCopy) {
			if(e.getType().equals("player")) {
				this.player = (Player) e;
				break;
			}
		}
		// Check collisions before drawing entities
		List<Entity> collidableEntityListCopy = new ArrayList<>(entityManager.getCollidableEntityList());
		for (Entity e : collidableEntityListCopy) {
			if (e.getVisible()) {
				cManager.checkCollision(player, e);
			}
		}
	}
	protected void updatePlayerPosition(float deltaTime) {
		// Update player's position based on input or other logic
	    // pcManager.update(Gdx.graphics.getDeltaTime());
		entityManager.movePlayerControlled(pcManager, deltaTime);

	 // Check if the player has moved beyond the screen width
	    if (player.getPosX() > Gdx.graphics.getWidth()) {
	        screenSwitchCounter++;
	        // Move player back to the left side of the screen
	        player.setPosX(-player.getWidth());

	    }
	}
	protected void spaceRender(SpriteBatch batch) {
		performCollisionChecks();
		updatePlayerPosition(Gdx.graphics.getDeltaTime());
	    
	    // Draw entities after collision detection and player update
	    for (Entity e : entityManager.getEntityList()) {
	        if (e instanceof Player) {
	            e.setVisible(true); // Ensure visibility is set to true before drawing
	            e.draw(batch);
	        } else if (e.getType().equals("mercury") || e.getType().equals("venus")) {
	        	e.setVisible(false);
	            if (screenSwitchCounter == 2 && e.getType().equals("mercury")) {
	                e.setVisible(true);
	                e.draw(batch);
	            }
	            else if(screenSwitchCounter == 4 && e.getType().equals("venus")) {
	            	e.setVisible(true);
	                e.draw(batch);
	                // pcManager.setEndPlayerPosition();
					player.setEndPlayerPosition();
	            }
	        }
	        else if(e.getType().equals("asteroid")){
	        	// check if current scene is the same as switch counter
	        	if (currentScene != screenSwitchCounter) {
	        		// To make asteroid AI controlled
	        		entityManager.moveAIDiagonal(aiManager);
		        	e.setVisible(true);
	                e.draw(batch);
	        	} else {
	        	if (e.getVisible()) {
	        		entityManager.moveAIDiagonal(aiManager);
	        		e.draw(batch);
	        		}
	        	}
	        }
	        else {
	            e.setVisible(false);
	        }
	    }
	    
	    if (currentScene != screenSwitchCounter) {
	    	currentScene = screenSwitchCounter;
	    }
	}

    protected void planetRender(SpriteBatch batch) {
    	performCollisionChecks();
    	
    	if(dialogOpen == false) {
    		updatePlayerPosition(Gdx.graphics.getDeltaTime());
    	
	    // Draw entities after collision detection and player update
	    for (Entity e : entityManager.getEntityList()) {
	    	
	        if (e.getType().equals("player") || e.getType().equals("flag")) {
	            e.setVisible(true); // Ensure visibility is set to true before drawing
	            e.draw(batch);
	        }
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
    public boolean getDialogOpen() {
    	System.out.print(dialogOpen);
    	return dialogOpen;
    }
    public void setDialogOpen(boolean dialogOpen) {
    	this.dialogOpen = dialogOpen;
    }
    // UI elements specific to planets only
    @Override
    protected void addButton(String text, float x, float y, Runnable action) {
        uiManager.addButton(text, x, y, action);
    }
    protected void addText(String text, SpriteBatch batch, Color color) {
        uiManager.addText(text, batch, color);
    }
    protected int getScreenSwitchCounter() {
        return this.screenSwitchCounter;
    }
    public void setScreenSwitchCounter(int screenSwitchCounter) {
        this.screenSwitchCounter = screenSwitchCounter;
    }
    protected void showCustomDialog(String title, String message, Window.WindowStyle windowStyle) {
    	uiManager.addDialog(title, message, windowStyle);
    }
    
	// reset scene when game restarts 
    protected abstract void resetScene();
    
    protected abstract void showDialog();

}
