package me.keybarricade.voxeltex.component.mesh.filter;

import me.keybarricade.voxeltex.mesh.Mesh;

public class MeshFilterComponent extends AbstractMeshFilterComponent {

    /**
     * Attached mesh.
     */
    private Mesh mesh;

    /**
     * Constructor.
     *
     * @param mesh Mesh to attach.
     */
    public MeshFilterComponent(Mesh mesh) {
        this.mesh = mesh;
    }

    @Override
    public Mesh getMesh() {
        return this.mesh;
    }

    @Override
    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
