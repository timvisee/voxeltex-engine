package me.keybarricade.test;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.input.mouse.MouseInputManager;
import org.lwjgl.glfw.GLFW;

public class MenuControllerComponent extends BaseComponent {

    /**
     * True to show the menu, false to hide.
     */
    private boolean showMenu = false;

    /**
     * The game object containing the menu.
     */
    private AbstractGameObject menuObject;

    /**
     * Stores the original mouse cursor mode when the menu panel is shown.
     */
    private int originalMouseCursorMode = MouseInputManager.CURSOR_MODE_NORMAL;

    /**
     * Constructor.
     *
     * @param menuObject Menu game object.
     */
    public MenuControllerComponent(AbstractGameObject menuObject) {
        this.menuObject = menuObject;
    }

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() {
        // Check whether the menu key is pressed
        if(Input.isKeyDownOnce(GLFW.GLFW_KEY_E)) {
            // Toggle the show menu flag
            if(!this.showMenu) {
                // Set the show menu flag
                this.showMenu = true;

                // Store the original mouse cursor mode
                this.originalMouseCursorMode = Input.getMouseCursorMode();

                // Set the current mouse cursor mode to normal
                Input.setMouseCursorMode(MouseInputManager.CURSOR_MODE_NORMAL);

                // Center the mouse cursor
                Input.centerMouseCursor();

            } else {
                // Set the show menu flag
                this.showMenu = false;

                // Restore the original mouse cursor mode
                Input.setMouseCursorMode(this.originalMouseCursorMode);
            }
        }

        // Set the enabled state of the menu object to match the show menu flag
        if(this.menuObject != null)
            this.menuObject.setEnabled(this.showMenu);
    }

    @Override
    public void destroy() { }
}
