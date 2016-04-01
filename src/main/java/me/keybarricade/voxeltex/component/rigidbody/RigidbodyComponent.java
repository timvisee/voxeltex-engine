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

package me.keybarricade.voxeltex.component.rigidbody;

import com.bulletphysics.collision.dispatch.CollisionFlags;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import me.keybarricade.voxeltex.component.collider.AbstractColliderComponent;
import me.keybarricade.voxeltex.math.matrix.Matrix4fUtil;
import me.keybarricade.voxeltex.physics.ScenePhysicsEngine;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public class RigidbodyComponent extends AbstractRigidbodyComponent {

    /**
     * Rigid body used with the Bullet physics engine.
     */
    private RigidBody physicsRigidbody = null;

    /**
     * The collider component that is used for this rigidbody.
     */
    private AbstractColliderComponent colliderComponent = null;

    /**
     * Flag which defines whether to initialize the rigidbody in kinematic mode or not.
     */
    private boolean initKinematic = false;

    /**
     * Shared lock for the temporary shared field usage.
     * These temporary fields are used to minimize object allocation at runtime,
     * which minimizes GC and greatly improved performance.
     * This ensures the temporary fields aren't used in multiple spots at the same time,
     * because that may cause unwanted behaviour.
     */
    private static final Object tempSharedLock = new Object();

    /**
     * Temporary transform, used for transform representations.
     * Using and recycling this temporary transform minimizes object allocation, resulting in better performance.
     */
    private static final Transform tempTransform = new Transform();

    /**
     * Temporary VecMath matrix, used for transform calculations.
     * Using and recycling this temporary matrix minimizes object allocation, resulting in better performance.
     */
    private static final Matrix4f tempMatrixVecmath = new Matrix4f();

    /**
     * Temporary JOML matrix, used for transform calculations.
     * Using and recycling this temporary matrix minimizes object allocation, resulting in better performance.
     */
    private static final org.joml.Matrix4f tempMatrixJoml = new org.joml.Matrix4f();

    /**
     * Constructor.
     */
    public RigidbodyComponent() { }

    /**
     * Constructor.
     *
     * @param kinematic True if kinematic, false if not.
     */
    public RigidbodyComponent(boolean kinematic) {
        this.initKinematic = kinematic;
    }

    @Override
    public void create() { }

    @Override
    public void start() {
        // Create the physics object
        createPhysicsRigidbody();
    }

    @Override
    public synchronized void update() {
        // Update the transform of the game object according to the physics object if available
        if(this.physicsRigidbody != null) {
            // Ensure we aren't using the temporary transform and matrix in multiple spots at the same time
            synchronized(tempSharedLock) {
                // Get the world transform matrix of the physics object
                this.physicsRigidbody.getWorldTransform(tempTransform);

                // Convert the transformation matrix to a JOML matrix
                Matrix4fUtil.toJoml(tempTransform.getMatrix(tempMatrixVecmath), tempMatrixJoml);

                // Update the game object transform
                tempMatrixJoml.getTranslation(getTransform().getPosition());
                tempMatrixJoml.getUnnormalizedRotation(getTransform().getRotation());
                tempMatrixJoml.getScale(getTransform().getScale());
            }
        }
    }

    @Override
    public void destroy() { }

    /**
     * Create the actual rigidbody for this component.
     * This should be called when the scene the game object is in is started.
     */
    private void createPhysicsRigidbody() {
        // Make sure a collider component is attached
        if(this.colliderComponent == null) {
            // Find a suitable collider component
            this.colliderComponent = getComponent(AbstractColliderComponent.class);

            // Show a warning if no collision component was found
            if(this.colliderComponent == null) {
                System.out.println(getOwner() + " doesn't have a suitable collider component to use with the rigidbody");
                return;
            }
        }

        // Get the physics engine
        ScenePhysicsEngine physicsEngine = getScene().getPhysicsEngine();

        // Get the collision shape
        CollisionShape collisionShape = this.colliderComponent.getBulletShape();

        // Define the motion state variable
        MotionState motionState;

        // Make sure the temporary fields being used aren't used in multiple spots at the same time
        synchronized(tempSharedLock) {
            // Get the transform object for the collision shape
            tempTransform.set(Matrix4fUtil.toVecmath(getTransform().getWorldMatrix(tempMatrixJoml), tempMatrixVecmath));

            // Create the motion state for the game object
            motionState = new DefaultMotionState(tempTransform);
        }

        // Calculate and define the inertia
        // TODO: Use proper inertia here!
        Vector3f inertia = new Vector3f(0, 0, 0);
        collisionShape.calculateLocalInertia(1f, inertia);

        // Create and configure the rigidbody construction info
        RigidBodyConstructionInfo constructionInfo = new RigidBodyConstructionInfo(1f, motionState, collisionShape, inertia);
        // TODO: Use proper restitution and angular damping here!
        constructionInfo.restitution = 0.5f;
        constructionInfo.angularDamping = 0.1f;

        // Create the rigidbody for the game object
        this.physicsRigidbody = new RigidBody(constructionInfo);
        // TODO: Determine what the activation state should be!
        this.physicsRigidbody.setActivationState(CollisionObject.DISABLE_DEACTIVATION);

        // Set whether the game rigidbody is kinematic or not
        setKinematic(this.initKinematic);

        // Set the user pointer to the game object
        this.physicsRigidbody.setUserPointer(getOwner());

        // Add the rigidbody to the physics engine world
        physicsEngine.addRigidbody(this.physicsRigidbody);
    }

    @Override
    public AbstractColliderComponent getColliderComponent() {
        return this.colliderComponent;
    }

    @Override
    public RigidBody getPhysicsRigidbody() {
        return this.physicsRigidbody;
    }

    @Override
    public boolean isKinematic() {
        return this.physicsRigidbody.isKinematicObject();
    }

    @Override
    public void setKinematic(boolean kinematic) {
        setCollisionFlag(CollisionFlags.KINEMATIC_OBJECT, kinematic);
    }

    /**
     * Set whether the given collision flag is set or not.
     *
     * @param collisionFlag Collision flag to configure.
     * @param enabled True to enable the flag, false to disable.
     */
    private void setCollisionFlag(int collisionFlag, boolean enabled) {
        if(enabled)
            this.physicsRigidbody.setCollisionFlags(this.physicsRigidbody.getCollisionFlags() | collisionFlag);
        else
            this.physicsRigidbody.setCollisionFlags(this.physicsRigidbody.getCollisionFlags() & ~collisionFlag);
    }
}
