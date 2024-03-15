package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MySkin {

	 public Skin createSkin() {
		 Skin skin = new Skin();
	     BitmapFont font = new BitmapFont();

	     // Define button style
	     TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
	     buttonStyle.font = font; // Set your font
	     buttonStyle.fontColor = Color.WHITE; // Set font color
	     skin.add("default", buttonStyle); // Add button style to the skin with name "default"

	     // Define window style
	     Window.WindowStyle windowStyle = new Window.WindowStyle(font, Color.WHITE, null);
	     skin.add("default", windowStyle); // Add window style to the skin with name "default"
	        
	     getBackground();
	     // Define label style
	     Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
	     skin.add("default", labelStyle); // Add label style to the skin with name "default"

	     return skin;
	    }
	 
	 public TextureRegionDrawable getBackground() {
	     Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
	     pixmap.setColor(Color.WHITE);

	     Texture texture = new Texture(pixmap);
	     TextureRegionDrawable drawable = new TextureRegionDrawable(texture);
	        
	     pixmap.dispose();

	     return drawable; // Return back to addButton
		 
	 }
}