package sceneManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import entityManager.EntityManager;
import entityManager.TextureObject;
import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import playerControllerManager.PlayerControllerManager;

public class GameScene extends BaseScene {
    private EntityManager entityManager;
    private PlayerControllerManager pcManager;
    private CollisionManager cManager;
    private AIControlManager aiManager;
    private TextureObject player;
    private TextureObject entity;

    // Constructor with dependencies injected
    protected GameScene(SceneManager sceneManager, EntityManager entityManager, PlayerControllerManager pcManager,
                        CollisionManager cManager, AIControlManager aiManager) {
        super(sceneManager);
        this.entityManager = entityManager;
        this.pcManager = pcManager;
        this.cManager = cManager;
        this.aiManager = aiManager;

        initializeScene();
    }

    private void initializeScene() {
        this.player = new TextureObject("bucket.png", 150, 150, 200, true, new Vector2(0, 0));
        entityManager.add(this.player);

        this.entity = new TextureObject("droplet.png", 150, 150, 4, false);
        entityManager.add(this.entity);
        entityManager.addCollidableEntity(this.entity );

        String buttonText = "End";
        addButton(buttonText, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50,
                () -> sceneManager.showEndScene());
    }

    @Override
    protected Color getBackgroundColor() {
        return new Color(0, 0, 0.2f, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        SpriteBatch batch = new SpriteBatch(); // Initialize batch here
        batch.begin();

        for (TextureObject e : entityManager.getCollidableEntityList()) {
            cManager.checkCollision(player, e); // Assuming player is declared elsewhere
        }
        pcManager.update(Gdx.graphics.getDeltaTime());
        entityManager.moveAIControlled(aiManager);

        entityManager.draw(batch);

        batch.end();
    }
}
