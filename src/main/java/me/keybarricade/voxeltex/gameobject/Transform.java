/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

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
     * Game object scale.
     */
    private final Vector3f scale = Vector3fFactory.one();

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
     * Temporary matrix, used while performing certain calculations.
     * Using and recycling this temporary matrix minimizes object allocation, resulting in better performance.
     */
    private final Matrix4f tempMatrix = new Matrix4f();

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
     * Get the transformation matrix of the game object in local space.
     *
     * This method allocates a new matrix and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Local transformation matrix.
     */
    public Matrix4f getLocalMatrix() {
        return getLocalMatrix(new Matrix4f());
    }

    /**
     * Get the transformation matrix of the game object in local space.
     *
     * @param dest Destination matrix. (allocation free)
     *
     * @return Local transformation matrix.
     */
    public Matrix4f getLocalMatrix(Matrix4f dest) {
        return addLocalMatrix(dest.identity());
    }

    /**
     * Get the transformation matrix of the game object in local space and add it to the given matrix.
     *
     * @param matrix Matrix to add the local matrix to.
     *
     * @return Combined matrix.
     */
    public Matrix4f addLocalMatrix(Matrix4f matrix) {
        return matrix.translate(this.position).rotate(this.rotation).scale(this.scale);
    }

    /**
     * Get the transformation matrix of the game object in world space.
     *
     * This method allocates a new matrix and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return World transformation matrix.
     */
    public Matrix4f getWorldMatrix() {
        return getWorldMatrix(new Matrix4f());
    }

    /**
     * Get the transformation matrix of the game object in world space.
     *
     * @param dest Destination matrix. (allocation free)
     *
     * @return World transformation matrix.
     */
    public Matrix4f getWorldMatrix(Matrix4f dest) {
        return addLocalMatrix(getParentWorldMatrix(dest));
    }

    /**
     * Get the transformation matrix of the game object in local space and add it to the given matrix.
     *
     * @param matrix Matrix to add the local matrix to.
     *
     * @return Combined matrix.
     */
    public Matrix4f addWorldMatrix(Matrix4f matrix) {
        // Synchronize to ensure we aren't using the matrix cache multiple times at once
        synchronized(tempMatrix) {
            return matrix.mul(getWorldMatrix(tempMatrix));
        }
    }

    /**
     * Get the transformation matrix of the parent game object in local space.
     * If this game object doesn't have a parent, the origin of the world is returned.
     *
     * This method allocates a new matrix and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Parent local transformation matrix.
     */
    public Matrix4f getParentLocalMatrix() {
        return getParentLocalMatrix(new Matrix4f());
    }

    /**
     * Get the transformation matrix of the parent game object in local space.
     * If this game object doesn't have a parent, the origin of the world is returned.
     *
     * @param dest Destination matrix. (allocation free)
     *
     * @return Parent local transformation matrix.
     */
    public Matrix4f getParentLocalMatrix(Matrix4f dest) {
        // If this object has a parent, return it's local transformation matrix
        if(getOwner().hasParent())
            return getOwner().getParent().getTransform().getLocalMatrix(dest);

        // Return the origin of the world
        return dest.identity();
    }

    /**
     * Get the transformation matrix of the parent game object in world space.
     * If this game object doesn't have a parent, the origin of the world is returned.
     *
     * This method allocates a new matrix and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Parent world transformation matrix.
     */
    public Matrix4f getParentWorldMatrix() {
        return getParentWorldMatrix(new Matrix4f());
    }

    /**
     * Get the transformation matrix of the parent game object in world space.
     * If this game object doesn't have a parent, the origin of the world is returned.
     *
     * @param dest Destination matrix. (allocation free)
     *
     * @return Parent world transformation matrix.
     */
    public Matrix4f getParentWorldMatrix(Matrix4f dest) {
        // If this object has a parent, return it's world transformation matrix
        if(getOwner().hasParent())
            return getOwner().getParent().getTransform().getWorldMatrix(dest);

        // Return the origin of the world
        return dest.identity();
    }

    /**
     * Get the position of this game object in local space.
     *
     * @return Game object local position.
     */
    public Vector3f getPosition() {
        return this.position;
    }

    /**
     * Get the position of this game object in world space.
     *
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Game object world position.
     */
    public Vector3f getWorldPosition() {
        return getWorldPosition(Vector3fFactory.identity());
    }

    /**
     * Get the position of this game object in world space.
     *
     * @param dest Destination vector. (allocation free)
     *
     * @return Game object world position.
     */
    public Vector3f getWorldPosition(Vector3f dest) {
        // Make sure we aren't using the matrix cache more than once at the same time
        synchronized(tempMatrix) {
            return getWorldMatrix(tempMatrix).getTranslation(dest);
        }
    }

    /**
     * Get the position of parent game object in world space.
     * The origin of the world will be returned if this object doesn't have a parent.
     *
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Parent game object world position.
     */
    public Vector3f getParentWorldPosition() {
        return getParentWorldPosition(Vector3fFactory.identity());
    }

    /**
     * Get the position of parent game object in world space.
     * The origin of the world will be returned if this object doesn't have a parent.
     *
     * @param dest Destination vector. (allocation free)
     *
     * @return Parent game object world position.
     */
    public Vector3f getParentWorldPosition(Vector3f dest) {
        // If this game object has a parent, return it's world position
        if(getOwner().hasParent())
            return getOwner().getParent().getTransform().getWorldPosition(dest);

        // Return the world origin position
        return dest.zero();
    }

    /**
     * Set the position of the game object in local space.
     *
     * @param position Game object local position.
     */
    public void setPosition(Vector3f position) {
        this.position.set(position);
    }

    /**
     * Get the rotation of the game object in local space.
     *
     * @return Game object local rotation.
     */
    public Quaternionf getRotation() {
        return this.rotation;
    }

    /**
     * Get the rotation of the game object in world space.
     *
     * This method allocates a new quaternion and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Game object world rotation.
     */
    public Quaternionf getWorldRotation() {
        return getWorldRotation(QuaternionfFactory.identity());
    }

    /**
     * Get the rotation of the game object in world space.
     *
     * @param dest Destination quaternion. (allocation free)
     *
     * @return Game object world rotation.
     */
    public Quaternionf getWorldRotation(Quaternionf dest) {
        // Make sure we aren't using the matrix cache more than once at the same time
        synchronized(tempMatrix) {
            return getWorldMatrix(tempMatrix).getUnnormalizedRotation(dest);
        }
    }

    /**
     * Get the rotation of the parent game object in world space.
     * The rotation of the world origin is returned if this game object doesn't have a parent.
     *
     * This method allocates a new quaternion and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Parent game object world rotation.
     */
    public Quaternionf getParentWorldRotation() {
        return getParentWorldRotation(QuaternionfFactory.identity());
    }

    /**
     * Get the rotation of the parent game object in world space.
     * The rotation of the world origin is returned if this game object doesn't have a parent.
     *
     * @param dest Destination quaternion. (allocation free)
     *
     * @return Parent game object world rotation.
     */
    public Quaternionf getParentWorldRotation(Quaternionf dest) {
        // If the game object has a parent, return it's world rotation
        if(getOwner().hasParent())
            return getOwner().getParent().getTransform().getWorldRotation(dest);

        // Return the world origin rotation
        return dest.identity();
    }

    /**
     * Set the rotation of the game object in local space.
     *
     * @param rotation Game object local rotation.
     */
    public void setRotation(Quaternionf rotation) {
        // Set the rotation and normalize to prevent weird rotation glitches on non-normalized quaternions
        this.rotation.set(rotation).normalize();
    }

    /**
     * Get the scale of the game object in local space.
     *
     * @return Game object local scale.
     */
    public Vector3f getScale() {
        return this.scale;
    }

    /**
     * Get the scale of the game object in world space.
     *
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Game object world scale.
     */
    public Vector3f getWorldScale() {
        return getWorldScale(Vector3fFactory.identity());
    }

    /**
     * Get the scale of the game object in world space.
     *
     * @param dest Destination vector. (allocation free)
     *
     * @return Game object world scale.
     */
    public Vector3f getWorldScale(Vector3f dest) {
        // Make sure we aren't using the matrix cache more than once at the same time
        synchronized(tempMatrix) {
            return getWorldMatrix(tempMatrix).getScale(dest);
        }
    }

    /**
     * Get the scale of the parent game object in world space.
     * The scale of the world origin will be returned if this game object doesn't have a parent.
     *
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
     *
     * @return Parent game object world scale.
     */
    public Vector3f getParentWorldScale() {
        return getParentWorldScale(Vector3fFactory.identity());
    }

    /**
     * Get the scale of the parent game object in world space.
     * The scale of the world origin will be returned if this game object doesn't have a parent.
     *
     * @param dest Destination vector. (allocation free)
     *
     * @return Parent game object world scale.
     */
    public Vector3f getParentWorldScale(Vector3f dest) {
        // If the game object has a parent, return it's world scale
        if(getOwner().hasParent())
            return getOwner().getParent().getTransform().getWorldScale(dest);

        // Return the world origin scale
        return dest.set(1);
    }

    /**
     * Set the scale of the game object in local space.
     *
     * @param scale Game object local local.
     */
    public void setScale(Vector3f scale) {
        this.scale.set(scale);
    }

    /**
     * Compute forward direction in world space.
     *
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
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
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
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
     * This method allocates a new vector and should thus be used with caution,
     * use the allocation free variant when possible.
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
        return this.linVel;
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
        return this.angAcc;
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
        return this.angVel;
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
        this.rotation.integrate(Time.deltaTimeFloat, this.angVel.x, this.angVel.y, this.angVel.z);

        // Update position based on linear velocity
        this.position.fma(Time.deltaTimeFloat, this.linVel);
    }
}
