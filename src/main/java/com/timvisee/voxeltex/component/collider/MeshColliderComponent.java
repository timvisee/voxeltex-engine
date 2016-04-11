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

import com.bulletphysics.collision.shapes.*;
import com.timvisee.voxeltex.mesh.RawMesh;
import com.timvisee.voxeltex.util.BufferUtil;

import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class MeshColliderComponent extends AbstractColliderComponent {

    /**
     * Raw mesh to use as collider.
     */
    private RawMesh rawMesh = null;

    /**
     * Bullet physics engine shape representation.
     */
    private TriangleMeshShape meshShape = null;

    /**
     * Constructor.
     *
     * @param rawMesh Raw mesh to use as collider.
     */
    public MeshColliderComponent(RawMesh rawMesh) {
        this.rawMesh = rawMesh;
    }

    /**
     * Get the raw mesh.
     *
     * @return Raw mesh.
     */
    public RawMesh getRawMesh() {
        return this.rawMesh;
    }

    @Override
    public CollisionShape getBulletShape() {
        // Make sure the bullet shape has been configured
        if(this.meshShape == null) {
            // Configure the indexed mesh
            IndexedMesh indexedMesh = new IndexedMesh();
            indexedMesh.numTriangles = rawMesh.getVertexCount() / 3;
            indexedMesh.triangleIndexBase = BufferUtil.createByteBuffer(rawMesh.getVertexCount() * 4).order(ByteOrder.nativeOrder());
            indexedMesh.triangleIndexStride = 3 * 4;
            indexedMesh.numVertices = rawMesh.getVertexCount();
            indexedMesh.vertexBase = BufferUtil.createByteBuffer(rawMesh.getVertexCount() * 3 * 4).order(ByteOrder.nativeOrder());
            indexedMesh.vertexBase.asFloatBuffer().put(rawMesh.getVertexes());
            indexedMesh.vertexStride = 3 * 4;

            // Put the proper indexes in the index base
            IntBuffer buff = indexedMesh.triangleIndexBase.asIntBuffer();
            for(int i = 0, size = indexedMesh.numTriangles * 3; i < size; i++)
                buff.put(i);

            // Create the triangle index vertex array and add the indexed mesh
            TriangleIndexVertexArray mesh = new TriangleIndexVertexArray();
            mesh.addIndexedMesh(indexedMesh);

            // Create the final mesh shape
            this.meshShape = new BvhTriangleMeshShape(mesh, true);
        }

        // Return the shape
        return this.meshShape;
    }
}
