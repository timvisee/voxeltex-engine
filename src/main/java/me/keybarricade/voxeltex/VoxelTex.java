package me.keybarricade.voxeltex;

import me.keybarricade.voxeltex.scene.TestEnvironmentScene;

public class VoxelTex {

    /**
     * Engine name.
     */
    public static final String ENGINE_NAME = "VoxelTex";

    /**
     * Engine version name.
     */
    public static final String ENGINE_VERSION_NAME = "0.1";

    /**
     * Engine version code.
     */
    public static final int ENGINE_VERSION_CODE = 1;

    /**
     * Get the full engine name string, including the version number.
     *
     * @return Engine name string.
     */
    public static String getEngineNameFull() {
        return ENGINE_NAME + " v" + ENGINE_VERSION_NAME;
    }

    /**
     * Run the VoxelTex engine with the test environment.
     *
     * @param args Startup arguments.
     */
    public static void main(String[] args) {
        // Define the engine variable
        VoxelTexEngine engine;

        // Create a VoxelTex engine instance
        engine = new VoxelTexEngine();

        // Set the title
        engine.setTitle(ENGINE_NAME + " v" + ENGINE_VERSION_NAME + " - Test Environment");

        // Initialize the engine
        engine.init();

        // Load the test environment scene
        engine.getSceneManager().loadScene(new TestEnvironmentScene());

        // Start the engine
        engine.start();
    }
}
