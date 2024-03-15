package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;
import playerControllerManager.PlayerControllerManager;

public class MarsScene extends BasePlanetScene{
	private boolean showDialogFlag = true;

	public MarsScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager) {
		super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);
        
        initializeScene();
	}
	private void initializeScene() {
        String buttonText = "End";
        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
                () -> sceneManager.showEndScene());
    }
	

	@Override
	protected Color getBackgroundColor() {
		return new Color(0.8f, 0.3f, 0.2f, 1);

	}
	protected void showDialog() {
        Window.WindowStyle windowStyle = new Window.WindowStyle(); 
        BitmapFont font = new BitmapFont();
        windowStyle.titleFont = font; 
        windowStyle.titleFontColor = Color.WHITE; 
        
        // Change this for trivia part
        showCustomDialog("", "Mars has the largest volcano \n"
        		+ "in our solar system, Olympus Mons!", windowStyle);
    }

	@Override
	public void render(float delta) {
	    super.render(delta);
	    
	    SpriteBatch batch = new SpriteBatch();
	    
	    String text = "Mars";
	    
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
