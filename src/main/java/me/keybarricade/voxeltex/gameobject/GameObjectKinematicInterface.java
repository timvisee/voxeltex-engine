package me.keybarricade.voxeltex.gameobject;

import org.joml.Vector3f;

public interface GameObjectKinematicInterface {

    /**
     * Get the linear acceleration.
     *
     * @return Linear acceleration.
     */
    Vector3f getLinearAcceleration();

    /**
     * Set the linear acceleration.
     *
     * @param linAcc Linear acceleration.
     */
    void setLinearAcceleration(Vector3f linAcc);

    /**
     * Get the linear velocity.
     *
     * @return Set the linear velocity.
     */
    Vector3f getLinearVelocity();

    /**
     * Set the linear velocity.
     *
     * @param linVel Linear velocity.
     */
    void setLinearVelocity(Vector3f linVel);

    /**
     * Get the angular acceleration.
     *
     * @return Angular acceleration.
     */
    Vector3f getAngularAcceleration();

    /**
     * Set the angular acceleration.
     *
     * @param angAcc Angular acceleration.
     */
    void setAngularAcceleration(Vector3f angAcc);

    /**
     * Get the angular velocity.
     *
     * @return Angular velocity.
     */
    Vector3f getAngularVelocity();

    /**
     * Set the angular velocity.
     *
     * @param angVel Angular velocity.
     */
    void setAngularVelocity(Vector3f angVel);
}
