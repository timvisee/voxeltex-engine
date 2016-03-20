package me.keybarricade.voxeltex.component.mesh.renderer;

import me.keybarricade.voxeltex.component.mesh.filter.AbstractMeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponentInterface;
import me.keybarricade.voxeltex.global.MainCamera;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.renderer.VoxelTexRenderer;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class MeshRendererComponent extends AbstractMeshRendererComponent {

    /**
     * Mesh filter component, which provides the mesh.
     */
    private MeshFilterComponentInterface meshFilter;

    /**
     * List of materials used for rendering.
     */
    private List<Material> materials = new ArrayList<>();

    /**
     * Constructor.
     */
    public MeshRendererComponent() { }

    /**
     * Constructor.
     *
     * @param meshFilter Mesh filter.
     * @param materials List of materials.
     */
    public MeshRendererComponent(MeshFilterComponentInterface meshFilter, List<Material> materials) {
        this.meshFilter = meshFilter;
        this.materials = materials;
    }

    /**
     * Constructor.
     * The mesh filter that is attached to the same game object is used automatically.
     *
     * @param material Material.
     */
    public MeshRendererComponent(Material material) {
        setMaterial(material);
    }

    /**
     * Constructor.
     * The mesh filter that is attached to the same game object is used automatically.
     *
     * @param materials List of materials.
     */
    public MeshRendererComponent(List<Material> materials) {
        this.materials = materials;
    }

    @Override
    public void start() {
        // Get the mesh filter if it hasn't been configured already
        if(!hasMeshFilterComponent())
            this.meshFilter = getComponent(AbstractMeshFilterComponent.class);

        // TODO: Make sure we're indeed having the class?
    }

    @Override
    public void draw() {
        // Make sure a mesh filter is attached and that a mesh is set
        if(!hasMeshFilterComponent() || !getMeshFilterComponent().hasMesh())
            return;

        // TODO: Should we also render if no material is available, with a default color of some sort?
        // TODO: Add compatibility for multiple materials!
        // TODO: Use a default material if none is found!

        // Make sure a material is available before using it
        if(hasMaterial()) {
            // Get the main material
            Material material = getMaterial(0);

            // Bind the material to OpenGL
            material.bind();

            // TODO: Move this shader configuration code somewhere else!

            // Calculate the projection matrix
            Matrix4f projectionMatrix = new Matrix4f(VoxelTexRenderer.mat);
            projectionMatrix.scale(0.5f, 0.5f, 0.5f);

            // Calculate the model matrix
            Vector3f pos = getTransform().getPosition();
            Matrix4f modelMatrix = new Matrix4f().rotate(getTransform().getRotation()).translate(pos.x, pos.y, pos.z);

            // Configure the shader
            material.getShader().setUniformMatrix4f("projectionMatrix", projectionMatrix);
            material.getShader().setUniformMatrix4f("viewMatrix", MainCamera.createRelativeCameraMatrix());
            material.getShader().setUniformMatrix4f("modelMatrix", modelMatrix);

            // Set the shader texture if a texture is available
            if(material.hasTexture())
                material.getShader().setUniform1f("texture", material.getTexture().getId());
        }

        // Draw the mesh attached to the mesh filter
        this.meshFilter.getMesh().draw();

        // Unbind the material
        if(hasMaterial())
            getMaterial(0).unbind();
    }

    /**
     * Get the mesh filter component that is attached and used for rendering.
     *
     * @return Mesh filter component.
     */
    public MeshFilterComponentInterface getMeshFilterComponent() {
        return this.meshFilter;
    }

    /**
     * Set the mesh filter component that is attached and used for rendering.
     *
     * @param meshFilter Mesh filter component.
     */
    public void setMeshFilterComponent(MeshFilterComponentInterface meshFilter) {
        this.meshFilter = meshFilter;
    }

    @Override
    public void addMaterial(Material material) {
        this.materials.add(material);
    }

    @Override
    public List<Material> getMaterials() {
        return this.materials;
    }

    @Override
    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    @Override
    public boolean removeMaterial(Material material) {
        return this.materials.remove(material);
    }

    @Override
    public Material removeMaterial(int i) {
        return this.materials.remove(i);
    }
}
