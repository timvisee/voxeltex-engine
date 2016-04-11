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

package com.timvisee.voxeltex.component.collider.primitive;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.timvisee.voxeltex.component.collider.AbstractColliderComponent;

public class SphereColliderComponent extends AbstractColliderComponent {

    /**
     * Radius of the sphere collider.
     */
    private float radius = 1.0f;

    /**
     * The bullet physics engine shape representation.
     */
    private SphereShape bulletShape = null;

    /**
     * Constructor.
     */
    public SphereColliderComponent() { }

    /**
     * Constructor.
     *
     * @param radius Sphere radius.
     */
    public SphereColliderComponent(float radius) {
        this.radius = radius;
    }

    /**
     * Get the radius of the sphere collider.
     *
     * @return Sphere radius.
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * Set the radius of the sphere collider.
     *
     * @param radius Sphere radius.
     */
    public void setRadius(float radius) {
        // Set the radius
        this.radius = radius;

        // Reset the current bullet shape instance to generate a new one when the shape is acquired
        this.bulletShape = null;
    }

    @Override
    public CollisionShape getBulletShape() {
        // Make sure the shape has been configured
        if(this.bulletShape == null)
            this.bulletShape = new SphereShape(this.radius);

        // Return the shape
        return this.bulletShape;
    }
}
