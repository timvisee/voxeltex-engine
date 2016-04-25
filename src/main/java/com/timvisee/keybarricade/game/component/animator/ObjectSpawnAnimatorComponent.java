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

package com.timvisee.keybarricade.game.component.animator;

import com.timvisee.voxeltex.global.Time;
import com.timvisee.voxeltex.structure.component.AbstractComponent;
import com.timvisee.voxeltex.structure.component.BaseComponent;
import com.timvisee.voxeltex.structure.component.mesh.renderer.MeshRendererComponent;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class ObjectSpawnAnimatorComponent extends BaseComponent {

    /**
     * The time offset used for animations.
     */
    private float timeOffset;

    /**
     * Target position of the object.
     */
    private Vector3f targetPosition;

    /**
     * Mesh renderer component.
     */
    private MeshRendererComponent meshRenderer;

    /**
     * Wait until the given time before starting the animation.
     */
    private float waitUntil = Time.timeFloat;

    /**
     * Components to add after the spawn animation has finished.
     */
    private List<AbstractComponent> delayedComponents;

    /**
     * Constructor.
     */
    public ObjectSpawnAnimatorComponent() { }

    /**
     * Constructor.
     *
     * @param delay Time in seconds to wait before starting the animation.
     */
    public ObjectSpawnAnimatorComponent(float delay) {
        this.waitUntil += delay;
    }

    /**
     * Constructor.
     *
     * @param delay Time in seconds to wait before starting the animation.
     * @param delayedComponent Component that will be added after the animation has finished, or null.
     */
    public ObjectSpawnAnimatorComponent(float delay, final AbstractComponent delayedComponent) {
        this(delay, delayedComponent == null ? null : new ArrayList<AbstractComponent>() {{
            add(delayedComponent);
        }});
    }

    /**
     * Constructor.
     *
     * @param delay Time in seconds to wait before starting the animation.
     * @param delayedComponents List of components that will be added after the animation has finished, or null.
     */
    public ObjectSpawnAnimatorComponent(float delay, List<AbstractComponent> delayedComponents) {
        this.waitUntil += delay;
        this.delayedComponents = delayedComponents;
    }

    @Override
    public void create() {
        // Use the mesh renderer of a child object if available
        for(int i = 0, size = getOwner().getChildren().size(); i < size; i++)
            if((this.meshRenderer = getOwner().getChild(i).getComponent(MeshRendererComponent.class)) != null)
                break;

        // Get the attached mesh renderer component if available
        if(this.meshRenderer == null)
            this.meshRenderer = getComponent(MeshRendererComponent.class);
    }

    @Override
    public void start() {
        // Call the super
        super.start();

        // Set the time offset
        this.timeOffset = this.waitUntil;

        // Store the object's targetPosition position
        targetPosition = new Vector3f(getTransform().getPosition());

        // Place the object in the air
        getTransform().getPosition().y = 6f;
    }

    @Override
    public void update() {
        // Do not start the animation if we still need to wait
        if(Time.timeFloat < waitUntil) {
            // Disable the mesh renderer, to ensure the blocks aren't visible already in the air
            if(this.meshRenderer != null)
                this.meshRenderer.setEnabled(false);

            // Return
            return;
        }

        // Enable the mesh renderer
        if(this.meshRenderer != null)
            this.meshRenderer.setEnabled(true);

        // Lerp the position
        getTransform().getPosition().lerp(this.targetPosition, (Time.timeFloat - timeOffset) / 4.0f);

        // Set the alpha intensity of the object based on it's spawn time
        if(this.meshRenderer != null)
            this.meshRenderer.getColor().setAlpha(Math.min((Time.timeFloat - timeOffset) / 0.25f, 1));

        // Destroy the spawn animator after a second
        if(Time.timeFloat - timeOffset > 1f) {
            // Force-set the position of the box
            getTransform().setPosition(this.targetPosition);

            // Destroy the component
            removeComponent(this);

            // Check if any delayed components are given, if so, add them
            if(this.delayedComponents != null)
                //noinspection ForLoopReplaceableByForEach
                for(int i = 0, size = this.delayedComponents.size(); i < size; i++)
                    addComponent(this.delayedComponents.get(i));
        }
    }
}
