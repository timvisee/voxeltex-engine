package me.keybarricade.voxeltex.component.mesh.filter;

import me.keybarricade.voxeltex.component.BaseComponent;

public abstract class AbstractMeshFilterComponent extends BaseComponent implements MeshFilterComponentInterface {

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public synchronized void update() { }

    @Override
    public void destroy() { }

    @Override
    public boolean hasMesh() {
        return getMesh() != null;
    }
}
