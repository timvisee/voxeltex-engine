package me.keybarricade.voxeltex.gameobject;

import org.joml.Vector3f;

public class GameObjectKinematic extends GameObject {

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

    @Override
    public void update() {
        // Call the super update method
        super.update();

        // TODO: Determine the delta time here.
        float dt = 0.0f;

        // Update linear velocity based on linear acceleration
        linVel.fma(dt, linAcc);

        // Update angular velocity based on angular acceleration
        angVel.fma(dt, angAcc);

        // Update the rotation based on the angular velocity
        getRotation().integrate(dt, angVel.x, angVel.y, angVel.z);

        // Update position based on linear velocity
        getPosition().fma(dt, linVel);
    }
}
