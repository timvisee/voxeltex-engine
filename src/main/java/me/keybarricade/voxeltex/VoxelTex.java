package me.keybarricade.voxeltex;

import me.keybarricade.voxeltex.renderer.VoxelTexRenderer;

public class VoxelTex {

    /**
     * Engine name.
     */
    public static final String ENGINE_NAME = "VoxelTex";

    /**
     * Create a new renderer.
     *
     * @param title Window title.
     *
     * @return Renderer instance.
     */
    public static VoxelTexRenderer createRenderer(String title) {
        // Show a status message
        System.out.println("Creating " + ENGINE_NAME + " renderer...");

        // Create the voxel renderer
        VoxelTexRenderer renderer = new VoxelTexRenderer();

        // Set the window title
        // TODO: Enable this again!
        //renderer.getWindow().setTitle(title);

        // Show a status message
        System.out.println(ENGINE_NAME + " renderer created!");

        // Return the renderer
        return renderer;
    }
}
