package me.keybarricade.voxeltex.component.mesh.filter;

import me.keybarricade.voxeltex.mesh.Mesh;

public interface MeshFilterComponentInterface {

    /**
     * Get the mesh that is attached to this mesh filter.
     *
     * @return Mesh, or null if no mesh is attached.
     */
    Mesh getMesh();

    /**
     * Attach a new mesh to this mesh filter.
     *
     * @param mesh Mesh to attach.
     */
    void setMesh(Mesh mesh);

    /**
     * Check whether this mesh filter has a mesh attached.
     *
     * @return True if attached, false if not.
     */
    boolean hasMesh();
}
