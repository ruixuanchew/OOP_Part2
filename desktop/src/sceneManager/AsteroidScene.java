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

	public AsteroidScene(SceneManager sceneManager, EntityManager entityManager, PlayerControllerManager pcManager,
            CollisionManager cManager, AIControlManager aiManager ) {
		super(sceneManager, entityManager, pcManager, cManager, aiManager);
		this.entityManager = entityManager;
        this.pcManager = pcManager;
        this.cManager = cManager;
        
        this.cManager = new CollisionManager(sceneManager);
        this.aiManager = aiManager;
        this.sceneManager = sceneManager;

        initializeScene();
	}
	 private void initializeScene() {
//		 this.entity = new TextureObject("asteroids.png", 550, 200, 4, false, false, "asteroid");
//	     entityManager.add(this.entity);
//	     entityManager.addCollidableEntity(this.entity);
	     
	     // To work on individual planets please just keep whichever planet you want to test 
	     //E.g. 
	     // String[] planetNames = {"mercury"};
	     // To test for mercury planet switching between scenes
	     
		 String[] planetNames = {"venus"};
		 for(int i = 0; i < planetNames.length; i++) {
			 String name = planetNames[i];
			 String fileName = planetNames[i] + ".png";
			 int x = MathUtils.random(100, 400);
			 int y = MathUtils.random(150, 300);
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
	 public void setTileMapTexture(Texture tileMapTexture) {
	        super.setTileMapTexture(tileMapTexture);
	        // Do the tile map stuff here 
	 }
	  @Override
	    public void render(float delta) {
		  super.render(delta);

	        SpriteBatch batch = new SpriteBatch(); // Initialize batch here
	        String text = "Space";

	        batch.begin();
		    super.addText(text, batch, Color.WHITE);
		    for (Entity e : entityManager.getEntityList()) {
	        	if(e.getType().equals("player")) {
	        		this.player = (TextureObject) e;
	        		break;
	        	}
	        }
		    
		    // Update player's position based on input or other logic
		    pcManager.update(Gdx.graphics.getDeltaTime());

		    // Check if the player has moved beyond the screen width
		    if (player.getPosX() > Gdx.graphics.getWidth()) {
		        // Move player back to the left side of the screen
		        player.setPosX(-player.getWidth());

		        // Update the positions of other entities accordingly
		        for (Entity e : entityManager.getEntityList()) {
		            // Adjust positions of entities excluding the player
		            if (!e.getType().equals("player")) {
		                // Randomly set x-position outside the screen width
		                e.setPosX(MathUtils.random(100, 400));
		                e.setPosY(MathUtils.random(150, 300));
		            }
		        }
		    }
		    
	        for (TextureObject e : entityManager.getCollidableEntityList()) {
		        if (e.getVisible()) {
		            cManager.checkCollision(player, e, sceneManager);
		        }
		    }

	        // Draw entities after collision detection and player update
		    for (Entity e : entityManager.getEntityList()) {
		        if (e.getType().equals("player") || e.getType().equals("asteroid") || e.getType().equals("mercury") || e.getType().equals("venus") || e.getType().equals("mars") || 
		        		e.getType().equals("jupiter") || e.getType().equals("saturn") || e.getType().equals("uranus") || e.getType().equals("neptune")) {
		            e.setVisible(true); // Ensure visibility is set to true before drawing
		            e.draw(batch);
		        }
		        else {
		        	e.setVisible(false);
		        }
		    }
		    batch.end();
	  }
}
