package inputOutputManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * InputHandler is used to handle input from the user. It provides methods to check if the left,
 * right and space keys are pressed. It also provides a method to handle button events.
 */

public class InputHandler { // check if certain keys are being pressed by the user
    public boolean LeftKey() {
        return Gdx.input.isKeyPressed(Keys.LEFT);
    }

    public boolean RightKey() {
        return Gdx.input.isKeyPressed(Keys.RIGHT);
    }

    public boolean SpaceKey() {
        return Gdx.input.isKeyPressed(Keys.SPACE);
    }
    
    public boolean UpKey() {
        return Gdx.input.isKeyPressed(Keys.UP);
    }
    
    public boolean DownKey() {
        return Gdx.input.isKeyPressed(Keys.DOWN);
    }
    
    // Handle buttons from sceneManager
    public void handleButtonEvents(TextButton button, Runnable onClick) {
        button.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	onClick.run();
                return true;
            }
        });
    }
}