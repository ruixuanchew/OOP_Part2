package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import inputOutputManager.InputHandler;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public abstract class BaseScene extends ScreenAdapter {
    protected SceneManager sceneManager;
    protected Stage stage;
    protected InputHandler inputHandler;

    public BaseScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        stage = new Stage();
        inputHandler = new InputHandler();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
    	// Set color of screen by getBackgroundColor RGB values
        Gdx.gl.glClearColor(getBackgroundColor().r, getBackgroundColor().g, getBackgroundColor().b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        // Reset cursor back to arrow upon switching scenes
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
    }

    // Abstract method for background color of scene
    protected abstract Color getBackgroundColor();
    
    // Add button for different scenes
    protected void addButton(String text, float x, float y, Runnable action) {
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
    private TextureRegionDrawable buttonBackgroundColor(Color color, int width, int height, int paddingX, int paddingY) {
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
    
    @Override
    public void dispose() {
        stage.dispose();
    }
}
