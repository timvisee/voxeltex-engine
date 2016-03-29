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

package me.keybarricade.voxeltex.component;

import me.keybarricade.voxeltex.VoxelTexEngine;
import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.gameobject.Transform;
import me.keybarricade.voxeltex.scene.AbstractScene;

import java.util.List;

public abstract class AbstractComponent {

    /**
     * Get the game object owner/parent of this component.
     *
     * @return Owner.
     */
    public abstract AbstractGameObject getOwner();

    /**
     * Set the game object owner/parent of this component.
     *
     * @param gameObject Owner.
     */
    public abstract void setOwner(AbstractGameObject gameObject);

    /**
     * Get the engine instance the game object of this component is in.
     *
     * @return Engine.
     */
    public VoxelTexEngine getEngine() {
        return getOwner().getEngine();
    }

    /**
     * Get the scene instance the game object of this component is in.
     *
     * @return Scene.
     */
    public AbstractScene getScene() {
        return getOwner().getScene();
    }

    /**
     * Check whether the scene the game object of this component is in is started.
     * If the game object isn't in any scene, false will be returned.
     *
     * @return True if the scene is started, false if not.
     */
    public boolean isSceneStarted() {
        // Make sure a scene is set
        if(getScene() == null)
            return false;

        // Return the result
        return getScene().isStarted();
    }

    /**
     * Get the transformation of the owner object.
     *
     * @return Owner object transformation.
     */
    public abstract Transform getTransform();

    /**
     * Get all components in this game object.
     *
     * @return Game object components.
     */
    public abstract List<AbstractComponent> getComponents();

    /**
     * Check whether this game object has any components.
     *
     * @return True if this game object has any components, fale if not.
     */
    public abstract boolean hasComponents();

    /**
     * Get the number of components in this game object.
     *
     * @return Component count.
     */
    public abstract int getComponentCount();

    /**
     * Get the component at the given index.
     *
     * @param i Component index.
     *
     * @return Component.
     */
    public abstract AbstractComponent getComponent(int i);

    /**
     * Get the first component of the given type.
     *
     * @param componentType Component type.
     * @param <T> Component type.
     *
     * @return The first component of the given type or null if none was found.
     */
    public abstract <T extends AbstractComponent> T getComponent(Class<T> componentType);

    /**
     * Create the component.
     *
     * Called when the component is added to a game object that is in a scene.
     */
    public abstract void create();

    /**
     * Start the component.
     *
     * Called when the scene the game object is in is started.
     */
    public abstract void start();

    /**
     * Update the component.
     *
     * Called once each frame before drawing.
     */
    public abstract void update();

    /**
     * Destroy the component.
     *
     * Called once before being destroyed.
     */
    public abstract void destroy();

    /**
     * Get the name of the component type.
     *
     * @return Component name.
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Component(" + getName() + ")";
    }
}
