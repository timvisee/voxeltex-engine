/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.voxeltex.structure.component.mesh.renderer;

import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.shader.Shader;
import com.timvisee.voxeltex.structure.component.mesh.filter.AbstractMeshFilterComponent;
import com.timvisee.voxeltex.structure.component.mesh.filter.MeshFilterComponentInterface;
import com.timvisee.voxeltex.util.Color;
import org.joml.Matrix4f;

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
     * Mesh color.
     */
    private Color color = new Color(1, 1, 1, 1);

    /**
     * Cached model matrix that is used for rendering from time to time.
     * Caching and recycling the instance adds a huge performance benefit.
     */
    private final Matrix4f tempModelMatrix = new Matrix4f();

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
        // Call the super
        super.start();

        // Get the mesh filter if it hasn't been configured already
        if(!hasMeshFilterComponent())
            this.meshFilter = getComponent(AbstractMeshFilterComponent.class);
    }

    @Override
    public synchronized void onDraw() {
        // Make sure a mesh filter is attached and that a mesh is set
        if(!hasMeshFilterComponent() || !getMeshFilterComponent().hasMesh())
            return;

        // TODO: Should we also render if no material is available, with a default color of some sort?
        // TODO: Add compatibility for multiple materials!
        // TODO: Use a default material if none is found!

        // Make sure a material is available before using it
        if(hasMaterial()) {
            // Get the main material and shader
            Material material = getMaterial();
            Shader shader = material.getShader();

            // Bind material to OpenGL and update the shader
            material.bind();
            shader.update(getScene(), material);

            // Get the model matrix and send it to the shader
            synchronized(this.tempModelMatrix) {
                shader.setUniformMatrix4f("modelMatrix", getTransform().getWorldMatrix(this.tempModelMatrix));
            }

            // Bind the texture if available
            // TODO: Also bind the normal!
            if(material.hasTexture())
                shader.setUniform1f("texture", material.getTexture().getId());

            // Send the color
            // TODO: Use buffering!
            shader.setUniform4f("color", this.color.toVector4f());

            // Draw the mesh attached to the mesh filter
            this.meshFilter.getMesh().draw(material);

            // Unbind the material
            material.unbind();
        }

        // TODO: Also draw the mesh if no material is attached!
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

    /**
     * Get the color.
     *
     * @return Color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set the color.
     *
     * @param color Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get the alpha channel intensity.
     *
     * @return Alpha channel intensity.
     */
    public float setAlpha() {
        return this.color.getAlpha();
    }

    /**
     * Set the alpha channel intensity.
     *
     * @param alpha Alpha.
     */
    public void setAlpha(float alpha) {
        this.color.setAlpha(alpha);
    }
}
