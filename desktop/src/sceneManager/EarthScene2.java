package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;
import entityManager.Player;
import playerControllerManager.PlayerControllerManager;
import sceneManager.BasePlanetScene;
import sceneManager.SceneManager;

public class EarthScene2 extends BasePlanetScene {
    private Entity player;
    private Entity flag;
    private MapManager mapManager;
    private boolean showDialogFlag = true;

    public EarthScene2(SceneManager sceneManager, EntityManager entityManager, EntityFactory entityFactory, PlayerControllerManager pcManager,
                       CollisionManager cManager, AIControlManager aiManager) {
        super(sceneManager, entityManager, entityFactory, pcManager, cManager, aiManager);

        // call loadMap method from MapManager to load the current map
        mapManager = new MapManager();
        mapManager.loadMap("Earth2.tmx"); // load Space map from Tiled
        player = entityManager.getPlayer(); // retrieve Player from SceneManager

        // Initialize the scene
        initializeScene();
    }

    private void initializeScene() {
        String buttonText = "End";
        addButton(buttonText, Gdx.graphics.getWidth() - 125, Gdx.graphics.getHeight() - 50,
                () -> sceneManager.setCurrentScene(SceneType.END_SCENE));
    }

    @Override
    protected Color getBackgroundColor() {
        return new Color(0, 0.5f, 0, 1); // Set the background color of the scene
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Update and draw entities
        entityManager.movePlayerControlled(pcManager, delta);

        // call render method from MapManager to render the current map
        mapManager.getRenderer().setView(mapManager.getCamera());
        mapManager.getRenderer().render();
        // Check collision with building
        cManager.checkCollisionWithTiledMap((Player) player, mapManager);

        SpriteBatch batch = new SpriteBatch();
        String text = "Earth";

        batch.begin();
        super.addText(text, batch, Color.BLACK);
        super.planetRender(batch);
        player.draw(batch);
        uiManager.displayHealth(batch, ((Player) player).getHealth()); // Display player health on screen
        batch.end();

        if (showDialogFlag) {
            showDialog();
            showDialogFlag = false;
        }

        renderStages();
    }

    @Override
    protected void showDialog() {
    }
    
	// reset scene when game restarts
	@Override 
    protected void resetScene() {
    	
    };
}