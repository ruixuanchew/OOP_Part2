package sceneManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class StartScene extends BaseScene {
    protected StartScene(SceneManager sceneManager) {
        super(sceneManager);

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
        addButton(buttonText, x, y, () -> sceneManager.showGameScene());
    }
    
    // Override abstract method in BaseScene.java
    @Override
    protected Color getBackgroundColor() {
        return new Color(0.2f, 0.6f, 0, 1);
    }

    // Method to calculate the width of the text
    private float calculateTextWidth(String text) {
        BitmapFont font = new BitmapFont();
        GlyphLayout layout = new GlyphLayout(font, text);
        return layout.width;
    }
}
