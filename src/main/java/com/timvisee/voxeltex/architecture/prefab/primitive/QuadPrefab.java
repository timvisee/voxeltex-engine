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

package com.timvisee.voxeltex.architecture.prefab.primitive;

import com.timvisee.voxeltex.architecture.component.collider.primitive.QuadColliderComponent;
import com.timvisee.voxeltex.architecture.component.mesh.filter.MeshFilterComponent;
import com.timvisee.voxeltex.architecture.component.mesh.renderer.MeshRendererComponent;
import com.timvisee.voxeltex.architecture.gameobject.GameObject;
import com.timvisee.voxeltex.module.material.Material;
import com.timvisee.voxeltex.module.mesh.generator.QuadMeshGenerator;
import com.timvisee.voxeltex.module.shader.ShaderManager;
import com.timvisee.voxeltex.util.math.vector.Vector2fFactory;
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
