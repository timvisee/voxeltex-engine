package me.keybarricade.voxeltex.gameobject;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class GameObjectKinematic extends GameObject implements GameObjectKinematicInterface {

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
    public GameObjectKinematic(Vector3f position, Quaternionf rotation) {
        super(position, rotation);
    }

    /**
     * Constructor.
     *
     * @param position Game object position.
     * @param rotation Game object rotation.
     */
    public GameObjectKinematic(Vector3f position, Quaternionf rotation, Vector3f linAcc, Vector3f linVel, Vector3f angAcc, Vector3f angVel) {
        // Construct the super object
        super(position, rotation);

        // Set the linear and angular acceleration and velocity
        this.linAcc = linAcc;
        this.linVel = linVel;
        this.angAcc = angAcc;
        this.angVel = angVel;
    }

    @Override
    public Vector3f getLinearAcceleration() {
        return linAcc;
    }

    @Override
    public void setLinearAcceleration(Vector3f linAcc) {
        this.linAcc = linAcc;
    }

    @Override
    public Vector3f getLinearVelocity() {
        return linVel;
    }

    @Override
    public void setLinearVelocity(Vector3f linVel) {
        this.linVel = linVel;
    }

    @Override
    public Vector3f getAngularAcceleration() {
        return angAcc;
    }

    @Override
    public void setAngularAcceleration(Vector3f angAcc) {
        this.angAcc = angAcc;
    }

    @Override
    public Vector3f getAngularVelocity() {
        return angVel;
    }

    @Override
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
