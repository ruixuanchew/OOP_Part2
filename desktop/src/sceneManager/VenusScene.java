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
import entityManager.Player;
import playerControllerManager.PlayerControllerManager;

public class VenusScene extends BasePlanetScene{
	private MapManager mapManager;
	private Random random;
	private boolean showDialogFlag = true;
	private Entity flag;
	private Entity player;
    private boolean fireballRendered = false; // boolean to check if fireballs are rendered

	public VenusScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Venus.tmx"); // load Venus map from Tiled
		player = entityManager.getPlayer(); // retrieve Player from SceneManager
       
        initializeScene();
	}
	 private void initializeScene() {
		Random random = new Random();
		
		// create fireball entities
		for (int i = 0; i < 5; i++) {
            float posX = random.nextInt(Gdx.graphics.getWidth());
            float posY = random.nextInt(Gdx.graphics.getHeight());
            
			Entity enemy = entityFactory.createEntity("fireball.png", posX, posY, 4, false, "fireball");
			entityManager.add(enemy);
			entityManager.addCollidableEntity(enemy);
		}
	    //entityManager.addCollidableEntity(this.entity);

	    String buttonText = "End";
	    addButton(buttonText, Gdx.graphics.getWidth() - 125, Gdx.graphics.getHeight() - 50,
	             () -> sceneManager.setCurrentScene(SceneType.END_SCENE));
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
	    super.addText(text, batch, Color.WHITE);
		// call UI Manager method to display player health on screen
		uiManager.displayHealth(batch, ((Player) player).getHealth());
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
	   		      entityManager.moveAIDown(aiManager);//Calling Movement from EntityManager, logic from AIManager
	        		} else {
	        			if (e.getVisible()) {
	        				entityManager.moveAIDown(aiManager);//Calling Movement from EntityManager, logic from AIManager
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
	
	// reset scene when game restarts
	@Override 
    protected void resetScene() {
    	System.out.println("Venus scene resetted!");
    	fireballRendered = false;
    	showDialogFlag = true;
    };
}
