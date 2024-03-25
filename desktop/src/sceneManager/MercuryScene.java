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

public class MercuryScene extends BasePlanetScene{
	private MapManager mapManager;
	private Random random;
	private boolean showDialogFlag = true;
	private Entity flag;
	private Entity player;
    private boolean rockRendered = false; // boolean to check if rocks are rendered

	public MercuryScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Mercury.tmx"); // load Mercury map from Tiled
		player = entityManager.getPlayer(); // retrieve Player from SceneManager
       
        initializeScene();
	}
	 private void initializeScene() {
		Random random = new Random();
		
		// create falling rocks
		for (int i = 0; i < 5; i++) {
            float posX = random.nextInt(Gdx.graphics.getWidth() + 20);
            float posY = random.nextInt(Gdx.graphics.getHeight());
            
			Entity enemy = entityFactory.createEntity("rock.png", posX, posY, 4, false, "rock");
			entityManager.add(enemy);
			entityManager.addCollidableEntity(enemy);
		}
		
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
        
        showCustomDialog("", "Mercury is the smallest planet in our solar"
        		+ "\nsystem and is heavily cratered from lack of atmosphere protection!", windowStyle);
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
		        else if(e.getType().equals("rock")) { //check if entity is rock 
	        		if (!rockRendered) {
	        			e.setVisible(true); // set visibility to true if rocks are not rendered yet
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
		    
		    //if not rendered, set rocks to be rendered already
	        if (!rockRendered) {
	        	rockRendered = true;
	        }
    	}
    }
	
	// reset scene when game restarts
	@Override 
    protected void resetScene() {
    	System.out.println("Mercury scene resetted!");
    	rockRendered = false;
    	showDialogFlag = true;
    };

}
