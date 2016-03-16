package me.keybarricade.voxeltex.component.camera;

import me.keybarricade.voxeltex.component.BaseComponent;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class AbstractCameraComponent extends BaseComponent {

    /**
     * Update the camera position.
     */
    public abstract void updateCamera();

    /**
     * Apply camera transformation to the given matrix.
     *
     * @param m Matrix to apply.
     *
     * @return Matrix.
     */
    public abstract Matrix4f apply(Matrix4f m);

    /**
     * Apply the camera transformation to a matrix.
     *
     * @return Matrix.
     */
    public abstract Matrix4f apply();

    /**
     * Apply the camera transformation to the model view matrix.
     */
    public abstract void applyViewMatrix();





    /**
     * Compute right direction in world-space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f right(Vector3f dest) {
        return getOwner().getRotation().positiveX(dest);
    }

    /**
     * Compute up direction in world-space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f up(Vector3f dest) {
        return getOwner().getRotation().positiveY(dest);
    }

    /**
     * Compute forward direction in world space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f forward(Vector3f dest) {
        return getOwner().getRotation().positiveZ(dest).negate();
    }
}
