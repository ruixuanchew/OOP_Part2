package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;
import playerControllerManager.PlayerControllerManager;
import entityManager.Entity;
import entityManager.EntityManager;

public class MercuryScene extends BasePlanetScene {
	private MapManager mapManager;
	private boolean showDialogFlag = true;
	private boolean dialogOpen = false;

    public MercuryScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
    	super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager, -9.8f);

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("Mercury.tmx");
        
        initializeScene();
	}
	 private void initializeScene() {
	        String buttonText = "End";
	        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
	                () -> sceneManager.showEndScene());
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
	    

	    batch.end();
        if (showDialogFlag) {
            showDialog();
            showDialogFlag = false; // Prevent continuous showing of dialog
        }
        
	    renderStages(delta);
	    
	}
}
