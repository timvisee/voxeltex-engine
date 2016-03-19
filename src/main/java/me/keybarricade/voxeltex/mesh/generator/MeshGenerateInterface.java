package me.keybarricade.voxeltex.mesh.generator;

import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.mesh.RawMesh;

public interface MeshGenerateInterface {

    /**
     * Get the raw mesh that was created by the generator.
     *
     * @return Raw mesh.
     */
    RawMesh getRawMesh();

    /**
     * Create a mesh based on the generated mesh.
     *
     * @return Created mesh.
     */
    Mesh createMesh();
}
