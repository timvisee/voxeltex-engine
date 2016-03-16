package me.keybarricade.voxeltex.input.key;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public interface KeyInputInterface {

    /**
     * Check whether a key is pressed.
     *
     * @param keyCode Key code, for example {@link GLFW#GLFW_KEY_W} or {@link GLFW#GLFW_KEY_ENTER}.
     *
     * @return True if the key is down, false if not.
     */
    boolean isKeyDown(int keyCode);
}
