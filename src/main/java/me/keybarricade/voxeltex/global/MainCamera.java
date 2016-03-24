package me.keybarricade.voxeltex.global;

import me.keybarricade.voxeltex.component.camera.AbstractCameraComponent;
import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
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
    private static final Vector3f cameraPosition = new Vector3f();

    /**
     * The world reotation of the camera on the last update.
     */
    private static final Quaternionf cameraRotation = new Quaternionf();

    /**
     * Projection matrix. This matrix is updated each frame before it's send to OpenGL.
     */
    private static final Matrix4f projectionMatrix = new Matrix4f();

    /**
     * Cached camera view matrix that is used for rendering from time to time.
     * Caching and recycling the instance adds a huge performance benefit.
     */
    private static final Matrix4f cameraViewMatrixCache = new Matrix4f();

    /**
     * Cached camera world rotation.
     * Caching and recycling the instance adds a performance benefit.
     */
    private static final Quaternionf cameraRotationCache = new Quaternionf();

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
    public static void setCamera(AbstractCameraComponent mainCameraComponent) {
        MainCamera.mainCameraComponent = mainCameraComponent;
    }

    /**
     * Update the camera positions.
     */
    public static synchronized void update() {
        // Make sure the main camera component is set
        if(MainCamera.mainCameraComponent == null) {
            // Reset the position and rotation
            cameraPosition.set(0);
            cameraRotation.identity();
            return;
        }

        // Set the camera transform positions
        mainCameraComponent.getTransform().getWorldPosition(cameraPosition);
        mainCameraComponent.getTransform().getWorldRotation(cameraRotation);

        // Update the camera itself
        MainCamera.mainCameraComponent.updateCamera();
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
     * Create the camera view matrix.
     *
     * @return Camera view matrix.
     */
    public static Matrix4f createCameraViewMatrix() {
        // Make sure the camera view matrix isn't modified by something else while using it
        synchronized(cameraViewMatrixCache) {
            // Set the matrix to it's identity
            cameraViewMatrixCache.identity();

            // Create the camera view matrix
            return createCameraViewMatrix(cameraViewMatrixCache);
        }
    }

    /**
     * Create the camera view matrix.
     *
     * @param dest Destination matrix. (allocation free)
     *
     * @return Camera view matrix.
     */
    public static Matrix4f createCameraViewMatrix(Matrix4f dest) {
        // Make sure a camera is available, otherwise return the world origin matrix
        if(!hasCamera())
            return dest.identity();

        // Create and return the camera view matrix
        return getCameraObject().getTransform().addWorldMatrix(dest.identity()).invert();
    }


    /**
     * Get the projection matrix.
     *
     * @return Projection matrix.
     */
    public static Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    /**
     * Set the projection matrix.
     *
     * @param projectionMatrix Projection matrix.
     */
    public static void setProjectionMatrix(Matrix4f projectionMatrix) {
        projectionMatrix.set(projectionMatrix);
    }
}
