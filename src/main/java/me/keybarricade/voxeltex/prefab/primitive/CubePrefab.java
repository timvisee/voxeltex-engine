package me.keybarricade.voxeltex.prefab.primitive;

import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.mesh.generator.CubeMeshGenerator;
import me.keybarricade.voxeltex.shader.ShaderManager;
import org.joml.Vector3f;

public class CubePrefab extends GameObject {

    /**
     * The default name of the cube.
     */
    private static final String DEFAULT_NAME = "CubePrefab";

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
    public CubePrefab() {
        this(DEFAULT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public CubePrefab(String name) {
        this(name, Vector3fFactory.one());
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param size Cube size.
     */
    public CubePrefab(String name, Vector3f size) {
        // Construct the super
        super(name);

        // Create the mesh filter component
        this.meshFilter = new MeshFilterComponent(new CubeMeshGenerator(size).createMesh());

        // Create the mesh renderer component
        // TODO: Use proper shader here!
        this.meshRenderer = new MeshRendererComponent(new Material(ShaderManager.SHADER_DEFAULT));

        // Add the mesh filter and renderer components to the object
        addComponent(this.meshFilter);
        addComponent(this.meshRenderer);
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
     * Set the cube material.
     *
     * @param material Cube material.
     */
    public void setMaterial(Material material) {
        // Make sure the mesh renderer has been configured
        if(this.meshRenderer != null)
            this.meshRenderer.setMaterial(material);
    }
}
