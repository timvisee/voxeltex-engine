package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;

public class MainCamera {

    /**
     * The main camera component that is used for rendering.
     */
    private static AbstractCameraComponent mainCameraComponent;

    /**
     * Get the main camera component that is used for rendering.
     *
     * @return Main camera component.
     */
    public static AbstractCameraComponent getCamera() {
        return MainCamera.mainCameraComponent;
    }

    /**
     * Get the game object the main camera is attached on.
     *
     * @return Game object the main camera is attached on.
     */
    public static AbstractGameObject getCameraObject() {
        return getCamera().getOwner();
    }

    /**
     * Set the main camera component that will be used for rendering.
     *
     * @param mainCameraComponent Main camera component.
     */
    // TODO: Also accept game objects (with a camera attached to them) here!
    public static void setCamera(AbstractCameraComponent mainCameraComponent) {
        // TODO: Should we update the camera coordinates and offset?

        MainCamera.mainCameraComponent = mainCameraComponent;
    }
}
