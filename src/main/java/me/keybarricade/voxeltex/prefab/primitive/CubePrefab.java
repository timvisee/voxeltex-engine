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

package me.keybarricade.voxeltex.prefab.primitive;

import me.keybarricade.voxeltex.component.collider.primitive.BoxColliderComponent;
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
     * Box collider component.
     */
    private BoxColliderComponent collider;

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
        this.meshRenderer = new MeshRendererComponent(new Material(ShaderManager.SHADER_DEFAULT));

        // Create and add an appropriate box collider
        this.collider = new BoxColliderComponent(size);

        // Add the mesh filter and renderer components to the object
        addComponent(this.meshFilter);
        addComponent(this.meshRenderer);
        addComponent(this.collider);
    }

    /**
     * Get the mesh filter component.
     *
     * @return Mesh filter component.
     */
    public MeshFilterComponent getMeshFilter() {
        return this.meshFilter;
    }

    /**
     * Get the mesh renderer component.
     *
     * @return Mesh renderer component.
     */
    public MeshRendererComponent getMeshRenderer() {
        return this.meshRenderer;
    }

    /**
     * Get the box collider component.
     *
     * @return Box collider component.
     */
    public BoxColliderComponent getCollider() {
        return this.collider;
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
