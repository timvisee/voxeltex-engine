package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.gameobject.Transform;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class MainCamera {

    /**
     * The main camera component that is used for rendering.
     */
    private static AbstractCameraComponent mainCameraComponent;

    /**
     * The world position of the camera on the last update.
     */
    private static Vector3f cameraPosition;

    /**
     * The world reotation of the camera on the last update.
     */
    private static Quaternionf cameraRotation;

    /**
     * Get the main camera component that is used for rendering.
     *
     * @return Main camera component.
     */
    public static AbstractCameraComponent getCamera() {
        return MainCamera.mainCameraComponent;
    }

    /**
     * Check whether any main camera is specified.
     *
     * @return True if a main camera has been specified, false if not.
     */
    public static boolean hasCamera() {
        return getCamera() != null;
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
        // Set the main camera
        MainCamera.mainCameraComponent = mainCameraComponent;

        // Update the camera positions in the world
        update();
    }

    /**
     * Update the camera positions.
     */
    public static void update() {
        // Make sure the main camera component is set
        if(MainCamera.mainCameraComponent == null) {
            // Reset the position and rotation
            cameraPosition = new Vector3f();
            cameraRotation = new Quaternionf();
            return;
        }

        // Get the camera transform
        Transform transform = MainCamera.mainCameraComponent.getTransform();

        // Set the camera transform positions
        // TODO: Make sure the rotation is cumulative over all objects
        cameraPosition = transform.getWorldPosition();
        cameraRotation = transform.getRotation();
    }

    /**
     * Get the camera position since the last update.
     *
     * @return Camera position since the last update.
     */
    public static Vector3f getCameraPositionLastUpdate() {
        return cameraPosition;
    }

    /**
     * Get the camera position since the last update.
     *
     * @return Camera position since the last update.
     */
    public static Quaternionf getCameraRotationLastUpdate() {
        return cameraRotation;
    }

    /**
     * Create the camera base matrix.
     *
     * @return Camera base matrix.
     */
    public static Matrix4f createCameraMatrix() {
        return createCameraMatrix(new Matrix4f());
    }

    /**
     * Create the camera base matrix.
     *
     * @param dest Destination matrix.
     *
     * @return Camera base matrix.
     */
    public static Matrix4f createCameraMatrix(Matrix4f dest) {
        return dest.rotate(cameraRotation).translate(cameraPosition.x, cameraPosition.y, cameraPosition.z);
    }
}
