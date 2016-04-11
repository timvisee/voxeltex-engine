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

package com.timvisee.voxeltex.mesh;

public class RawMesh {

    /**
     * Number of axis used in the vertex specification.
     */
    public static final int VERTEX_AXIS_COUNT = 3;

    /**
     * Number of axis used in the normal specification.
     */
    public static final int NORMAL_AXIS_COUNT = 3;

    /**
     * Number of axis used in the texture coordinate specification.
     */
    public static final int TEXTURE_AXIS_COUNT = 2;

    /**
     * Vertexes of this mesh.
     */
    private float[] vertexes = new float[0];

    /**
     * Normal coordinates of this mesh.
     */
    private float[] normals = new float[0];

    /**
     * Texture coordinates of this mesh.
     */
    private float[] textures = new float[0];

    /**
     * Constructor.
     *
     * @param vertexes The vertexes data.
     */
    public RawMesh(float[] vertexes) {
        this(vertexes, new float[0], new float[0]);
    }

    /**
     * Constructor.
     *
     * @param vertexes The vertexes data.
     * @param normals The normals data.
     * @param textures The textures data.
     */
    public RawMesh(float[] vertexes, float[] normals, float[] textures) {
        // Set the fields
        this.vertexes = vertexes;
        this.normals = normals;
        this.textures = textures;
    }

    /**
     * Get the number of vertexes in this this mesh.
     *
     * @return Vertex count.
     */
    public int getVertexCount() {
        return this.vertexes.length / VERTEX_AXIS_COUNT;
    }

    /**
     * Get all raw vertex coordinates of this mesh.
     *
     * @return Vertexes.
     */
    public float[] getVertexes() {
        return this.vertexes;
    }

    /**
     * Get the number of normals in this this mesh.
     *
     * @return Normal count.
     */
    public int getNormalCount() {
        return this.normals.length / NORMAL_AXIS_COUNT;
    }

    /**
     * Get all raw normal coordinates of this mesh.
     *
     * @return Normals.
     */
    public float[] getNormals() {
        return this.normals;
    }

    /**
     * Check whether this mesh has any normal data.
     *
     * @return True if this mash has normal data, false if not.
     */
    public boolean hasNormalData() {
        return this.normals.length > 0;
    }

    /**
     * Get all raw texture coordinates of this mesh.
     *
     * @return Textures.
     */
    public float[] getTextures() {
        return this.textures;
    }

    /**
     * Check whether this mesh has any texture data.
     *
     * @return True if this mash has texture data, false if not.
     */
    public boolean hasTextureData() {
        return this.textures.length > 0;
    }

    /**
     * Get the number of axis used for vertex data.
     *
     * @return Vertex axis count.
     */
    public int getVertexAxisCount() {
        return VERTEX_AXIS_COUNT;
    }

    /**
     * Get the number of axis used for normal data.
     *
     * @return Normal axis count.
     */
    public int getNormalAxisCount() {
        return NORMAL_AXIS_COUNT;
    }

    /**
     * Get the number of axis used for texture data.
     *
     * @return Texture axis count.
     */
    public int getTextureAxisCount() {
        return TEXTURE_AXIS_COUNT;
    }
}
