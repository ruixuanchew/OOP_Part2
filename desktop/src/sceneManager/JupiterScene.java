package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;
import playerControllerManager.PlayerControllerManager;

public class JupiterScene extends BasePlanetScene{
	private boolean showDialogFlag = true;
	
	public JupiterScene(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory,PlayerControllerManager pcManager,
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
		return new Color(0.7f, 0.6f, 0.5f, 1);
	}
	protected void showDialog() {
        Window.WindowStyle windowStyle = new Window.WindowStyle(); 
        BitmapFont font = new BitmapFont();
        windowStyle.titleFont = font; 
        windowStyle.titleFontColor = Color.WHITE; 
        
        // Change this for trivia part
        showCustomDialog("", "Jupiter is the largest planet in our solar system\n"
        		+ ", it has a strong magnetic field and strong winds!", windowStyle);
    }
	
	@Override
	public void render(float delta) {
	    super.render(delta);
	    
	    SpriteBatch batch = new SpriteBatch();
	    
	    String text = "Jupiter";
	    
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
