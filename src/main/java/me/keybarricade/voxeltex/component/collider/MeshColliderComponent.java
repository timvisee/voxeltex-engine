package me.keybarricade.voxeltex.component.collider;

import com.bulletphysics.collision.shapes.*;
import me.keybarricade.voxeltex.mesh.RawMesh;
import me.keybarricade.voxeltex.util.BufferUtil;

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
