package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.*;
import playerControllerManager.PlayerControllerManager;
import entityManager.Entity;
import entityManager.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class MercuryScene extends BasePlanetScene {
	private MapManager mapManager;
	private boolean showDialogFlag = true;
	private boolean dialogOpen = false;
	private Entity flag;
	private Entity player;

    public MercuryScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
    	super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Mercury.tmx"); // load Mercury map from Tiled
		player = sceneManager.getPlayer(); // retrieve Player from SceneManager
        
        initializeScene();
	}
	 private void initializeScene() {
		 String buttonText = "End";
		 addButton(buttonText, Gdx.graphics.getWidth() - 125, Gdx.graphics.getHeight() - 50,
				 () -> sceneManager.setCurrentScene(SceneType.END_SCENE));
	  }

	@Override
	protected Color getBackgroundColor() {
		return new Color(0.2f, 0.2f, 0.2f, 1);
	}
	protected void showDialog() {
        Window.WindowStyle windowStyle = new Window.WindowStyle(); // Create your WindowStyle instance
        BitmapFont font = new BitmapFont(); // This is your font for the labels
        windowStyle.titleFont = font; // Set the font for the window title
        windowStyle.titleFontColor = Color.WHITE; // Set the font color for the window title

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
	    
	    super.planetRender(batch);
	    super.addText(text, batch, Color.BLACK);
		// call UI Manager method to display player health on screen
		uiManager.displayHealth(batch, ((Player) player).getHealth());
	    batch.end();

        if (showDialogFlag) {
            showDialog();
            showDialogFlag = false; // Prevent continuous showing of dialog
        }
        
	    renderStages();
	}
}
