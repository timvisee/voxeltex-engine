package me.keybarricade.voxeltex.scene;

import me.keybarricade.voxeltex.VoxelTexEngine;

public class SceneManager {

    /**
     * The engine instance this scene manager is for.
     */
    private VoxelTexEngine engine;

    /**
     * Current loaded scene.
     */
    private Scene scene = null;

    /**
     * Constructor.
     *
     * @param engine VoxelTex engine instance.
     */
    public SceneManager(VoxelTexEngine engine) {
        this.engine = engine;
    }

    /**
     * Get the VoxelTex engine instance.
     *
     * @return Engine instance.
     */
    public VoxelTexEngine getEngine() {
        return engine;
    }

    /**
     * Get the loaded scene.
     *
     * @return Loaded scene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Check whether a scene is loaded.
     *
     * @return True if a scene is loaded, false if not.
     */
    public boolean isSceneLoaded() {
        return this.scene != null;
    }

    /**
     * Load a scene.
     *
     * @param scene Scene to load
     */
    public void loadScene(Scene scene) {
        // Set the loaded scene
        this.scene = scene;

        // Set the scene engine
        scene.setEngine(getEngine());

        // Load the scene
        System.out.println("Loading scene...");
        scene.load();
        System.out.println("Scene loaded successfully!");
    }

    /**
     * Update the scene that is currently loaded.
     */
    public void update() {
        // Make sure a scene is loaded, then update it
        if(isSceneLoaded())
            this.scene.update();
    }

    /**
     * Draw the scene that is currently loaded.
     */
    public void draw() {
        // Make sure a scene is loaded, then update it
        if(isSceneLoaded())
            this.scene.draw();
    }
}
