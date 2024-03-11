package com.mygdx.myhealthygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import aiControlManager.AIControlManager;
import collisionManager.CollisionManager;
import entityManager.EntityManager;
import entityManager.EntityFactory;
import inputOutputManager.InputOutputManager;
import playerControllerManager.PlayerControllerManager;
import sceneManager.SceneManager;
import simulationLifecycleManager.SimulationLifecycleManager;

public class GameMaster extends Game {
    private InputOutputManager ioManager;
    private SceneManager sceneManager;
    private PlayerControllerManager pcManager;
    private EntityManager em;
    private EntityFactory ef;
    private SimulationLifecycleManager slManager;
    private AIControlManager aiManager;
    private CollisionManager cManager;

    @Override
    public void create() {
        initializeManagers();
        initializeScenes();
        startGame();
    }

    private void initializeManagers() {
        // Initialize the InputOutputManager and Error Handler
        ioManager = new InputOutputManager();

        // Initialize the EntityManager
        em = new EntityManager();

        // Initialize the EntityManager
        ef = new EntityFactory();

        // Initialize the PlayerControllerManager
        pcManager = new PlayerControllerManager(em);

        // Initialize the AIControlManager
        aiManager = new AIControlManager(em);

        // Initialize the SceneManager
        sceneManager = new SceneManager(this);
        
        // Initialize the CollisionManager
        cManager = new CollisionManager(sceneManager, pcManager);

        // Initialize the SimulationLifecycleManager
        slManager = new SimulationLifecycleManager(sceneManager, em);
    }

    private void initializeScenes() {
        // Create and set up scenes
        sceneManager.initializeScenes(em, ef, pcManager, cManager, aiManager, slManager, ioManager);
    }

    private void startGame() {
        // Start with start scene
        try {
            slManager.startGame();
        } catch (Exception e) {
            slManager.getErrorHandler().handleException(e, "Failed to start the game");
        }
    }

    @Override
    public void render() {
        super.render();
        ioManager.getBgMusic().play();
    }

    @Override
    public void dispose() {
        sceneManager.dispose();
    }
}

