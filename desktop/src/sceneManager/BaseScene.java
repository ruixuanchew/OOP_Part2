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
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import inputOutputManager.InputHandler;
import playerControllerManager.PlayerControllerManager;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.Entity;
import entityManager.EntityManager;
import entityManager.EntityFactory;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BaseScene extends ScreenAdapter {
    protected SceneManager sceneManager;
    protected Stage stage;
    protected InputHandler inputHandler;
    protected Texture tileMapTexture;
    private EntityManager entityManager;
    private PlayerControllerManager pcManager;
    private CollisionManager cManager;
    private AIControlManager aiManager;
    private UIManager uiManager;
    protected SpriteBatch batch;
    private int screenWidth = Gdx.graphics.getWidth();

    public BaseScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        stage = new Stage();
        inputHandler = new InputHandler();
        uiManager = new UIManager(stage, this);
        batch = new SpriteBatch();
        this.cManager = new CollisionManager(sceneManager, pcManager, entityManager);
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
    
    // Add common button for different scenes
    protected void addButton(String text, float x, float y, Runnable action) {
        uiManager.addButton(text, x, y, action);
    }
  
    @Override
    public void dispose() {
        stage.dispose();
    }

}
