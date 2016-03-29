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

package me.keybarricade.voxeltex.model;

import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Vector3f;

public class Face {

    /**
     * Face vertex index.
     */
    public Vector3f vertex = Vector3fFactory.identity();

    /**
     * Face normal index.
     */
    public Vector3f normal = Vector3fFactory.identity();

    /**
     * Constructor.
     *
     * @param vertex Face vertex index.
     * @param normal Face normal index.
     */
    public Face(Vector3f vertex, Vector3f normal) {
        this.vertex = vertex;
        this.normal = normal;
    }

    /**
     * Get the vertex index.
     *
     * @return Vertex index.
     */
    public Vector3f getVertex() {
        return vertex;
    }

    /**
     * Set the vertex index.
     *
     * @param vertex Vertex index.
     */
    public void setVertex(Vector3f vertex) {
        this.vertex = vertex;
    }

    /**
     * Get the normal index.
     *
     * @return Normal index.
     */
    public Vector3f getNormal() {
        return normal;
    }

    /**
     * Set the normal index.
     *
     * @param normal Normal index.
     */
    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
}
