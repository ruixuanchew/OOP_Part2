package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import simulationLifecycleManager.SimulationLifecycleManager;

public class EndScene extends BaseScene {
    private SimulationLifecycleManager slManager;
    private Texture backgroundImage;
    private SpriteBatch batch;

    protected EndScene(SceneManager sceneManager, SimulationLifecycleManager slManager) {
        super(sceneManager);
        this.slManager = slManager;
        batch = new SpriteBatch();
        
        initializeScene();

    }
    private void initializeScene() {
        // Load the end scene background image
        backgroundImage = new Texture(Gdx.files.internal("endscene.png"));

    	 // Define the button actions in a list
        Runnable[] buttonActions = {
            () -> this.slManager.endGame(),
            () -> this.slManager.restartGame()
        };

        // Set the button text in array
        String[] buttonTexts = {"Exit", "Restart"};

        // Calculate button width and height
        float buttonWidth = calculateMaxTextWidth(buttonTexts) + 40;
        float buttonHeight = 35; 

        // Calculate initial y-coordinate for the first button
        float initialY = Gdx.graphics.getHeight() / 2 + (buttonHeight / 2) + 30;

        // Loop through buttons with different y-coordinates and actions
        for (int i = 0; i < buttonTexts.length; i++) {
            float y = initialY - (i * (buttonHeight + 20)); // Adjust y spacing between buttons
            // Call addbuttons to set buttons
            addButton(buttonTexts[i], (Gdx.graphics.getWidth() - buttonWidth) / 2, y, buttonActions[i]);
        }
	 }
    
    // Override abstract method in BaseScene.java
    @Override
    protected Color getBackgroundColor() {
        return new Color(0.8f, 0, 0, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Draw the end scene background image
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.draw();
    }

    // Method to calculate the maximum width
    private float calculateMaxTextWidth(String[] texts) {
        float maxWidth = 0; // Initialize maxWidth
        BitmapFont font = new BitmapFont();
        for (String text : texts) { // for through number of text in buttons
        	maxWidth = super.calculateTextWidth(text, font);
        }
		return maxWidth;
    }
    
	// reset scene when game restarts
	@Override 
    protected void resetScene() {
    	
    };
}
