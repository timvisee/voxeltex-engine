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

package com.timvisee.voxeltex.model;

import com.timvisee.voxeltex.mesh.RawMesh;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class RawModel {

    /**
     * The model vertexes.
     */
    public List<Vector3f> vertexes = new ArrayList<>();

    /**
     * The model normals.
     */
    public List<Vector3f> normals = new ArrayList<>();

    /**
     * The model faces.
     */
    public List<Face> faces = new ArrayList<>();

    /**
     * Constructor.
     */
    public RawModel() { }

    /**
     * Add a vertex.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    public void addVertex(float x, float y, float z) {
        addVertex(new Vector3f(x, y, z));
    }

    /**
     * Add a vertex.
     *
     * @param vertex Vertex coordinates.
     */
    private void addVertex(Vector3f vertex) {
        this.vertexes.add(vertex);
    }

    /**
     * Get the vertexes.
     *
     * @return Model vertexes.
     */
    public List<Vector3f> getVertexes() {
        return vertexes;
    }

    /**
     * Add a normal.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    public void addNormal(float x, float y, float z) {
        addNormal(new Vector3f(x, y, z));
    }

    /**
     * Add a normal.
     *
     * @param normal Normal coordinates.
     */
    private void addNormal(Vector3f normal) {
        this.normals.add(normal);
    }

    /**
     * Get the normals.
     *
     * @return Model normals.
     */
    public List<Vector3f> getNormals() {
        return normals;
    }

    /**
     * Add a face.
     *
     * @param vertexIndexes Vertex indexes.
     * @param normalIndexes Normal indexes.
     */
    public void addFace(Vector3f vertexIndexes, Vector3f normalIndexes) {
        addFace(new Face(vertexIndexes, normalIndexes));
    }

    /**
     * Get the model faces.
     *
     * @return Model faces.
     */
    public List<Face> getFaces() {
        return faces;
    }

    /**
     * Add a face.
     *
     * @param face The face.
     */
    private void addFace(Face face) {
        this.faces.add(face);
    }

    /**
     * Convert the raw model to a raw mesh.
     *
     * @return Raw mesh of the model.
     */
    public RawMesh toRawMesh() {
        // Create two lists for the parsed vertexes and normals
        List<Vector3f> parsedVertexes = new ArrayList<>();
        List<Vector3f> parsedNormals = new ArrayList<>();

        // Parse the vertexes and normals from the faces
        for(Face face : this.faces) {
            // Parse the vertex
            parsedVertexes.add(this.vertexes.get((int) face.getVertex().x - 1));
            parsedVertexes.add(this.vertexes.get((int) face.getVertex().y - 1));
            parsedVertexes.add(this.vertexes.get((int) face.getVertex().z - 1));

            // Parse the normal
            parsedNormals.add(this.normals.get((int) face.getNormal().x - 1));
            parsedNormals.add(this.normals.get((int) face.getNormal().y - 1));
            parsedNormals.add(this.normals.get((int) face.getNormal().z - 1));
        }

        // Create a vertexes and normals array with the proper size
        float[] vertexes = new float[parsedVertexes.size() * 3];
        float[] normals = new float[parsedNormals.size() * 3];

        // Put the vertex coordinates in the vertexes array
        for(int i = 0; i < parsedVertexes.size(); i++) {
            // Get the vertex
            Vector3f vertex = parsedVertexes.get(i);

            // Set the vertex coordinates in the array
            vertexes[i * 3] = vertex.x;
            vertexes[i * 3 + 1] = vertex.y;
            vertexes[i * 3 + 2] = vertex.z;
        }

        // Put the normal coordinates in the normals array
        for(int i = 0; i < parsedNormals.size(); i++) {
            // Get the normal
            Vector3f normal = parsedNormals.get(i);

            // Set the normal coordinates in the array
            normals[i * 3] = normal.x;
            normals[i * 3 + 1] = normal.y;
            normals[i * 3 + 2] = normal.z;
        }

        // Create and return the raw mesh
        // TODO: Load texture coordinates, or skip it?
        return new RawMesh(vertexes, normals, new float[0]);
    }
}
