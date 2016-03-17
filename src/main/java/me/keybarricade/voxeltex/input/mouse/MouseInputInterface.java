package me.keybarricade.voxeltex.input.mouse;


import org.lwjgl.glfw.GLFW;

public interface MouseInputInterface {

    /**
     * Get the x coordinate of the mouse cursor.
     *
     * @return X coordinate.
     */
    float getMouseX();

    /**
     * Get the y coordinate of the mouse cursor.
     *
     * @return Y coordinate.
     */
    float getMouseY();

    /**
     * Check whether a mouse button is pressed.
     *
     * @param mouseButtonCode Mouse button code, for example {@link GLFW#GLFW_MOUSE_BUTTON_LEFT} or {@link GLFW#GLFW_MOUSE_BUTTON_RIGHT}.
     *
     * @return True if the mouse button is down, false if not.
     */
    boolean isMouseButtonDown(int mouseButtonCode);
}
