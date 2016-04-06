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

package me.keybarricade.voxeltex.component.movement;

import com.bulletphysics.dynamics.RigidBody;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.global.Input;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.math.vector.Vector3fUtil;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class WasdPhysicsMovementComponent extends AbstractMovementComponent {

    /**
     * Movement speed.
     */
    private float movementIntensity = 6.0f;

    @Override
    public void update() {
        // Get the linear velocity of the object, and set it back to it's identity
        Vector3f target = Vector3fFactory.identity();

        // TODO: Make sure moving sideways has the same speed!

        // Determine the linear velocity based on user input
        target.add(
                (Input.isKeyDown(GLFW_KEY_D) ? movementIntensity : 0) + (Input.isKeyDown(GLFW_KEY_A) ? -movementIntensity : 0),
                0.0f,
                (Input.isKeyDown(GLFW_KEY_W) ? -movementIntensity : 0) + (Input.isKeyDown(GLFW_KEY_S) ? movementIntensity : 0)
        );

        // Get the rigidbody
        RigidBody rigidbody = getComponent(RigidbodyComponent.class).getPhysicsRigidbody();

        rigidbody.clearForces();

        // TODO: Use buffering!
        rigidbody.applyCentralForce(
                    Vector3fUtil.toVecmath(target, new javax.vecmath.Vector3f())
        );
    }

    /**
     * Get the moment speed.
     *
     * @return Movement speed.
     */
    public float getMovementIntensity() {
        return movementIntensity;
    }

    /**
     * Set the movement speed.
     *
     * @param movementIntensity Movement speed.
     */
    public void setMovementIntensity(float movementIntensity) {
        this.movementIntensity = movementIntensity;
    }
}
