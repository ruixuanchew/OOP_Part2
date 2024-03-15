package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import inputOutputManager.InputHandler;

public class CustomDialog extends Dialog {
    private Table contentTable; 
    private InputHandler inputHandler;
    
    public CustomDialog(String title, Skin skin, InputHandler inputHandler) {
        super(title, new MySkin().createSkin());
        this.inputHandler = inputHandler;
        contentTable = getContentTable(); // Initialize contentTable
        contentTable.defaults().pad(20f); 
    }
    
    @Override
    public Dialog text(String text) {
        // Set the text content of the dialog
        contentTable.add(text).pad(20f);
        return null;
    }

    @Override
    public Dialog button(String buttonText) {
        // Create a button with the specified text
        TextButton button = new TextButton(buttonText, getSkin());
        return this;
    }
    
    @Override
    protected void result(Object object) {
        if ((Boolean) object) {
            Gdx.app.exit();
        }else{
        	hide();
        }
    }
    
    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
        if (contentTable != null) { 
            // Set the background to cover the entire dialog
            contentTable.setBackground(background);
        }
    }
    
    @Override
    public Dialog show(Stage stage) {
        return super.show(stage);
    }

}
