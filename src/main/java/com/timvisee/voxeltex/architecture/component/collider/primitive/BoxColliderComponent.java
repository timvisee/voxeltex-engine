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

package com.timvisee.voxeltex.architecture.component.collider.primitive;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.timvisee.voxeltex.architecture.component.collider.AbstractColliderComponent;
import com.timvisee.voxeltex.util.math.vector.Vector3fFactory;
import org.joml.Vector3f;

public class BoxColliderComponent extends AbstractColliderComponent {

    /**
     * Box collider size.
     */
    private Vector3f size = Vector3fFactory.one();

    /**
     * Bullet physics engine shape representation.
     */
    private BoxShape bulletShape = null;

    /**
     * Constructor.
     *
     * @param size Box collider size.
     */
    public BoxColliderComponent(Vector3f size) {
        this.size.set(size);
    }

    /**
     * Get the size of the collider.
     *
     * @return Collider size.
     */
    public Vector3f getSize() {
        return this.size;
    }

    /**
     * Set the size of the collider.
     *
     * @param size Collider size.
     */
    public void setSize(Vector3f size) {
        this.size.set(size);
    }

    @Override
    public CollisionShape getBulletShape() {
        // Make sure the bullet shape has been configured
        if(this.bulletShape == null)
            this.bulletShape = new BoxShape(
                    new javax.vecmath.Vector3f(
                            size.x / 2.0f,
                            size.y / 2.0f,
                            size.y / 2.0f
                    )
            );

        // Return the shape
        return this.bulletShape;
    }
}
