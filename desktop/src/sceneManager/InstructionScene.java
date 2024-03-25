package sceneManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InstructionScene extends BaseScene {
    private Texture backgroundImage;
    private SpriteBatch batch;
	private MapManager mapManager;
    protected InstructionScene(SceneManager sceneManager) {
        super(sceneManager);
        batch = new SpriteBatch();
        initializeScene();
        
        mapManager = new MapManager();
		mapManager.loadMap("space.tmx");
    }
    
    private void initializeScene() {
    	UIManager uiManager = new UIManager(null, null);
        // Load the start scene background image
        backgroundImage = new Texture(Gdx.files.internal("startscene.png"));

    	// Set the button text
        String buttonText = "GO!";

        // Get the button width and height
        float buttonWidth = calculateTextWidth(buttonText) + 40; // Adjust as needed
        float buttonHeight = 35; // Adjust as needed

        // Calculate the x-coordinate to center the button horizontally
        float x = (Gdx.graphics.getWidth() - buttonWidth) / 2;

        // Set the y-coordinate to position the button near the bottom of the screen
        float y = 30;

        // Add the button
        addButton(buttonText, x, y, () -> sceneManager.setCurrentScene(SceneType.EARTH_SCENE));
	  }

    // Override abstract method in BaseScene.java
    @Override
    protected Color getBackgroundColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        
        mapManager.getRenderer().setView(mapManager.getCamera());
		mapManager.getRenderer().render();
		
        // Draw the start scene background image
        batch.begin();
        // Set the instruction text here
        String text = "Instructions:\n "
        		+ "\n1. Avoid obstacles and maneuver through tough terrain!\n"
        		+ "\n2. Find the planets when travelling through space and go into them!\n"
        		+ "\n3. Reach the flag or rocket to go to the next scene!\n"
        		+ "\n4. Win the game by completing the venus harsh obstacles!\n"
        		+ "\n5. Controls: Arrow Keys, Jump: spacebar";
        // Call addText from super class
		super.addText(text, batch, Color.WHITE);
       
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	    stage.draw();
    }

    // Method to calculate the width of the text
    protected float calculateTextWidth(String text) {
    	BitmapFont font = new BitmapFont();
        return super.calculateTextWidth(text, font);
    }
    
	// reset scene when game restarts
	@Override 
    protected void resetScene() {
    	
    };
}
