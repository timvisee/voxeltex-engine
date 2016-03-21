package me.keybarricade.voxeltex.mesh.generator;

import me.keybarricade.voxeltex.mesh.Mesh;

public abstract class AbstractMeshGenerator implements MeshGenerateInterface {

    @Override
    public Mesh createMesh() {
        return new Mesh(getRawMesh());
    }
}
