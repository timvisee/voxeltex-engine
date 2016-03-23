package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.global.Time;
import me.keybarricade.voxeltex.math.quaternion.QuaternionfFactory;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
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
    private final Vector3f position = Vector3fFactory.identity();

    /**
     * Game object rotation.
     */
    private final Quaternionf rotation = QuaternionfFactory.identity();

    /**
     * Linear acceleration.
     */
    private final Vector3f linAcc = new Vector3f();

    /**
     * Linear velocity.
     */
    private final Vector3f linVel = new Vector3f();

    /**
     * Angular acceleration. (local)
     */
    private final Vector3f angAcc = new Vector3f();

    /**
     * Angular velocity. (local)
     */
    private final Vector3f angVel = new Vector3f();

    /**
     * Shared lock for parent world object cache usage. Use a static instance to minimize the memory footprint.
     */
    private static final Object parentWorldSharedCacheLock = new Object();

    /**
     * Cached parent world position.
     * Caching and recycling the instance adds a performance benefit. Use a static instance to minimize the memory footprint.
     */
    private static final Vector3f parentWorldPositionCache = new Vector3f();

    /**
     * Cached parent world rotation.
     * Caching and recycling the instance adds a performance benefit. Use a static instance to minimize the memory footprint.
     */
    private static final Quaternionf parentWorldRotationCache = new Quaternionf();

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
        return getWorldPosition(Vector3fFactory.identity());
    }

    /**
     * Get the game object position in the world.
     *
     * @param dest Vector destination. (allocation free)
     *
     * @return Game object position.
     */
    public Vector3f getWorldPosition(Vector3f dest) {
        // Copy the local position
        dest.set(this.position);

        // Synchronize usage of the cache to prevent issues
        synchronized(parentWorldSharedCacheLock) {
            // Clear the parent world position and rotation cache
            parentWorldPositionCache.zero();
            parentWorldRotationCache.identity();

            // Apply the world rotation of the parent
            dest.rotate(getParentWorldRotation(parentWorldRotationCache));

            // Add the world rotation of the parent and return the result
            return dest.add(getParentWorldPosition(parentWorldPositionCache));
        }
    }

    /**
     * Get the position of the parent game object.
     * If the object doesn't have a parent, a zero vector will be returned.
     *
     * @return Parent game object position.
     */
    public Vector3f getParentWorldPosition() {
        return getParentWorldPosition(Vector3fFactory.identity());
    }

    /**
     * Get the position of the parent game object.
     * If the object doesn't have a parent, a zero vector will be returned.
     *
     * @param dest Vector destination. (allocation free)
     *
     * @return Parent game object position.
     */
    public Vector3f getParentWorldPosition(Vector3f dest) {
        // Return the parent position if set
        if(getOwner().hasParent())
            return getOwner().getParent().getTransform().getWorldPosition(dest);

        // Return zero
        return dest.zero();
    }

    /**
     * Set the game object position.
     *
     * @param position Game object position.
     */
    public void setPosition(Vector3f position) {
        this.position.set(position);
    }

    /**
     * Set the game object position in the world.
     *
     * @param position Game object world position.
     */
    public void setWorldPosition(Vector3f position) {
        // Make sure we aren't using the cache in multiple places
        synchronized(parentWorldSharedCacheLock) {
            // Clear the parent world position cache
            parentWorldPositionCache.zero();
            parentWorldRotationCache.identity();

            // Set the local position by subtracting the world position of the parent and rotating it by the world rotation
            // of this object
            // TODO: Use cache for the second vector instance too?
            setPosition(
                    position.sub(getParentWorldPosition(parentWorldPositionCache), Vector3fFactory.identity())
                            .rotate(getWorldRotation(parentWorldRotationCache).invert())
            );
        }
    }

    /**
     * Get the game object rotation.
     *
     * @return Game object rotation.
     */
    public Quaternionf getRotation() {
        return this.rotation;
    }

    /**
     * Get the game object rotation in the world.
     *
     * @return Game object rotation.
     */
    public Quaternionf getWorldRotation() {
        return getWorldRotation(QuaternionfFactory.identity());
    }

    /**
     * Get the game object rotation in the world.
     *
     * @param dest Quaternion destination. (allocation free)
     *
     * @return Game object rotation.
     */
    public Quaternionf getWorldRotation(Quaternionf dest) {
        // Get the parent rotation
        getParentWorldRotation(dest);

        // Multiply the parent's rotation with the current rotation and return the result
        return dest.mul(getRotation(), dest);
    }

    /**
     * Get the rotation of the parent game object.
     * If the object doesn't have a parent, a zero quaternion will be returned.
     *
     * @return Parent game object rotation.
     */
    public Quaternionf getParentWorldRotation() {
        return getParentWorldRotation(QuaternionfFactory.identity());
    }

    /**
     * Get the rotation of the parent game object.
     * If the object doesn't have a parent, a zero quaternion will be returned.
     *
     * @param dest Quaternion destination. (allocation free)
     *
     * @return Parent game object rotation.
     */
    public Quaternionf getParentWorldRotation(Quaternionf dest) {
        // Return the parent position if set
        if(getOwner().hasParent())
            return getOwner().getParent().getTransform().getWorldRotation(dest);

        // Return zero
        return dest.identity();
    }

    /**
     * Set the game object rotation.
     *
     * @param rotation Game object rotation.
     */
    public void setRotation(Quaternionf rotation) {
        // Set the rotation and normalize to prevent weird rotation glitches on non-normalized quaternions
        this.rotation.set(rotation).normalize();
    }

    /**
     * Set the game object world space rotation.
     *
     * @param rotation Game object world space rotation.
     */
    public void setWorldRotation(Quaternionf rotation) {
        // Make sure we aren't using the rotation cache in multiple places
        synchronized(parentWorldSharedCacheLock) {
            this.rotation.set(
                    rotation.mul(
                            getParentWorldRotation(parentWorldRotationCache).invert(),
                            this.rotation)
            );
        }
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
        this.linAcc.set(linAcc);
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
        this.linVel.set(linVel);
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
        this.angAcc.set(angAcc);
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
        this.angVel.set(angVel);
    }

    /**
     * Update the transform.
     */
    public synchronized void update() {
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
     * @return Destination.
     */
    public Vector3f forward() {
        return forward(Vector3fFactory.identity());
    }

    /**
     * Compute forward direction in world space.
     *
     * @param dest Destination. (allocation free)
     *
     * @return Destination.
     */
    public Vector3f forward(Vector3f dest) {
        return this.rotation.positiveZ(dest).negate();
    }

    /**
     * Compute right direction in world-space.
     *
     * @return Destination.
     */
    public Vector3f right() {
        return right(Vector3fFactory.identity());
    }

    /**
     * Compute right direction in world-space.
     *
     * @param dest Destination. (allocation free)
     *
     * @return Destination.
     */
    public Vector3f right(Vector3f dest) {
        return this.rotation.positiveX(dest);
    }

    /**
     * Compute up direction in world-space.
     *
     * @return Destination.
     */
    public Vector3f up() {
        return up(Vector3fFactory.identity());
    }

    /**
     * Compute up direction in world-space.
     *
     * @param dest Destination. (allocation free)
     *
     * @return Destination.
     */
    public Vector3f up(Vector3f dest) {
        return this.rotation.positiveY(dest);
    }

    /**
     * Apply the local transform of the object to the given matrix.
     *
     * @param dest Matrix.
     *
     * @return The transformed matrix.
     */
    public Matrix4f applyTransform(Matrix4f dest) {
        return dest.translate(this.position).rotate(this.rotation);
    }

    /**
     * Apply the world transform of the object to the given matrix.
     *
     * @param dest Matrix.
     *
     * @return The transformed matrix.
     */
    public Matrix4f applyWorldTransform(Matrix4f dest) {
        // Apply the parent to the matrix first if available
        if(getOwner().hasParent())
            getOwner().getParent().getTransform().applyWorldTransform(dest);

        // Apply the local transformation and return
        return applyTransform(dest);
    }
}
