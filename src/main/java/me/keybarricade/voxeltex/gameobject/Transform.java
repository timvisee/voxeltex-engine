package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.time.Time;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform {

    /**
     * The owner of this transform object.
     */
    private AbstractGameObject owner;

    /**
     * Game object position.
     */
    private Vector3f position = new Vector3f(0, 0, 0);

    /**
     * Game object rotation.
     */
    private Quaternionf rotation = new Quaternionf();

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
     *
     * @param owner The owner of this transformation.
     */
    public Transform(GameObject owner) {
        this.owner = owner;
    }

    /**
     * Get the owner.
     *
     * @return Game object owner.
     */
    public AbstractGameObject getOwner() {
        return this.owner;
    }

    /**
     * Get the game object position.
     *
     * @return Game object position.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Get the game object position in the world.
     *
     * @return Game object position.
     */
    public Vector3f getWorldPosition() {
        // Get the parent position
        Vector3f parentPos = new Vector3f(getParentWorldPosition());

        // TODO: We should use some sort of rotation here!
        parentPos.rotate(getRotation());

        // Calculate and return the world position
        return parentPos.add(getPosition());
    }

    /**
     * Get the game object position in the world.
     *
     * @param dest Vector destination.
     *
     * @return Game object position.
     */
    public Vector3f getWorldPosition(Vector3f dest) {
        return getParentWorldPosition().add(getPosition(), dest);
    }

    /**
     * Get the position of the parent game object.
     * If the object doesn't have a parent, a zero vector will be returned.
     *
     * @return Parent game object position.
     */
    public Vector3f getParentWorldPosition() {
        // Return the parent position if set
        if(getOwner().getParent() != null)
            return getOwner().getParent().getTransform().getWorldPosition();

        // Return zero
        return Vector3fFactory.zero();
    }

    /**
     * Set the game object position.
     *
     * @param position Game object position.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * Set the game object position in the world.
     *
     * @param position Game object world position.
     */
    public void setWorldPosition(Vector3f position) {
        // Calculate and set the local position
        setPosition(position.sub(getParentWorldPosition(), Vector3fFactory.zero()));
    }

    /**
     * Get the game object rotation.
     *
     * @return Game object rotation.
     */
    public Quaternionf getRotation() {
        return rotation;
    }

    /**
     * Set the game object rotation.
     *
     * @param rotation Game object rotation.
     */
    public void setRotation(Quaternionf rotation) {
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
     * Update the transform.
     */
    public void update() {
        // Update linear velocity based on linear acceleration
        linVel.fma(Time.deltaTimeFloat, this.linAcc);

        // Update angular velocity based on angular acceleration
        angVel.fma(Time.deltaTimeFloat, this.angAcc);

        // Update the rotation based on the angular velocity
        getRotation().integrate(Time.deltaTimeFloat, this.angVel.x, this.angVel.y, this.angVel.z);

        // Update position based on linear velocity
        getPosition().fma(Time.deltaTimeFloat, this.linVel);
    }

    /**
     * Compute forward direction in world space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f forward(Vector3f dest) {
        return this.rotation.positiveZ(dest).negate();
    }

    /**
     * Compute right direction in world-space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f right(Vector3f dest) {
        return this.rotation.positiveX(dest);
    }

    /**
     * Compute up direction in world-space.
     *
     * @param dest Destination.
     *
     * @return Destination.
     */
    public Vector3f up(Vector3f dest) {
        return this.rotation.positiveY(dest);
    }

    /**
     * Apply the world transform of the current object to the given matrix.
     *
     * @param dest Matrix.
     *
     * @return The transformed matrix.
     */
    public Matrix4f applyWorldTransform(Matrix4f dest) {
        // Apply the parent to the matrix first if available
        if(getOwner().hasParent())
            getOwner().getParent().getTransform().applyWorldTransform(dest);

        // Translate the matrix with the object's local position
        dest.translate(-this.position.x, -this.position.y, -this.position.z).rotate(this.rotation);

        // Return the matrix
        return dest;
    }
}
