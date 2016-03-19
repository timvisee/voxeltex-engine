package me.keybarricade.voxeltex.shader;

import java.util.ArrayList;
import java.util.List;

public class ShaderTracker {

    /**
     * List of shaders managed by the engine.
     */
    private static List<Shader> shaders = new ArrayList<>();

    /**
     * Add a new shader to the tracker.
     *
     * @param shader Shader to add.
     */
    public static void trackShader(Shader shader) {
        shaders.add(shader);
    }

    /**
     * Get the list of shaders.
     *
     * @return List of shaders.
     */
    public static List<Shader> getShaders() {
        return shaders;
    }

    /**
     * Get the number of managed shaders.
     *
     * @return Shader count.
     */
    public static int getShaderCount() {
        return shaders.size();
    }

    /**
     * Remove the given shader from the tracker.
     *
     * @param shader Shader to remove.
     *
     * @return True if a shader was removed from the tracker, false othewrise.
     */
    public static boolean untrackShader(Shader shader) {
        return shaders.remove(shader);
    }

    /**
     * Remove the shader from the tracker at the position of the given index.
     *
     * @param i Shader index.
     *
     * @return Removed shader.
     */
    public static Shader untrackShader(int i) {
        return shaders.remove(i);
    }

    /**
     * Dispose all tracked shaders and remove them from the tracker.
     */
    public static void disposeAll() {
        // Loop through all shaders and dispose them
        // Loop through all textures and dispose them
        for(int i = getShaderCount() - 1; i >= 0; i--)
            shaders.get(i).dispose();

        // Remove all shaders (should have been removed already)
        shaders.clear();
    }
}
