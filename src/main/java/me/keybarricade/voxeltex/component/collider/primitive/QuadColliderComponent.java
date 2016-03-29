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

package me.keybarricade.voxeltex.component.collider.primitive;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import me.keybarricade.voxeltex.component.collider.AbstractColliderComponent;
import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.mesh.generator.QuadMeshGenerator;
import org.joml.Vector2f;

import javax.vecmath.Vector3f;

public class QuadColliderComponent extends AbstractColliderComponent {

    /**
     * Box collider size.
     */
    private Vector2f size = Vector2fFactory.one();

    /**
     * The orientation of the quad.
     */
    private int orientation = QuadMeshGenerator.DEFAULT_ORIENTATION;

    /**
     * Thickness of the quad.
     */
    private float thickness = 0.01f;

    /**
     * Bullet physics engine shape representation.
     */
    private BoxShape bulletShape = null;

    /**
     * Constructor.
     *
     * @param orientation Quad orientation.
     * @param size Quad size.
     * @param thickness Quad collider thickness.
     */
    public QuadColliderComponent(int orientation, Vector2f size, float thickness) {
        this.orientation = orientation;
        this.size.set(size);
        this.thickness = thickness;
    }

    /**
     * Get the size of the collider.
     *
     * @return Collider size.
     */
    public Vector2f getSize() {
        return this.size;
    }

    /**
     * Set the size of the collider.
     *
     * @param size Collider size.
     */
    public void setSize(Vector2f size) {
        this.size.set(size);
    }

    /**
     * Get the quad orientation.
     *
     * @return Quad orientation.
     */
    public int getOrientation() {
        return this.orientation;
    }

    /**
     * Set the quad orientation.
     *
     * @param orientation Quad orientation.
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    /**
     * Get the thickness of the quad.
     *
     * @return Quad thickness.
     */
    public float getThickness() {
        return this.thickness;
    }

    /**
     * Quad thickness.
     *
     * @param thickness Quad thickness.
     */
    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    @Override
    public CollisionShape getBulletShape() {
        // Make sure the shape is configured
        if(this.bulletShape == null) {
            // Determine the size of the shape
            Vector3f shapeSize = new Vector3f();
            switch(this.orientation) {
                case QuadMeshGenerator.ORIENTATION_X_POSITIVE:
                case QuadMeshGenerator.ORIENTATION_X_NEGATIVE:
                    shapeSize.x = this.thickness / 2.0f;
                    shapeSize.y = this.size.x / 2.0f;
                    shapeSize.z = this.size.y / 2.0f;
                    break;

                case QuadMeshGenerator.ORIENTATION_Y_POSITIVE:
                case QuadMeshGenerator.ORIENTATION_Y_NEGATIVE:
                    shapeSize.x = this.size.x / 2.0f;
                    shapeSize.y = this.thickness / 2.0f;
                    shapeSize.z = this.size.y / 2.0f;
                    break;

                case QuadMeshGenerator.ORIENTATION_Z_POSITIVE:
                case QuadMeshGenerator.ORIENTATION_Z_NEGATIVE:
                    shapeSize.x = this.size.x / 2.0f;
                    shapeSize.y = this.size.y / 2.0f;
                    shapeSize.z = this.thickness / 2.0f;
                    break;
            }

            // Construct the shape
            this.bulletShape = new BoxShape(shapeSize);
        }

        // Return the shape
        return this.bulletShape;
    }
}
