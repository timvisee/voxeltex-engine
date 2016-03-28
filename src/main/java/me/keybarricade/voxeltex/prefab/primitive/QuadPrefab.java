package me.keybarricade.voxeltex.prefab.primitive;

import me.keybarricade.voxeltex.component.collider.primitive.QuadColliderComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.math.vector.Vector2fFactory;
import me.keybarricade.voxeltex.mesh.generator.QuadMeshGenerator;
import me.keybarricade.voxeltex.shader.ShaderManager;
import org.joml.Vector2f;

public class QuadPrefab extends GameObject {

    /**
     * The default name of the quad.
     */
    private static final String DEFAULT_NAME = "QuadPrefab";

    /**
     * Default orientation of the quad.
     */
    private static final int DEFAULT_ORIENTATION = QuadMeshGenerator.ORIENTATION_Y_POSITIVE;

    /**
     * Thickness of the quad collider. Required because a quad doesn't have a thickness by default.
     */
    // TODO: Objects are still falling through quads with this thickness!
    private static final float COLLIDER_THICKNESS = 0.005f;

    /**
     * Mesh filter component.
     */
    private MeshFilterComponent meshFilter;

    /**
     * Mesh renderer component.
     */
    private MeshRendererComponent meshRenderer;

    /**
     * Quad collider component.
     */
    private QuadColliderComponent collider;

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
        this(name, Vector2fFactory.one());
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param size Quad size.
     */
    public QuadPrefab(String name, Vector2f size) {
        // Construct the super
        super(name);

        // Create the mesh filter component
        this.meshFilter = new MeshFilterComponent(new QuadMeshGenerator(DEFAULT_ORIENTATION, size).createMesh());

        // Create the mesh renderer component
        // TODO: Use proper shader here!
        this.meshRenderer = new MeshRendererComponent(new Material(ShaderManager.SHADER_DEFAULT));

        // Create and add an appropriate quad collider
        this.collider = new QuadColliderComponent(DEFAULT_ORIENTATION, size, COLLIDER_THICKNESS);

        // Add the mesh filter and renderer components to the object
        addComponent(this.meshFilter);
        addComponent(this.meshRenderer);
        addComponent(this.collider);
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
     * Get the quad collider component.
     *
     * @return Quad collider component.
     */
    public QuadColliderComponent getCollider() {
        return collider;
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
