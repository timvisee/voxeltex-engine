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
    private Vector3f linAcc = new Vector3f();

    /**
     * Linear velocity.
     */
    private Vector3f linVel = new Vector3f();

    /**
     * Angular acceleration. (local)
     */
    private Vector3f angAcc = new Vector3f();

    /**
     * Angular velocity. (local)
     */
    private Vector3f angVel = new Vector3f();

    /**
     * Camera position.
     */
    private Vector3f position = new Vector3f(0, 0, 10);

    /**
     * Camera rotation.
     */
    private Quaternionf rotation = new Quaternionf();

    /**
     * Constructor.
     */
    public FreeCamera() { }

    /**
     * Constructor.
     *
     * @param position Camera position.
     * @param rotation Camera rotation.
     */
    public FreeCamera(Vector3f position, Quaternionf rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Get the linear acceleration.
     *
     * @return Linear acceleration.
     */
    public Vector3f getLinearAcceleration() {
        return linAcc;
    }

    /**
     * Set the linear acceleration.
     *
     * @param linAcc Linear acceleration.
     */
    public void setLinearAcceleration(Vector3f linAcc) {
        this.linAcc = linAcc;
    }

    /**
     * Get the linear velocity.
     *
     * @return Set the linear velocity.
     */
    public Vector3f getLinearVelocity() {
        return linVel;
    }

    /**
     * Set the linear velocity.
     *
     * @param linVel Linear velocity.
     */
    public void setLinearVelocity(Vector3f linVel) {
        this.linVel = linVel;
    }

    /**
     * Get the angular acceleration.
     *
     * @return Angular acceleration.
     */
    public Vector3f getAngularAcceleration() {
        return angAcc;
    }

    /**
     * Set the angular acceleration.
     *
     * @param angAcc Angular acceleration.
     */
    public void setAngularAcceleration(Vector3f angAcc) {
        this.angAcc = angAcc;
    }

    /**
     * Get the angular velocity.
     *
     * @return Angular velocity.
     */
    public Vector3f getAngularVelocity() {
        return angVel;
    }

    /**
     * Set the angular velocity.
     *
     * @param angVel Angular velocity.
     */
    public void setAngularVelocity(Vector3f angVel) {
        this.angVel = angVel;
    }

    /**
     * Get the camera position.
     *
     * @return Camera position.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Set the camera position.
     *
     * @param position Camera position.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * Get the camera rotation.
     *
     * @return Camera rotation.
     */
    public Quaternionf getRotation() {
        return rotation;
    }

    /**
     * Set the camera rotation.
     *
     * @param rotation Camera rotation.
     */
    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

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
