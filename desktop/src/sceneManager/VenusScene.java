package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

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
	private boolean showDialogFlag = true;
	private Entity flag;
    private boolean fireballRendered = false; // boolean to check if fireballs are rendered

	public VenusScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Venus.tmx");
       
        initializeScene();
	}
	 private void initializeScene() {
		// create flag entity
		flag = entityFactory.createEntity("flag.png", 200, 20, false, "flag");
		Random random = new Random();
		
		for (int i = 0; i < 5; i++) {
            float posX = random.nextInt(Gdx.graphics.getWidth());
            float posY = random.nextInt(Gdx.graphics.getHeight());
            
			Entity enemy = entityFactory.createEntity("fireball.png", posX, posY, 4, false, "fireball");
			entityManager.add(enemy);
			entityManager.addCollidableEntity(enemy);
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

	protected void showDialog() {
        Window.WindowStyle windowStyle = new Window.WindowStyle(); 
        BitmapFont font = new BitmapFont();
        windowStyle.titleFont = font; 
        windowStyle.titleFontColor = Color.WHITE; 
        
        // Change this for trivia part
        showCustomDialog("", "Venus is the hottest planet in our solar system\n"
        		+ ", its thick carbon dioxide atmostphere is the cause of this!", windowStyle);
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
	    planetRender(batch);

	    batch.end();
	    if (showDialogFlag) {
            showDialog();
            showDialogFlag = false;
        }
	    renderStages();
	}
	
	@Override
	protected void planetRender(SpriteBatch batch) {
    	performCollisionChecks();
    	
    	if(dialogOpen == false) {
    		updatePlayerPosition(Gdx.graphics.getDeltaTime());
    		
		    // Draw entities after collision detection and player update
		    for (Entity e : entityManager.getEntityList()) {
		    	
		    	//check if entity type is player or flag
		        if (e.getType().equals("player") || e.getType().equals("flag")) {
		            e.setVisible(true); // Ensure visibility is set to true before drawing
		            e.draw(batch);
		        }
		        else if(e.getType().equals("fireball")) { //check if entity is fireball
	        		if (!fireballRendered) {
	        			e.setVisible(true); // set visibility to true if fireballs are not rendered yet
	   		         e.draw(batch);
	   		         aiManager.moveAIControlled();
	        		} else {
	        			if (e.getVisible()) {
	        				aiManager.moveAIControlled();
	        				e.draw(batch);
	        			}
	        		}
	        	}
		       
		        
		        else {
		        	e.setVisible(false);
		        }
		        		        
		    }
		  //if not rendered, set fireballs to be rendered already
	        if (!fireballRendered) {
	        	fireballRendered = true;
	        }
    	}
    }

	@Override
	public void show() {
		super.show();
		if (flag != null) {
			entityManager.add(flag);
			entityManager.addCollidableEntity(flag);
		}
	}

	@Override
	public void hide() {
		super.hide();
		if (flag != null) {
			entityManager.removeFlagEntity(flag);
		}
	}

}
