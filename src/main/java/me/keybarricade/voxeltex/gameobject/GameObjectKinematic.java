package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.math.vector.*;

import org.joml.Quaternionf;

public class GameObjectKinematic extends GameObject implements GameObjectKinematicInterface {

    /**
     * Linear acceleration.
     */
    private Vector3fFactory linAcc = new Vector3fFactory();

    /**
     * Linear velocity.
     */
    private Vector3fFactory linVel = new Vector3fFactory();

    /**
     * Angular acceleration. (local)
     */
    private Vector3fFactory angAcc = new Vector3fFactory();

    /**
     * Angular velocity. (local)
     */
    private Vector3fFactory angVel = new Vector3fFactory();

    /**
     * Constructor.
     */
    public GameObjectKinematic() {
        super();
    }

    /**
     * Constructor.
     *
     * @param position Game object position.
     * @param rotation Game object rotation.
     */
    public GameObjectKinematic(Vector3fFactory position, Quaternionf rotation) {
        super(position, rotation);
    }

    /**
     * Constructor.
     *
     * @param position Game object position.
     * @param rotation Game object rotation.
     */
    public GameObjectKinematic(Vector3fFactory position, Quaternionf rotation, Vector3fFactory linAcc, Vector3fFactory linVel, Vector3fFactory angAcc, Vector3fFactory angVel) {
        // Construct the super object
        super(position, rotation);

        // Set the linear and angular acceleration and velocity
        this.linAcc = linAcc;
        this.linVel = linVel;
        this.angAcc = angAcc;
        this.angVel = angVel;
    }

    @Override
    public Vector3fFactory getLinearAcceleration() {
        return linAcc;
    }

    @Override
    public void setLinearAcceleration(Vector3fFactory linAcc) {
        this.linAcc = linAcc;
    }

    @Override
    public Vector3fFactory getLinearVelocity() {
        return linVel;
    }

    @Override
    public void setLinearVelocity(Vector3fFactory linVel) {
        this.linVel = linVel;
    }

    @Override
    public Vector3fFactory getAngularAcceleration() {
        return angAcc;
    }

    @Override
    public void setAngularAcceleration(Vector3fFactory angAcc) {
        this.angAcc = angAcc;
    }

    @Override
    public Vector3fFactory getAngularVelocity() {
        return angVel;
    }

    @Override
    public void setAngularVelocity(Vector3fFactory angVel) {
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
