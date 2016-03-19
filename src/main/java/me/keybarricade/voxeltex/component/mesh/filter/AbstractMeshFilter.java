package me.keybarricade.voxeltex.component.mesh.filter;

import me.keybarricade.voxeltex.component.BaseComponent;

public abstract class AbstractMeshFilter extends BaseComponent implements MeshFilterComponentInterface {

    @Override
    public void start() { }

    @Override
    public void update() { }

    @Override
    public boolean hasMesh() {
        return getMesh() != null;
    }
}
