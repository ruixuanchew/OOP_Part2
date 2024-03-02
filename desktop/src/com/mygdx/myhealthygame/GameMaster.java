package com.mygdx.myhealthygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.EntityManager;
import entityManager.TextureObject;
import inputOutputManager.InputOutputManager;
import playerControllerManager.PlayerControllerManager;
import sceneManager.SceneManager;
import simulationLifecycleManager.SimulationLifecycleManager;

public class GameMaster extends Game {
    private InputOutputManager ioManager;
    private SceneManager sceneManager;
    private PlayerControllerManager pcManager;
    private EntityManager em;
    private SimulationLifecycleManager slManager;
    private AIControlManager aiManager;
    private CollisionManager cManager;
    private TextureObject player;

    @Override
    public void create() {
        // Initialize the InputOutputManager and Error Handler
        ioManager = new InputOutputManager();

        // Initialize the EntityManager
        em = new EntityManager();

        // Initialize the PlayerControllerManager
        pcManager = new PlayerControllerManager(em);

        // Initialize the AIControlManager
        aiManager = new AIControlManager(em);

        // Initialize the CollisionManager
        cManager = new CollisionManager();

        // Initialize the SceneManager
        sceneManager = new SceneManager(this);

        // Initialize the SimulationLifecycleManager
        slManager = new SimulationLifecycleManager(sceneManager, em);

        // Initialize scenes
        initializeScenes();

        // Start with start scene
        try {
            slManager.startGame();
        } catch (Exception e) {
            slManager.getErrorHandler().handleException(e, "Failed to start the game");
        }
    }

    private void initializeScenes() {
        // Create and set up scenes
        sceneManager.initializeScenes(em, pcManager, cManager, aiManager, slManager);
    }

    @Override
    public void render() {
        super.render();
        ioManager.getBgMusic().play();
        ioManager.getBgMusic().setVolume(0.2f);
    }

    @Override
    public void dispose() {
        sceneManager.dispose();
    }
}
