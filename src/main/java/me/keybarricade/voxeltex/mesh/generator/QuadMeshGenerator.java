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

package me.keybarricade.voxeltex.mesh.generator;

import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.mesh.RawMesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class QuadMeshGenerator extends AbstractMeshGenerator {

    /**
     * Quad orientation on the positive X axis.
     */
    public static final int ORIENTATION_X_POSITIVE = 1;

    /**
     * Quad orientation on the negative X axis.
     */
    public static final int ORIENTATION_X_NEGATIVE = 2;

    /**
     * Quad orientation on the positive Y axis.
     */
    public static final int ORIENTATION_Y_POSITIVE = 3;

    /**
     * Quad orientation on the negative Y axis.
     */
    public static final int ORIENTATION_Y_NEGATIVE = 4;

    /**
     * Quad orientation on the positive Z axis.
     */
    public static final int ORIENTATION_Z_POSITIVE = 5;

    /**
     * Quad orientation on the negative Z axis.
     */
    public static final int ORIENTATION_Z_NEGATIVE = 6;

    /**
     * Default quad orientation.
     */
    public static final int DEFAULT_ORIENTATION = ORIENTATION_Y_POSITIVE;

    /**
     * The default size of the quad.
     */
    private static final float DEFAULT_SIZE = 1.0f;

    /**
     * Raw mesh instance containing the generated mesh.
     */
    private RawMesh raw;

    /**
     * Generate a quad mesh with the default size.
     */
    public QuadMeshGenerator() {
        this(DEFAULT_SIZE);
    }

    /**
     * Generate a quad mesh with the default size.
     *
     * @param orientation Quad orientation.
     */
    public QuadMeshGenerator(int orientation) {
        this(orientation, DEFAULT_SIZE);
    }

    /**
     * Generate a quad mesh with the given size.
     *
     * @param size Size on both axis.
     */
    public QuadMeshGenerator(float size) {
        this(new Vector2f(size));
    }

    /**
     * Generate a quad mesh with the given size.
     *
     * @param orientation Quad orientation.
     * @param size Size on both axis.
     */
    public QuadMeshGenerator(int orientation, float size) {
        this(orientation, new Vector2f(size));
    }

    /**
     * Generate a quad mesh with the given size.
     *
     * @param size Size.
     */
    public QuadMeshGenerator(Vector2f size) {
        this(size, Vector3fFactory.zero());
    }

    /**
     * Generate a quad mesh with the given size.
     *
     * @param orientation Quad orientation.
     * @param size Size.
     */
    public QuadMeshGenerator(int orientation, Vector2f size) {
        this(orientation, size, Vector3fFactory.zero());
    }

    /**
     * Generate a quad mesh with the given size and offset.
     *
     * @param size Size.
     * @param offset Offset.
     */
    public QuadMeshGenerator(Vector2f size, Vector3f offset) {
        this(DEFAULT_ORIENTATION, size, offset);
    }

    /**
     * Generate a quad mesh with the given size and offset.
     *
     * @param orientation Quad orientation.
     * @param size Size.
     * @param offset Offset.
     */
    public QuadMeshGenerator(int orientation, Vector2f size, Vector3f offset) {
        // Generate and store the quad vertexes
        float[] vertexes = generateVertexes(orientation, size, offset);

        // Generate and store the quad vertexes
        float[] normals = generateNormals(orientation);

        // Generate the texture coordinates in the same order
        float[] textures = generateTextures();

        // Create and store the raw mesh
        this.raw = new RawMesh(vertexes, normals, textures);
    }

    @Override
    public RawMesh getRawMesh() {
        return this.raw;
    }

    /**
     * Generate the vertexes for a quad.
     *
     * @param orientation Quad orientation.
     * @param size Quad size.
     * @param offset Quad offset.
     *
     * @return Quad vertexes.
     */
    public static float[] generateVertexes(int orientation, Vector2f size, Vector3f offset) {
        // Calculate half of the offset values
        Vector2f halfSize = size.mul(0.5f, Vector2fFactory.identity());

        // Generate the vertexes for the quad based on the specified orientation and return them
        switch (orientation) {
            case ORIENTATION_X_POSITIVE:
            case ORIENTATION_X_NEGATIVE:
                return new float[]{
                        // Left-bottom triangle
                        offset.x, offset.y - halfSize.x, offset.z + halfSize.y,
                        offset.x, offset.y - halfSize.x, offset.z - halfSize.y,
                        offset.x, offset.y + halfSize.x, offset.z - halfSize.y,

                        // Right-top triangle
                        offset.x, offset.y + halfSize.x, offset.z - halfSize.y,
                        offset.x, offset.y + halfSize.x, offset.z + halfSize.y,
                        offset.x, offset.y - halfSize.x, offset.z + halfSize.y
                };

            case ORIENTATION_Y_POSITIVE:
            case ORIENTATION_Y_NEGATIVE:
                return new float[]{
                        // Left-bottom triangle
                        offset.x - halfSize.x, offset.y, offset.z + halfSize.y,
                        offset.x - halfSize.x, offset.y, offset.z - halfSize.y,
                        offset.x + halfSize.x, offset.y, offset.z - halfSize.y,

                        // Right-top triangle
                        offset.x + halfSize.x, offset.y, offset.z - halfSize.y,
                        offset.x + halfSize.x, offset.y, offset.z + halfSize.y,
                        offset.x - halfSize.x, offset.y, offset.z + halfSize.y
                };

            case ORIENTATION_Z_POSITIVE:
            case ORIENTATION_Z_NEGATIVE:
                return new float[]{
                        // Left-bottom triangle
                        offset.x - halfSize.x, offset.y + halfSize.y, offset.z,
                        offset.x - halfSize.x, offset.y - halfSize.y, offset.z,
                        offset.x + halfSize.x, offset.y - halfSize.y, offset.z,

                        // Right-top triangle
                        offset.x + halfSize.x, offset.y - halfSize.y, offset.z,
                        offset.x + halfSize.x, offset.y + halfSize.y, offset.z,
                        offset.x - halfSize.x, offset.y + halfSize.y, offset.z
                };
        }

        // Invalid orientation, throw exception
        throw new RuntimeException("Invalid quad orientation");
    }

    /**
     * Generate the normals for a quad.
     *
     * @param orientation Quad orientation.
     *
     * @return Quad normals.
     */
    public static float[] generateNormals(int orientation) {
        float f = 1;
        if(orientation == ORIENTATION_X_NEGATIVE || orientation == ORIENTATION_Y_NEGATIVE || orientation == ORIENTATION_Z_NEGATIVE)
            f = -1;

        // Generate the vertexes for the quad based on the specified orientation and return them
        switch (orientation) {
            case ORIENTATION_X_POSITIVE:
            case ORIENTATION_X_NEGATIVE:
                return new float[]{
                        // Left-bottom triangle
                        f * 1, 0, 0,
                        f * 1, 0, 0,
                        f * 1, 0, 0,

                        // Right-top triangle
                        f * 1, 0, 0,
                        f * 1, 0, 0,
                        f * 1, 0, 0
                };

            case ORIENTATION_Y_POSITIVE:
            case ORIENTATION_Y_NEGATIVE:
                return new float[]{
                        // Left-bottom triangle
                        0, f * 1, 0,
                        0, f * 1, 0,
                        0, f * 1, 0,

                        // Right-top triangle
                        0, f * 1, 0,
                        0, f * 1, 0,
                        0, f * 1, 0
                };

            case ORIENTATION_Z_POSITIVE:
            case ORIENTATION_Z_NEGATIVE:
                return new float[]{
                        // Left-bottom triangle
                        0, 0, f * 1,
                        0, 0, f * 1,
                        0, 0, f * 1,

                        // Right-top triangle
                        0, 0, f * 1,
                        0, 0, f * 1,
                        0, 0, f * 1
                };
        }

        // Invalid orientation, throw exception
        throw new RuntimeException("Invalid quad orientation");
    }

    /**
     * Generate the texture coordinates for a quad mesh.
     *
     * @return Textures.
     */
    public static float[] generateTextures() {
        return new float[]{
                // Left-bottom triangle
                0, 1,
                0, 0,
                1, 0,

                // Right-top triangle
                1, 0,
                1, 1,
                0, 1
        };
    }
}
