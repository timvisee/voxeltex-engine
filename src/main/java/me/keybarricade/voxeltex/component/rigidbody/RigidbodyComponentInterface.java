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

import com.bulletphysics.dynamics.RigidBody;
import me.keybarricade.voxeltex.component.collider.AbstractColliderComponent;

public interface RigidbodyComponentInterface {

    /**
     * Get the collider component that is attached to this rigidbody.
     *
     * @return Collider component or null.
     */
    AbstractColliderComponent getColliderComponent();

    /**
     * Get the Bullet physics engine rigidbody if created.
     *
     * @return Bullet physics engine rigidbody or null.
     */
    RigidBody getPhysicsRigidbody();

    /**
     * Check whether this game object rigidbody is kinematic.
     *
     * @return True if kinematic, false if not.
     */
    boolean isKinematic();

    /**
     * Set whether this game object rigidbody is kinematic.
     *
     * @param kinematic True if kinematic, false if not.
     */
    void setKinematic(boolean kinematic);
}
