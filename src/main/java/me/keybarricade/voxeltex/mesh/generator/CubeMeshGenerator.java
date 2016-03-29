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

import java.util.ArrayList;
import java.util.List;

public class CubeMeshGenerator extends AbstractMeshGenerator {

    /**
     * The default size of the cube.
     */
    private static final float DEFAULT_SIZE = 1.0f;

    /**
     * Raw mesh instance containing the generated mesh.
     */
    private RawMesh raw;

    /**
     * Generate a cube mesh with the default size.
     */
    public CubeMeshGenerator() {
        this(DEFAULT_SIZE);
    }

    /**
     * Generate a cube mesh with the given size.
     *
     * @param size Size on both axis.
     */
    public CubeMeshGenerator(float size) {
        this(new Vector3f(size));
    }

    /**
     * Generate a cube mesh with the given size.
     *
     * @param size Size.
     */
    public CubeMeshGenerator(Vector3f size) {
        this(size, Vector3fFactory.zero());
    }

    /**
     * Generate a cube mesh with the given size and offset.
     *
     * @param size Size.
     * @param offset Offset.
     */
    public CubeMeshGenerator(Vector3f size, Vector3f offset) {
        // Create a list to put quad meshes in for each cube face
        List<RawMesh> quadMeshes = new ArrayList<>();

        // Create a quad size and offset vector
        Vector2f quadSize = Vector2fFactory.identity();
        Vector3f quadOffset = Vector3fFactory.identity();

        // TODO: Improve this code!
        {
            // Determine the quad size and offset for the X axis orientation
            quadSize.set(size.y, size.z);
            quadOffset.set(0f).add(offset).add(-(size.x / 2.0f), 0f, 0f);

            // Generate the quad for this position and add the raw mesh to the list
            QuadMeshGenerator quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_X_NEGATIVE, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());

            // Determine the quad size and offset for the Y axis orientation
            quadSize.set(size.x, size.z);
            quadOffset.set(0f).add(offset).add(0f, -(size.y / 2.0f), 0f);

            // Generate the quad for this position and add the raw mesh to the list
            quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_Y_NEGATIVE, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());

            // Determine the quad size and offset for the Z axis orientation
            quadSize.set(size.x, size.y);
            quadOffset.set(0f).add(offset).add(0f, 0f, -(size.z / 2.0f));

            // Generate the quad for this position and add the raw mesh to the list
            quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_Z_NEGATIVE, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());

            // Determine the quad size and offset for the X axis orientation
            quadSize.set(size.y, size.z);
            quadOffset.set(0f).add(offset).add(size.x / 2.0f, 0f, 0f);

            // Generate the quad for this position and add the raw mesh to the list
            quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_X_POSITIVE, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());

            // Determine the quad size and offset for the Y axis orientation
            quadSize.set(size.x, size.z);
            quadOffset.set(0f).add(offset).add(0f, size.y / 2.0f, 0f);

            // Generate the quad for this position and add the raw mesh to the list
            quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_Y_POSITIVE, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());

            // Determine the quad size and offset for the Z axis orientation
            quadSize.set(size.x, size.y);
            quadOffset.set(0f).add(offset).add(0f, 0f, size.z / 2.0f);

            // Generate the quad for this position and add the raw mesh to the list
            quadMesh = new QuadMeshGenerator(QuadMeshGenerator.ORIENTATION_Z_POSITIVE, quadSize, quadOffset);
            quadMeshes.add(quadMesh.getRawMesh());
        }

        // Calculate the number of vertexes for each mesh, and all of them combined
        final int vertexCountSingle = quadMeshes.get(0).getVertexCount() * RawMesh.VERTEX_AXIS_COUNT;
        final int vertexCountAll = vertexCountSingle * quadMeshes.size();

        // Define the vertexes and normals array
        float[] vertexes = new float[vertexCountAll];
        float[] normals = new float[0];

        // Initialize the normals array if available
        if(quadMeshes.get(0).hasNormalData())
            normals = new float[vertexCountAll];

        // Copy all vertexes from the raw meshes into the new vertexes array
        for(int i = 0; i < quadMeshes.size(); i++) {
            // Put the vertexes in the array
            System.arraycopy(quadMeshes.get(i).getVertexes(), 0, vertexes, i * vertexCountSingle, vertexCountSingle);

            // Put the normals in the array if available
            if(normals.length > 0)
                System.arraycopy(quadMeshes.get(i).getNormals(), 0, normals, i * vertexCountSingle, vertexCountSingle);
        }

        // Generate the texture coordinates in the same order
        float[] textures = new float[12 * 6];
        for(int i = 0; i < 6; i++) {
            float[] quadTextures = QuadMeshGenerator.generateTextures();
            System.arraycopy(quadTextures, 0, textures, i * 12, 12);
        }

        // Create and store the raw mesh
        this.raw = new RawMesh(vertexes, normals, textures);
    }

    @Override
    public RawMesh getRawMesh() {
        return this.raw;
    }
}
