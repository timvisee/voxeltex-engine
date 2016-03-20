package me.keybarricade.voxeltex.prefab.primitive;

import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.generator.CubeMeshGenerator;
import me.keybarricade.voxeltex.shader.ShaderManager;

public class CubePrefab extends GameObject {

    /**
     * The default name of the cube.
     */
    private static final String DEFAULT_NAME = "CubePrefab";

    /**
     * Constructor.
     */
    public CubePrefab() {
        super(DEFAULT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public CubePrefab(String name) {
        super(name);
    }

    @Override
    public void start() {
        // Call the super
        super.start();

        // Add a mesh filter component with a cube mesh
        addComponent(new MeshFilterComponent(new CubeMeshGenerator().createMesh()));

        // Add a mesh renderer component with the default material
        // TODO: Use proper shader here!
        addComponent(new MeshRendererComponent(new Material(ShaderManager.SHADER_DEFAULT)));
    }
}
