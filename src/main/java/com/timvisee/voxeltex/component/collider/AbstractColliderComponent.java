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

package com.timvisee.voxeltex.component.collider;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.timvisee.voxeltex.component.BaseComponent;
import com.timvisee.voxeltex.math.vector.Vector3fFactory;
import org.joml.Vector3f;

public abstract class AbstractColliderComponent extends BaseComponent implements ColliderComponentInterface {

    /**
     * Collider offset in local space.
     */
    private Vector3f offset = Vector3fFactory.zero();

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() { }

    /**
     * Get the collider offset in local space.
     *
     * @return Collider offset.
     */
    public Vector3f getOffset() {
        return this.offset;
    }

    /**
     * Set the collider offset in local space.
     *
     * @param offset Collider offset.
     */
    public void setOffset(Vector3f offset) {
        this.offset.set(offset);
    }

    /**
     * Get the shape to use with the bullet physics engine.
     *
     * @return Bullet shape.
     */
    public abstract CollisionShape getBulletShape();
}