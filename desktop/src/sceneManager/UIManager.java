package sceneManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inputOutputManager.InputHandler;

public class UIManager {
	private Stage stage;
	private InputHandler inputHandler;

    public UIManager(Stage stage) {
        this.stage = stage;
        inputHandler = new InputHandler();
    }

    public void addButton(String text, float x, float y, Runnable action) {
    	int fontSize = 12; // Define font size of text
        BitmapFont font = new BitmapFont();
        // Set font size of text
        font.getData().setScale(fontSize / font.getCapHeight());

        // To measure the text bounds (width and height)
        GlyphLayout layout = new GlyphLayout(font, text);

        // Get text width and height
        float textWidth = layout.width;
        float textHeight = layout.height;

        // Add padding around the text
        float paddingX = 20f;
        float paddingY = 10f; 

        // Calculate button width and height based on text size and padding
        float buttonWidth = textWidth + 2 * paddingX;
        float buttonHeight = textHeight + 2 * paddingY;
        
        // Call function to draw color of background
        TextureRegionDrawable drawable = buttonBackgroundColor(Color.YELLOW, (int) buttonWidth, (int) buttonHeight, (int) paddingX, (int) paddingY);
        
        // Initialize button style
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font; 
        buttonStyle.fontColor = Color.BLACK;

        TextButton button = new TextButton(text, buttonStyle);
        button.setSize(buttonWidth + 20, buttonHeight + 15); // Set the button size
        button.getStyle().up = drawable; // Set the background color to color 
        button.setPosition(x, y); 
        inputHandler.handleButtonEvents(button, action); // Pass the Runnable action

        stage.addActor(button);
    }
 // Method to create a background color for text button
    public TextureRegionDrawable buttonBackgroundColor(Color color, int width, int height, int paddingX, int paddingY) {
    	// Use pixmap to create the background color for button
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        // Set color of background based on color params
        pixmap.setColor(color);

        // Fill the pixmap rectangle with the color
        // Defining paddings for button
        pixmap.fillRectangle(paddingX, paddingY, width - 2 * paddingX, height - 2 * paddingY);

        // Create the texture region drawable
        Texture texture = new Texture(pixmap);
        TextureRegionDrawable drawable = new TextureRegionDrawable(texture);
        
        pixmap.dispose();

        return drawable; // Return back to addButton
    }
}
