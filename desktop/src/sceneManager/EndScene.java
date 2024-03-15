package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import simulationLifecycleManager.SimulationLifecycleManager;

public class EndScene extends BaseScene {
    private SimulationLifecycleManager slManager;

    protected EndScene(SceneManager sceneManager, SimulationLifecycleManager slManager) {
        super(sceneManager);
        this.slManager = slManager;

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

    // Method to calculate the maximum width
    private float calculateMaxTextWidth(String[] texts) {
        float maxWidth = 0; // Initialize maxWidth
        BitmapFont font = new BitmapFont();
        for (String text : texts) { // for through number of text in buttons
            GlyphLayout layout = new GlyphLayout(font, text);
            float textWidth = layout.width;
            if (textWidth > maxWidth) {
                maxWidth = textWidth;
            }
        }
        return maxWidth; 
    }
}
