package me.keybarricade;

import me.keybarricade.voxeltex.VoxelTex;
import me.keybarricade.voxeltex.VoxelTexRenderer;

public class App {

    /**
     * Constructor.
     */
    public App() { }

    /**
     * Initialize.
     */
    public void init() {
        // Show initialization message
        System.out.println("Initializing...");

        // Create and run the renderer
        VoxelTexRenderer renderer = VoxelTex.createRenderer(KeyBarricade.APP_NAME + " v" + KeyBarricade.APP_VERSION_NAME);
        renderer.run();
    }
}
