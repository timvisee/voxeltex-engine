package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.math.vector.*;

public interface GameObjectKinematicInterface {

    /**
     * Get the linear acceleration.
     *
     * @return Linear acceleration.
     */
    Vector3fFactory getLinearAcceleration();

    /**
     * Set the linear acceleration.
     *
     * @param linAcc Linear acceleration.
     */
    void setLinearAcceleration(Vector3fFactory linAcc);

    /**
     * Get the linear velocity.
     *
     * @return Set the linear velocity.
     */
    Vector3fFactory getLinearVelocity();

    /**
     * Set the linear velocity.
     *
     * @param linVel Linear velocity.
     */
    void setLinearVelocity(Vector3fFactory linVel);

    /**
     * Get the angular acceleration.
     *
     * @return Angular acceleration.
     */
    Vector3fFactory getAngularAcceleration();

    /**
     * Set the angular acceleration.
     *
     * @param angAcc Angular acceleration.
     */
    void setAngularAcceleration(Vector3fFactory angAcc);

    /**
     * Get the angular velocity.
     *
     * @return Angular velocity.
     */
    Vector3fFactory getAngularVelocity();

    /**
     * Set the angular velocity.
     *
     * @param angVel Angular velocity.
     */
    void setAngularVelocity(Vector3fFactory angVel);
}
