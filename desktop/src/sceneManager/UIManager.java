package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inputOutputManager.InputHandler;
import java.util.Map;
import java.util.HashMap;

public class UIManager {
	private Stage stage;
	private InputHandler inputHandler;
	private BaseScene baseScene;
    private Map<Integer, Texture> healthTextures;


    public UIManager(Stage stage, BaseScene baseScene) {
        this.stage = stage;
        inputHandler = new InputHandler();
        this.baseScene = baseScene;

        // hash map to store health textures with player health as the key to retrieve textures
        healthTextures = new HashMap<>();
        healthTextures.put(100, new Texture("100health.png"));
        healthTextures.put(90, new Texture("90health.png"));
        healthTextures.put(80, new Texture("80health.png"));
        healthTextures.put(70, new Texture("70health.png"));
        healthTextures.put(60, new Texture("60health.png"));
        healthTextures.put(50, new Texture("50health.png"));
        healthTextures.put(40, new Texture("40health.png"));
        healthTextures.put(30, new Texture("30health.png"));
        healthTextures.put(20, new Texture("20health.png"));
        healthTextures.put(10, new Texture("10health.png"));
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
    public void addText(String text, SpriteBatch batch, Color color) {
    	BitmapFont planetTxt = new BitmapFont();
    	planetTxt.setColor(color);
    	GlyphLayout layout = new GlyphLayout(planetTxt, text);
        float textWidth = layout.width;
        float textHeight = layout.height;

        float screenWidth = Gdx.graphics.getWidth();
        float x = (screenWidth - textWidth) / 2; // Center the text horizontally

        float y = Gdx.graphics.getHeight() - textHeight;
        
    	planetTxt.draw(batch, text, x, y);
    }
    
    public void addDialog(String title, String message, Window.WindowStyle windowStyle) {
    	if (baseScene instanceof BasePlanetScene) {
    	    BasePlanetScene basePlanetScene = (BasePlanetScene) baseScene;
    	    basePlanetScene.setDialogOpen(true);
    	}
    	CustomDialog dialog = new CustomDialog(title,  new MySkin().createSkin(), inputHandler); // Create your CustomDialog instance with appropriate title and windowStyle
        dialog.text(message); // Set the message text
        dialog.button("Close", false);
        
     // Handle the button click event to close the dialog and update dialogOpen state
        dialog.getButtonTable().getCells().peek().getActor().addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
//            	dialog.setDialogOpen(false);
            	if (baseScene instanceof BasePlanetScene) {
            	    BasePlanetScene basePlanetScene = (BasePlanetScene) baseScene;
            	    basePlanetScene.setDialogOpen(false);
            	}
            }
        });
        
        dialog.show(stage); // Show the dialog on the stage
        dialog.setBackground(buttonBackgroundColor(Color.BLACK, 1000, 1500, 20, 20));
        stage.addActor(dialog);
    }
    
    public float calculateTextWidth(String text, BitmapFont font) {
         GlyphLayout layout = new GlyphLayout(font, text);
         return layout.width;
    }

    // get health texture from the hash map and display it on screen according to the player's health
    public void displayHealth(SpriteBatch batch, int health) {
        Texture healthTexture = healthTextures.get(health);
        if (healthTexture != null) {
            batch.draw(healthTexture, 10, Gdx.graphics.getHeight() - 35, 110, 30);
        }
    }
}
