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

package com.timvisee.voxeltex.structure.gameobject;

import com.timvisee.voxeltex.math.quaternion.QuaternionfFactory;
import com.timvisee.voxeltex.math.vector.Vector3fFactory;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import static org.junit.Assert.assertEquals;

public class TransformTest {

    /**
     * Parent game object. Parent of the owner game object instance.
     */
    protected GameObject parent;

    /**
     * Owner game object. Owner object of the transform instance.
     */
    private GameObject owner;

    /**
     * Position reference of the parent game object.
     */
    private final Vector3f parentPosition = new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());

    /**
     * Position reference of the owner game object.
     */
    private final Vector3f ownerPosition = new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());

    /**
     * The world position of the owner.
     */
    private final Vector3f ownerWorldPosition = Vector3fFactory.identity();

    /**
     * Linear acceleration reference of the owner game object.
     */
    private final Vector3f ownerLinearAcceleration = new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());

    /**
     * Linear velocity reference of the owner game object.
     */
    private final Vector3f ownerLinearVelocity = new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());

    /**
     * Angular acceleration reference of the owner game object.
     */
    private final Vector3f ownerAngularAcceleration = new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());

    /**
     * Angular velocity reference of the owner game object.
     */
    private final Vector3f ownerAngularVelocity = new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());

    /**
     * Create a temporary object which is used as shared lock for other temporary fields.
     */
    private final Object tempSharedLock = new Object();

    /**
     * Create a temporary vector which is used for some vector calculations.
     * This temporary vector minimized object allocation at runtime and thus greatly improves performance.
     */
    private final Vector3f tempVector3f = Vector3fFactory.identity();

    /**
     * Create a second temporary vector which is used for some vector calculations.
     * This temporary vector minimized object allocation at runtime and thus greatly improves performance.
     */
    private final Vector3f tempVector3f2 = Vector3fFactory.identity();

    /**
     * Create a temporary matrix which is used for some matrix calculations.
     * This temporary matrix minimized object allocation at runtime and thus greatly improves performance.
     */
    private final Matrix4f tempMatrix4f = new Matrix4f();

    /**
     * Create a temporary quaternion which is used for some quaternion calculations.
     * This temporary quaternion minimized object allocation at runtime and thus greatly improves performance.
     */
    private final Quaternionf tempQuaternionf = QuaternionfFactory.identity();

    /**
     * Create a second temporary quaternion which is used for some quaternion calculations.
     * This temporary quaternion minimized object allocation at runtime and thus greatly improves performance.
     */
    private final Quaternionf tempQuaternionf2 = QuaternionfFactory.identity();

    /**
     * Transform instance we're testing on.
     */
    protected Transform transform;

    @org.junit.Before
    public void setUp() throws Exception {
        // Create a game object that will be parent
        this.parent = new GameObject("Parent");

        // Create a game object that will be owner of the transform instances
        this.owner = new GameObject("Owner");
        this.parent.addChild(this.owner);

        // Set the position of both objects based on the reference
        this.parent.getTransform().setPosition(new Vector3f(this.parentPosition));
        this.owner.getTransform().setPosition(new Vector3f(this.ownerPosition));

        // Set the linear and angular velocity and acceleration of the owner
        this.owner.getTransform().setLinearAcceleration(this.ownerLinearAcceleration);
        this.owner.getTransform().setLinearVelocity(this.ownerLinearVelocity);
        this.owner.getTransform().setAngularAcceleration(this.ownerAngularAcceleration);
        this.owner.getTransform().setAngularVelocity(this.ownerAngularVelocity);

        // Determine the world position of the owner
        this.ownerWorldPosition.add(this.parentPosition).add(this.ownerPosition);

        // Create the transform instance
        this.transform = this.owner.getTransform();
    }

    @org.junit.After
    public void tearDown() throws Exception { }

    /**
     * Assert the position of two vectors.
     *
     * @param vector First vector.
     * @param other Second vector.
     */
    private void assertVector3f(Vector3f vector, Vector3f other) {
        assertEquals(vector.x, other.x, 0.00000001f);
        assertEquals(vector.y, other.y, 0.00000001f);
        assertEquals(vector.z, other.z, 0.00000001f);
    }

    /**
     * Assert the position of two quaternions.
     *
     * @param quaternion First quaternion.
     * @param other Second quaternion.
     */
    private void assertQuaternionf(Quaternionf quaternion, Quaternionf other) {
        assertEquals(quaternion.x, other.x, 0.0001f);
        assertEquals(quaternion.y, other.y, 0.0001f);
        assertEquals(quaternion.z, other.z, 0.0001f);
        assertEquals(quaternion.w, other.w, 0.0001f);
    }

    @org.junit.Test
    public void getOwner() throws Exception {
        // Make sure the owners are equal
        assertEquals(this.transform.getOwner(), this.owner);
    }

    @org.junit.Test
    public void getLocalMatrix() throws Exception {
        // Get the local matrix
        Matrix4f localMatrix = this.transform.getLocalMatrix();

        // Compare the position to the reference
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            assertVector3f(localMatrix.getTranslation(this.tempVector3f), this.ownerPosition);
        }
    }

    @org.junit.Test
    public void getLocalMatrixAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Get the local matrix
            this.transform.getLocalMatrix(this.tempMatrix4f);

            // Compare the position to the reference
            assertVector3f(this.tempMatrix4f.getTranslation(this.tempVector3f), this.ownerPosition);
        }
    }

    @org.junit.Test
    public void addLocalMatrix() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Reset the base matrix to it's identity
            this.tempMatrix4f.identity();

            // Add the local matrix
            this.transform.addLocalMatrix(this.tempMatrix4f);

            // Compare the position to the reference
            assertVector3f(this.tempMatrix4f.getTranslation(this.tempVector3f), this.ownerPosition);
        }
    }

    @org.junit.Test
    public void getWorldMatrix() throws Exception {
        // Get the local matrix
        Matrix4f worldMatrix = this.transform.getWorldMatrix();

        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Compare the position to the reference
            assertVector3f(worldMatrix.getTranslation(this.tempVector3f), this.ownerWorldPosition);
        }
    }

    @org.junit.Test
    public void getWorldMatrixAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Get the world matrix
            this.transform.getWorldMatrix(this.tempMatrix4f);

            // Compare the position to the reference
            assertVector3f(this.tempMatrix4f.getTranslation(this.tempVector3f), this.ownerWorldPosition);
        }
    }

    @org.junit.Test
    public void addWorldMatrix() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Reset the base matrix to it's identity
            this.tempMatrix4f.identity();

            // Add the world matrix
            this.transform.addWorldMatrix(this.tempMatrix4f);

            // Compare the position to the reference
            assertVector3f(this.tempMatrix4f.getTranslation(this.tempVector3f), this.ownerWorldPosition);
        }
    }

    @org.junit.Test
    public void getParentLocalMatrix() throws Exception {
        // Get the local matrix
        Matrix4f localMatrix = this.transform.getParentLocalMatrix();

        // Compare the position to the reference
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            assertVector3f(localMatrix.getTranslation(this.tempVector3f), this.parentPosition);
        }
    }

    @org.junit.Test
    public void getParentLocalMatrixAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Get the local matrix
            this.transform.getParentLocalMatrix(this.tempMatrix4f);

            // Compare the position to the reference
            assertVector3f(this.tempMatrix4f.getTranslation(this.tempVector3f), this.parentPosition);
        }
    }

    @org.junit.Test
    public void getParentWorldMatrix() throws Exception {
        // Get the local matrix
        Matrix4f worldMatrix = this.transform.getParentWorldMatrix();

        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Compare the position to the reference
            assertVector3f(worldMatrix.getTranslation(this.tempVector3f), this.parentPosition);
        }
    }

    @org.junit.Test
    public void getParentWorldMatrixAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Get the world matrix
            this.transform.getParentWorldMatrix(this.tempMatrix4f);

            // Compare the position to the reference
            assertVector3f(this.tempMatrix4f.getTranslation(this.tempVector3f), this.parentPosition);
        }
    }

    @org.junit.Test
    public void getPosition() throws Exception {
        // Compare the position to the reference
        assertVector3f(this.transform.getPosition(), this.ownerPosition);
    }

    @org.junit.Test
    public void getWorldPosition() throws Exception {
        // Compare the position to the reference
        assertVector3f(this.transform.getWorldPosition(), this.ownerWorldPosition);
    }

    @org.junit.Test
    public void getWorldPositionAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Compare the position to the reference
            assertVector3f(this.transform.getWorldPosition(this.tempVector3f), this.ownerWorldPosition);
        }
    }

    @org.junit.Test
    public void getParentWorldPosition() throws Exception {
        // Compare the position to the reference
        assertVector3f(this.transform.getParentWorldPosition(), this.parentPosition);
    }

    @org.junit.Test
    public void getParentWorldPosition1AllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Compare the position to the reference
            assertVector3f(this.transform.getParentWorldPosition(this.tempVector3f), this.parentPosition);
        }
    }

    @org.junit.Test
    public void setPositionVector() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Create a new position
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random());

            // Set the position
            this.transform.setPosition(this.tempVector3f);

            // Compare the position to the reference
            assertVector3f(this.transform.getPosition(), this.tempVector3f);

            // Reset the position to it's initial
            this.transform.setPosition(this.ownerPosition);
        }
    }

    @org.junit.Test
    public void setPositionFloats() throws Exception {
        // Define a new position
        final float x = (float) Math.random();
        final float y = (float) Math.random();
        final float z = (float) Math.random();

        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Set the position
            this.transform.setPosition(x, y, z);

            // Mirror the position with the temporary vector
            this.tempVector3f.set(x, y, z);

            // Compare the position to the reference
            assertVector3f(this.transform.getPosition(), this.tempVector3f);

            // Reset the position to it's initial
            this.transform.setPosition(this.ownerPosition);
        }
    }

    @org.junit.Test
    public void getRotation() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Set the temporary quaternion to it's identity
            this.tempQuaternionf.identity();

            // Compare the position to the identity
            assertQuaternionf(this.transform.getRotation(), this.tempQuaternionf);

            // Randomize the quaternion
            this.tempQuaternionf.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the rotation
            this.transform.setRotation(this.tempQuaternionf);

            // Compare the position to the reference
            assertQuaternionf(this.transform.getRotation(), this.tempQuaternionf);

            // Reset the rotation to it's identity
            this.transform.setRotation(this.tempQuaternionf.identity());
        }
    }

    @org.junit.Test
    public void getWorldRotation() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the quaternion
            this.tempQuaternionf.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the rotation
            this.parent.getTransform().setRotation(this.tempQuaternionf);

            // Compare the position to the reference
            assertQuaternionf(this.transform.getWorldRotation(), this.tempQuaternionf);

            // Reset the rotation to it's identity
            this.parent.getTransform().setRotation(this.tempQuaternionf.identity());
        }
    }

    @org.junit.Test
    public void getWorldRotationAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the quaternion
            this.tempQuaternionf.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the rotation
            this.parent.getTransform().setRotation(this.tempQuaternionf);

            // Compare the position to the reference
            assertQuaternionf(this.transform.getWorldRotation(this.tempQuaternionf2), this.tempQuaternionf);

            // Reset the rotation to it's identity
            this.parent.getTransform().setRotation(this.tempQuaternionf.identity());
        }
    }

    @org.junit.Test
    public void getParentWorldRotation() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the quaternion
            this.tempQuaternionf.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the rotation
            this.parent.getTransform().setRotation(this.tempQuaternionf);

            // Compare the position to the reference
            assertQuaternionf(this.transform.getParentWorldRotation(), this.tempQuaternionf);

            // Reset the rotation to it's identity
            this.parent.getTransform().setRotation(this.tempQuaternionf.identity());
        }
    }

    @org.junit.Test
    public void getParentWorldRotationAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the quaternion
            this.tempQuaternionf.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the rotation
            this.parent.getTransform().setRotation(this.tempQuaternionf);

            // Compare the position to the reference
            assertQuaternionf(this.transform.getParentWorldRotation(this.tempQuaternionf2), this.tempQuaternionf);

            // Reset the rotation to it's identity
            this.parent.getTransform().setRotation(this.tempQuaternionf.identity());
        }
    }

    @org.junit.Test
    public void setRotation() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the quaternions
            this.tempQuaternionf.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();
            this.tempQuaternionf2.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the first rotation
            this.transform.setRotation(this.tempQuaternionf);

            // Compare the position to the first reference
            assertQuaternionf(this.transform.getRotation(), this.tempQuaternionf);

            // Set the second rotation
            this.transform.setRotation(this.tempQuaternionf2);

            // Compare the position to the second reference
            assertQuaternionf(this.transform.getRotation(), this.tempQuaternionf2);

            // Reset the rotation to it's identity
            this.transform.setRotation(this.tempQuaternionf.identity());
        }
    }

    @org.junit.Test
    public void getScale() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Set the temporary vector to it's identity
            this.tempVector3f.set(1f);

            // Compare the position to the identity
            assertVector3f(this.transform.getScale(), this.tempVector3f);

            // Randomize the vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the scale
            this.transform.setScale(this.tempVector3f);

            // Compare the position to the reference
            assertVector3f(this.transform.getScale(), this.tempVector3f);

            // Reset the scale to it's identity
            this.transform.setScale(this.tempVector3f.set(1f));
        }
    }

    @org.junit.Test
    public void getWorldScale() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the scale
            this.transform.setScale(this.tempVector3f);

            // Compare the position to the reference
            assertVector3f(this.transform.getWorldScale(), this.tempVector3f);

            // Reset the scale to it's identity
            this.transform.setScale(this.tempVector3f.set(1f));
        }
    }

    @org.junit.Test
    public void getWorldScaleAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the scale
            this.transform.setScale(this.tempVector3f);

            // Compare the position to the reference
            assertVector3f(this.transform.getWorldScale(this.tempVector3f2), this.tempVector3f);

            // Reset the scale to it's identity
            this.transform.setScale(this.tempVector3f.set(1f));
        }
    }

    @org.junit.Test
    public void getParentWorldScale() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the scale
            this.parent.getTransform().setScale(this.tempVector3f);

            // Compare the position to the reference
            assertVector3f(this.transform.getParentWorldScale(), this.tempVector3f);

            // Reset the scale to it's identity
            this.parent.getTransform().setScale(this.tempVector3f.set(1f));
        }
    }

    @org.junit.Test
    public void getParentWorldScaleAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Randomize the vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random()).normalize();

            // Set the scale
            this.parent.getTransform().setScale(this.tempVector3f);

            // Compare the position to the reference
            assertVector3f(this.transform.getParentWorldScale(this.tempVector3f2), this.tempVector3f);

            // Reset the scale to it's identity
            this.parent.getTransform().setScale(this.tempVector3f.set(1f));
        }
    }

    @org.junit.Test
    public void setScaleVector() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Define a new scale
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random());

            // Set the scale
            this.transform.setScale(this.tempVector3f);

            // Mirror the scale with the temporary vector
            this.tempVector3f2.set(this.tempVector3f);

            // Compare the scale to the reference
            assertVector3f(this.transform.getScale(), this.tempVector3f2);

            // Reset the scale to it's initial
            this.transform.setPosition(this.tempVector3f2.set(1f));
        }
    }

    @org.junit.Test
    public void setScaleFloats() throws Exception {
        // Define a new scale
        final float x = (float) Math.random();
        final float y = (float) Math.random();
        final float z = (float) Math.random();

        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Set the scale
            this.transform.setScale(x, y, z);

            // Mirror the scale with the temporary vector
            this.tempVector3f.set(x, y, z);

            // Compare the scale to the reference
            assertVector3f(this.transform.getScale(), this.tempVector3f);

            // Reset the scale to it's initial
            this.transform.setPosition(this.tempVector3f.set(1f));
        }
    }

    @org.junit.Test
    public void forward() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Define the target vector
            this.tempVector3f.set(0f, 0f, -1f);

            // Compare the normalized vector to the reference
            assertVector3f(this.transform.forward().normalize(), this.tempVector3f);
        }
    }

    @org.junit.Test
    public void forwardAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Define the target vector
            this.tempVector3f.set(0f, 0f, -1f);

            // Compare the normalized vector to the reference
            assertVector3f(this.transform.forward(this.tempVector3f2).normalize(), this.tempVector3f);
        }
    }

    @org.junit.Test
    public void right() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Define the target vector
            this.tempVector3f.set(1f, 0f, 0f);

            // Compare the normalized vector to the reference
            assertVector3f(this.transform.right().normalize(), this.tempVector3f);
        }
    }

    @org.junit.Test
    public void rightAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Define the target vector
            this.tempVector3f.set(1f, 0f, 0f);

            // Compare the normalized vector to the reference
            assertVector3f(this.transform.right(this.tempVector3f2).normalize(), this.tempVector3f);
        }
    }

    @org.junit.Test
    public void up() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Define the target vector
            this.tempVector3f.set(0f, 1f, 0f);

            // Compare the normalized vector to the reference
            assertVector3f(this.transform.up().normalize(), this.tempVector3f);
        }
    }

    @org.junit.Test
    public void upAllocationFree() throws Exception {
        // Make sure we aren't using the temporary variables in multiple spots at the same time
        synchronized(this.tempSharedLock) {
            // Define the target vector
            this.tempVector3f.set(0f, 1f, 0f);

            // Compare the normalized vector to the reference
            assertVector3f(this.transform.up(this.tempVector3f2).normalize(), this.tempVector3f);
        }
    }

    @org.junit.Test
    public void getLinearAcceleration() throws Exception {
        // Make sure we aren't configuring this property and asserting it somewhere else at the same time
        synchronized(this.tempSharedLock) {
            // Compare the linear acceleration with the reference vector
            assertVector3f(this.transform.getLinearAcceleration(), this.ownerLinearAcceleration);
        }
    }

    @org.junit.Test
    public void setLinearAcceleration() throws Exception {
        // Make sure we aren't configuring this property and asserting it somewhere else at the same time
        synchronized(this.tempSharedLock) {
            // Create a new vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random());

            // Set the property (without keeping the reference alive)
            this.transform.setLinearAcceleration(this.tempVector3f2.set(this.tempVector3f));

            // Compare the property with the reference vector
            assertVector3f(this.transform.getLinearAcceleration(), this.tempVector3f);

            // Reset the property to it's initial
            this.transform.setLinearAcceleration(this.ownerLinearAcceleration);
        }
    }

    @org.junit.Test
    public void getLinearVelocity() throws Exception {
        // Make sure we aren't configuring this property and asserting it somewhere else at the same time
        synchronized(this.tempSharedLock) {
            // Compare the linear velocity with the reference vector
            assertVector3f(this.transform.getLinearVelocity(), this.ownerLinearVelocity);
        }
    }

    @org.junit.Test
    public void setLinearVelocity() throws Exception {
        // Make sure we aren't configuring this property and asserting it somewhere else at the same time
        synchronized(this.tempSharedLock) {
            // Create a new vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random());

            // Set the property (without keeping the reference alive)
            this.transform.setLinearVelocity(this.tempVector3f2.set(this.tempVector3f));

            // Compare the property with the reference vector
            assertVector3f(this.transform.getLinearVelocity(), this.tempVector3f);

            // Reset the property to it's initial
            this.transform.setLinearVelocity(this.ownerLinearVelocity);
        }
    }

    @org.junit.Test
    public void getAngularAcceleration() throws Exception {
        // Make sure we aren't configuring this property and asserting it somewhere else at the same time
        synchronized(this.tempSharedLock) {
            // Compare the angular acceleration with the reference vector
            assertVector3f(this.transform.getAngularAcceleration(), this.ownerAngularAcceleration);
        }
    }

    @org.junit.Test
    public void setAngularAcceleration() throws Exception {
        // Make sure we aren't configuring this property and asserting it somewhere else at the same time
        synchronized(this.tempSharedLock) {
            // Create a new vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random());

            // Set the property (without keeping the reference alive)
            this.transform.setAngularAcceleration(this.tempVector3f2.set(this.tempVector3f));

            // Compare the property with the reference vector
            assertVector3f(this.transform.getAngularAcceleration(), this.tempVector3f);

            // Reset the property to it's initial
            this.transform.setAngularAcceleration(this.ownerAngularAcceleration);
        }
    }

    @org.junit.Test
    public void getAngularVelocity() throws Exception {
        // Make sure we aren't configuring this property and asserting it somewhere else at the same time
        synchronized(this.tempSharedLock) {
            // Compare the angular velocity with the reference vector
            assertVector3f(this.transform.getAngularVelocity(), this.ownerAngularVelocity);
        }
    }

    @org.junit.Test
    public void setAngularVelocity() throws Exception {
        // Make sure we aren't configuring this property and asserting it somewhere else at the same time
        synchronized(this.tempSharedLock) {
            // Create a new vector
            this.tempVector3f.set((float) Math.random(), (float) Math.random(), (float) Math.random());

            // Set the property (without keeping the reference alive)
            this.transform.setAngularVelocity(this.tempVector3f2.set(this.tempVector3f));

            // Compare the property with the reference vector
            assertVector3f(this.transform.getAngularVelocity(), this.tempVector3f);

            // Reset the property to it's initial
            this.transform.setAngularVelocity(this.ownerAngularVelocity);
        }
    }

    @org.junit.Test
    public void update() throws Exception {
        // Make sure this doesn't throw any exceptions
        this.transform.update();
    }
}