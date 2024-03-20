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
import inputOutputManager.InputOutputManager;
import playerControllerManager.PlayerControllerManager;

public class EarthScene extends BasePlanetScene{
	private MapManager mapManager;
	private Random random;
	private Entity player;
	private boolean showDialogFlag = true;
	private boolean dialogOpen = false;
	
	public EarthScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Earth.tmx");

        initializeScene();
	}
	 private void initializeScene() {
		// player entity
		player = entityFactory.createEntity("astronaut.png", 0, 0, 200, false, new Vector2(0, 0), "player", 10000);
		entityManager.add(player);
		
		// flag entity
		Entity flag = entityFactory.createEntity("flag.png", 600, 350, false, "flag");
		entityManager.add(flag);
		entityManager.addCollidableEntity(flag);

        String buttonText = "End";
        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
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
	
	@Override
	public void render(float delta) {
	    super.render(delta);

		// call render method from MapManager to render the current map
		mapManager.getRenderer().setView(mapManager.getCamera());
		mapManager.getRenderer().render();
		// Check collision with building
		cManager.checkCollisionWithObject((Player) player, mapManager);
	    
	    SpriteBatch batch = new SpriteBatch();
	    
	    String text = "Earth";
	    
	    batch.begin();
	    super.addText(text, batch, Color.BLACK);
	    super.planetRender(batch);
	    
	    batch.end();
	    if (showDialogFlag) {
            showDialog();
            showDialogFlag = false;
        }
	    renderStages();
	}



}
