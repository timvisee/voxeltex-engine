package me.keybarricade.voxeltex.component.mesh.renderer;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.material.Material;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMeshRendererComponent extends BaseComponent implements MeshRendererComponentInterface {

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() { }

    @Override
    public boolean hasMeshFilterComponent() {
        return getMeshFilterComponent() != null;
    }

    @Override
    public Material getMaterial(int i) {
        return getMaterials().get(i);
    }

    @Override
    public int getMaterialCount() {
        return getMaterials().size();
    }

    @Override
    public boolean hasMaterial() {
        return getMaterialCount() > 0;
    }

    @Override
    public void setMaterial(Material material) {
        // Create a new list with the material
        List<Material> list = new ArrayList<>();
        list.add(material);

        // Set the materials
        setMaterials(list);
    }
}
