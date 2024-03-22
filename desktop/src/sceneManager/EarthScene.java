package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;

import entityManager.Player;
import inputOutputManager.InputOutputManager;
import playerControllerManager.PlayerControllerManager;

public class EarthScene extends BasePlanetScene{
	private MapManager mapManager;
	private Random random;
	private Entity player;
	private Entity flag;
	private boolean showDialogFlag = true;
	private boolean dialogOpen = false;
	private boolean isSecondMapLoaded = false;

	public EarthScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Earth.tmx"); // load Earth map from Tiled

        initializeScene();
	}
	 private void initializeScene() {
		// player entity
		player = entityFactory.createEntity("astronaut.png", 0, 0, 200, false, new Vector2(0, 0), "player", 100);
		entityManager.add(player); // add player entity to the entity list
		 /* set player in SceneManager to this created player entity. This allows other
		 scenes to retrieve this particular player entity using getPlayer */
		sceneManager.setPlayer(player);

		// flag entity
		//flag = entityFactory.createEntity("flag.png", 600, 350, false, "flag");

        String buttonText = "End";
        addButton(buttonText, Gdx.graphics.getWidth() - 125, Gdx.graphics.getHeight() - 50,
                () -> sceneManager.showEndScene());
    }

	@Override
	protected Color getBackgroundColor() {
		// TODO Auto-generated method stub
		return new Color(0, 0.5f, 0, 1);
	}
	protected void showDialog() {
        Window.WindowStyle windowStyle = new Window.WindowStyle(); 
        BitmapFont font = new BitmapFont();
        windowStyle.titleFont = font; 
        windowStyle.titleFontColor = Color.WHITE; 
        
        // Change this for trivia part
        showCustomDialog("", "Earth is our home planet and uniquely"
        		+ "\nsupports life due to water and protective atmosphere.", windowStyle);
    }

	// check which Earth map player is on
	public boolean isSecondMapLoaded() {
		return isSecondMapLoaded;
	}

	// for player to load back into first map when colliding with lava in second map
	public void loadFirstMap() {
		mapManager.loadMap("Earth.tmx");
		isSecondMapLoaded = false;
	}

	// switch to second Earth map when player exits first Earth map
	public void switchMap() {
		if (player.getPosX() > 620) {
			// Load the second map if it's not already loaded
			if (!isSecondMapLoaded) {
				mapManager.loadMap("Earth2.tmx"); // Load the second map
				isSecondMapLoaded = true; // Set the flag to true
				// Create the flag entity only when Earth2.tmx is loaded and the flag entity does not exist yet
				if (flag == null) {
					flag = entityFactory.createEntity("flag.png", 570, 400, false, "flag");
					entityManager.add(flag);
					entityManager.addCollidableEntity(flag);
				}
			}
		}
	}
	
	@Override
	public void render(float delta) {
	    super.render(delta);

	    entityManager.movePlayerControlled(pcManager, delta);
	    
		// call render method from MapManager to render the current map
		mapManager.getRenderer().setView(mapManager.getCamera());
		mapManager.getRenderer().render();
		// Check collision with building
		cManager.checkCollisionWithObject((Player) player, mapManager);
		switchMap(); // call map switching function when player exits bounds of first Earth map

	    SpriteBatch batch = new SpriteBatch();
	    
	    String text = "Earth";
	    
	    batch.begin();
	    super.addText(text, batch, Color.BLACK);
	    super.planetRender(batch);
		// call UI Manager method to display player health on screen
		uiManager.displayHealth(batch, ((Player) player).getHealth());
	    batch.end();

	    if (showDialogFlag) {
            showDialog();
            showDialogFlag = false;
        }
	    renderStages();
	}

	@Override
	public void hide() { // to remove flag from first Earth map
		super.hide();
		if (flag != null && !isSecondMapLoaded) {
			entityManager.removeFlagEntity(flag);
			flag = null; // Set flag to null after removing it
		}
	}
}
