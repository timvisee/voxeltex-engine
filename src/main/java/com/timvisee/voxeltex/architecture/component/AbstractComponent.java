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

package com.timvisee.voxeltex.architecture.component;

import com.timvisee.voxeltex.EnabledState;
import com.timvisee.voxeltex.VoxelTexEngine;
import com.timvisee.voxeltex.architecture.gameobject.AbstractGameObject;
import com.timvisee.voxeltex.architecture.scene.AbstractScene;
import com.timvisee.voxeltex.module.transform.Transform;

import java.util.List;

public abstract class AbstractComponent {

    /**
     * True if the component is enabled, false if not.
     * If the component is disabled, the draw and update loops won't be notified.
     */
    private EnabledState enabled = EnabledState.UNDEFINED;

    /**
     * Check whether this component is enabled.
     * If the enabled state is yet undefined, false will be returned.
     *
     * @return True if enabled, false if not.
     */
    public boolean isEnabled() {
        return this.enabled.equals(EnabledState.ENABLED);
    }

    /**
     * Check whether the current enabled state is undefined.
     *
     * @return True if undefined, false if not.
     */
    public boolean isEnabledUndefined() {
        return this.enabled.equals(EnabledState.UNDEFINED);
    }

    /**
     * Set whether this component is enabled.
     *
     * @param enabled True if enabled, false if not.
     */
    public void setEnabled(boolean enabled) {
        // Determine whether the enabled state is changing
        boolean change = isEnabledUndefined() || enabled != isEnabled();

        // Set the new state
        this.enabled = enabled ? EnabledState.ENABLED : EnabledState.DISABLED;

        // Call the onEnable or onDisable method accordingly
        // TODO: Only call this if the component has started?
        if(change) {
            if(enabled)
                onEnable();
            else
                onDisable();
        }
    }

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
     * Add a child to the owner of this component.
     *
     * @param gameObject Game object to add.
     */
    public abstract void addOwnerChild(AbstractGameObject gameObject);


    /**
     * Remove a child game object from the owner of this component before the next update.
     * To destroy the child, call {@see AbstractGameObject.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param gameObject Game object to remove.
     *
     * @return True if any game object will be removed, false if not.
     */
    public abstract boolean removeOwnerChild(AbstractGameObject gameObject);

    /**
     * Remove a child game object from the owner of this component before the next update.
     * To destroy the child, call {@see AbstractGameObject.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param i Index of the child game object to remove.
     *
     * @return True if any game object will be removed, false if not.
     */
    public abstract AbstractGameObject removeOwnerChild(int i);

    /**
     * Add a component to the owner game object of this component.
     *
     * @param component Component to add.
     */
    public abstract void addComponent(AbstractComponent component);

    /**
     * Remove a component from the owner game object of this component before the next update.
     * To destroy the component, call {@see AbstractComponent.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param component Component to remove.
     *
     * @return True if any component will be removed, false if not.
     */
    public abstract boolean removeComponent(AbstractComponent component);

    /**
     * Remove a component from the owner game object of this component before the next update.
     * To destroy the component, call {@see AbstractComponent.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param i Index of the component to remove.
     *
     * @return True if any component will be removed, false if not.
     */
    public abstract AbstractComponent removeComponent(int i);

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
     * This will remove the component from the owning game object automatically before the next update.
     * All references created by the VoxelTex engine to this component will be cleared.
     * The component may not be used anymore after it has been destroyed.
     * All game objects inside this scene will be destroyed accordingly.
     */
    public abstract void destroy();

    /**
     * On enable of this component.
     * This will be called when the component is enabled.
     * The component will be enabled automatically after starting, unless specified otherwise.
     */
    public abstract void onEnable();

    /**
     * On disable of this component.
     * This will be called when the component is disabled.
     * The component will be disabled automatically before destruction, unless it was disabled already.
     */
    public abstract void onDisable();

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
        return "Component[" + getName() + "]";
    }
}
