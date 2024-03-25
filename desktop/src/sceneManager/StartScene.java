package sceneManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScene extends BaseScene {
    private Texture backgroundImage;
    private SpriteBatch batch;
    protected StartScene(SceneManager sceneManager) {
        super(sceneManager);
        batch = new SpriteBatch();
        initializeScene();
    }
    
    private void initializeScene() {
        // Load the start scene background image
        backgroundImage = new Texture(Gdx.files.internal("startscene.png"));

    	// Set the button text
        String buttonText = "Start";

        // Get the button width and height
        float buttonWidth = calculateTextWidth(buttonText) + 40; // Adjust as needed
        float buttonHeight = 35; // Adjust as needed

        // Calculate the x-coordinate to center the button horizontally
        float x = (Gdx.graphics.getWidth() - buttonWidth) / 2;

        // Set the y-coordinate to position the button near the bottom of the screen
        float y = Gdx.graphics.getHeight() / 2;

        // Add the button
        addButton(buttonText, x, y, () -> sceneManager.setCurrentScene(SceneType.INSTRUCTION_SCENE));
	  }

    // Override abstract method in BaseScene.java
    @Override
    protected Color getBackgroundColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Draw the start scene background image
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

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
