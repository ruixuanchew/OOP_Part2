package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import aiControlManager.AIControlManager;
import entityManager.TextureObject;
import playerControllerManager.PlayerControllerManager;
import entityManager.Entity;
import entityManager.EntityManager;
import collisionManager.CollisionManager;

public class AsteroidScene extends BasePlanetScene{
	private EntityManager entityManager;
    private PlayerControllerManager pcManager;
    private CollisionManager cManager;
    private AIControlManager aiManager;
    private SceneManager sceneManager;
    private TextureObject player;
    private TextureObject entity;
    private int screenWidth = Gdx.graphics.getWidth();
	private MapManager mapManager;
	private int screenSwitchCounter = 0;

	public AsteroidScene(SceneManager sceneManager, EntityManager entityManager, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager ) {
		super(sceneManager, entityManager, pcManager, cManager, aiManager);
		this.entityManager = entityManager;
        this.pcManager = pcManager;
        this.cManager = cManager;
        
        this.cManager = new CollisionManager(sceneManager);
        this.aiManager = aiManager;
        this.sceneManager = sceneManager;

		// call loadMap method from MapManager to load the current map
		mapManager = new MapManager();
		mapManager.loadMap("space.tmx");

        initializeScene();
	}
	 private void initializeScene() {
		 int x = MathUtils.random(100, 400);
		 int y = MathUtils.random(150, 300);
		 this.entity = new TextureObject("asteroids.png", x, y, 2, false, false, "asteroid");
	     entityManager.add(this.entity);
	     entityManager.addCollidableEntity(this.entity);
	     
		 String[] planetNames = {"mercury","venus","mars","jupiter","saturn","uranus","neptune"};
		 for(int i = 0; i < planetNames.length; i++) {
			 String name = planetNames[i];
			 String fileName = planetNames[i] + ".png";
			 TextureObject planet = new TextureObject(fileName, x, y, 4, false, false, name);
			 entityManager.add(planet);
			 entityManager.addCollidableEntity(planet);
		 }
	        
	        String buttonText = "End";
	        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
	                () -> sceneManager.showEndScene());
	  }

	@Override
	protected Color getBackgroundColor() {
		return new Color(0, 0, 0, 1);
	}

	@Override
	protected Color getMapBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	  @Override
	    public void render(float delta) {
		  super.render(delta);

		  // call render method from MapManager to render the current map
		  mapManager.getRenderer().setView(mapManager.getCamera());
		  mapManager.getRenderer().render();

	        SpriteBatch batch = new SpriteBatch(); // Initialize batch here
	        String text = "Space";

	        batch.begin();
		    super.addText(text, batch, Color.WHITE);
		    super.spaceRender(batch);
		    batch.end();
	  }
}
