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

package me.keybarricade.voxeltex.physics;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import me.keybarricade.voxeltex.global.Time;
import me.keybarricade.voxeltex.scene.AbstractScene;

import javax.vecmath.Vector3f;

public class ScenePhysicsEngine {

    /**
     * The scene this physics engine is attached to.
     */
    private AbstractScene scene;

    /**
     * Bullet engine physics world instance.
     */
    private DynamicsWorld bulletDynamicsWorld;

    /**
     * Constructor.
     *
     * @param scene Scene.
     */
    public ScenePhysicsEngine(AbstractScene scene) {
        this.scene = scene;
    }

    /**
     * Get the scene this physics engine is attached to.
     *
     * @return Attached scene.
     */
    public AbstractScene getScene() {
        return this.scene;
    }

    /**
     * Set up the physics engine for the scene.
     */
    public void setUp() {
        // Create a DBVT broadphase to broadly check collisions using the AABB technique which ensures good performance
        BroadphaseInterface broadphase = new DbvtBroadphase();

        // Set up a collision configuration and dispatcher
        CollisionConfiguration collisionConfig = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfig);

        // Configure a constraint solver
        ConstraintSolver solver = new SequentialImpulseConstraintSolver();

        // Create and configure the Bullet physics dynamic world
        this.bulletDynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfig);
        this.bulletDynamicsWorld.setGravity(new Vector3f(0, -9.81f, 0));
    }

    /**
     * Update the physics engine state.
     * This will simulate a physics step, and will apply the physics to the game objects in the attached scene.
     */
    public void update() {
        // Simulate the next physics step
        //bulletDynamicsWorld.stepSimulation(Time.deltaTimeFloat, 1, 1f / 60f);
        bulletDynamicsWorld.stepSimulation(Time.deltaTimeFloat);
    }

    /**
     * Add a rigidbody to the physics world.
     *
     * @param rigidbody Rigidbody to add.
     */
    public void addRigidbody(RigidBody rigidbody) {
        this.bulletDynamicsWorld.addRigidBody(rigidbody);
    }

    /**
     * Remove a rigidbody from the physics world.
     *
     * @param rigidbody Rigidbody to remove.
     */
    public void removeRigidbody(RigidBody rigidbody) {
        this.bulletDynamicsWorld.removeRigidBody(rigidbody);
    }
}
