package me.keybarricade.voxeltex.camera;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * Simple 6-axis free camera.
 */
public class FreeCamera {

    /**
     * Linear acceleration.
     */
    public Vector3f linAcc = new Vector3f();

    /**
     * Linear velocity.
     */
    public Vector3f linVel = new Vector3f();

    /**
     * Angular acceleration. (local)
     */
    public Vector3f angAcc = new Vector3f();

    /**
     * Angular velocity. (local)
     */
    public Vector3f angVel = new Vector3f();

    /**
     * Camera position.
     */
    public Vector3f position = new Vector3f(0, 0, 10);

    /**
     * Camera rotation.
     */
    public Quaternionf rotation = new Quaternionf();

    /**
     * Update the camera based on the elapsed time.
     *
     * @param dt Delta time.
     *
     * @return this
     */
    public FreeCamera update(float dt) {
        // Update linear velocity based on linear acceleration
        linVel.fma(dt, linAcc);

        // Update angular velocity based on angular acceleration
        angVel.fma(dt, angAcc);

        // Update the rotation based on the angular velocity
        rotation.integrate(dt, angVel.x, angVel.y, angVel.z);

        // Update position based on linear velocity
        position.fma(dt, linVel);

        // Return the camera
        return this;
    }

    /**
     * Apply camera transformation to the given matrix.
     *
     * @param m Matrix to apply.
     *
     * @return Matrix.
     */
    public Matrix4f apply(Matrix4f m) {
        return m.rotate(rotation).translate(-position.x, -position.y, -position.z);
    }

    /**
     * Compute right direction in world-space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f right(Vector3f dest) {
        return rotation.positiveX(dest);
    }

    /**
     * Compute up direction in world-space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f up(Vector3f dest) {
        return rotation.positiveY(dest);
    }

    /**
     * Compute forward direction in world space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f forward(Vector3f dest) {
        return rotation.positiveZ(dest).negate();
    }
}
