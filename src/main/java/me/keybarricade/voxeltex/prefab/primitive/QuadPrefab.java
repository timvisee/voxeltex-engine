package me.keybarricade.voxeltex.prefab.primitive;

import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.generator.QuadMeshGenerator;
import me.keybarricade.voxeltex.shader.ShaderManager;

public class QuadPrefab extends GameObject {

    /**
     * The default name of the quad.
     */
    private static final String DEFAULT_NAME = "QuadPrefab";

    /**
     * Mesh filter component.
     */
    private MeshFilterComponent meshFilter;

    /**
     * Mesh renderer component.
     */
    private MeshRendererComponent meshRenderer;

    /**
     * Constructor.
     */
    public QuadPrefab() {
        this(DEFAULT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public QuadPrefab(String name) {
        super(name);

        // Create the mesh filter component
        this.meshFilter = new MeshFilterComponent(new QuadMeshGenerator(10.0f).createMesh());

        // Create the mesh renderer component
        // TODO: Use proper shader here!
        this.meshRenderer = new MeshRendererComponent(new Material(ShaderManager.SHADER_DEFAULT));

        // Add the mesh filter and renderer components to the object
        addComponent(this.meshFilter);
        addComponent(this.meshRenderer);
    }

    @Override
    public void create() {
        // Call the super
        super.create();
    }

    /**
     * Get the mesh filter component.
     *
     * @return Mesh filter component.
     */
    public MeshFilterComponent getMeshFilter() {
        return meshFilter;
    }

    /**
     * Get the mesh renderer component.
     *
     * @return Mesh renderer component.
     */
    public MeshRendererComponent getMeshRenderer() {
        return meshRenderer;
    }

    /**
     * Set the quad material.
     *
     * @param material Quad material.
     */
    public void setMaterial(Material material) {
        // Make sure the mesh renderer has been configured
        if(this.meshRenderer != null)
            this.meshRenderer.setMaterial(material);
    }
}
