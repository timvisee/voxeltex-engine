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

package com.timvisee.voxeltex.architecture.gameobject;

import com.timvisee.voxeltex.VoxelTexEngine;
import com.timvisee.voxeltex.architecture.component.AbstractComponent;
import com.timvisee.voxeltex.architecture.scene.AbstractScene;
import com.timvisee.voxeltex.module.transform.Transform;
import com.timvisee.voxeltex.util.EnabledState;

import java.util.List;

public abstract class AbstractGameObject {

    /**
     * True if the game object is enabled, false if not.
     * If the game object is disabled, it won't be called by the drawing and update loops.
     */
    private EnabledState enabled = EnabledState.UNDEFINED;

    /**
     * The scene this game object is in.
     */
    private AbstractScene scene;

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public AbstractGameObject(String name) {
        setName(name);
    }

    /**
     * Check whether the game object is enabled or not.
     * If the enabled state is yet undefined, false will be returned.
     *
     * @return True if this game object is enabled.
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
     * Set whether this game object is enabled or not.
     *
     * @param enabled True if enabled, false if not.
     */
    public void setEnabled(boolean enabled) {
        // Determine whether the enabled state is changing
        boolean change = isEnabledUndefined() || enabled != isEnabled();

        // Set the new state
        this.enabled = enabled ? EnabledState.ENABLED : EnabledState.DISABLED;

        // Call the onEnable or onDisable method accordingly
        // TODO: Only call this if the game object has started?
        if(change) {
            if(enabled)
                onEnable();
            else
                onDisable();
        }
    }

    /**
     * Get the name of the game object.
     *
     * @return Game object name.
     */
    public abstract String getName();

    /**
     * Set the name of the game object.
     *
     * @param name Game object name.
     */
    public abstract void setName(String name);

    /**
     * Get the transformation of the game object.
     *
     * @return Game object transformation.
     */
    public abstract Transform getTransform();

    /**
     * Get the instance of the engine this game object is in.
     *
     * @return Engine instance.
     */
    public VoxelTexEngine getEngine() {
        return this.scene.getEngine();
    }

    /**
     * Get the scene this game object is in.
     *
     * @return Game object scene.
     */
    public AbstractScene getScene() {
        return this.scene;
    }

    /**
     * Check whether the scene this game object is in is started.
     * If this game object isn't in any scene, false will be returned.
     *
     * @return True if started, false if not.
     */
    public boolean isSceneStarted() {
        // Make sure a scene is set
        if(this.scene == null)
            return false;

        // Return the result
        return this.scene.isStarted();
    }

    /**
     * Set the scene this game object is in.
     *
     * @param scene Game object scene.
     */
    public void setScene(AbstractScene scene) {
        // Set the scene
        this.scene = scene;

        // Set the scene of the children
        for(AbstractGameObject child : this.getChildren())
            child.setScene(this.scene);
    }

    /**
     * Get the parent game object.
     *
     * @return Parent game object.
     */
    public abstract AbstractGameObject getParent();

    /**
     * Check whether this game object has a parent.
     *
     * @return True if this game object has a parent.
     */
    public abstract boolean hasParent();

    /**
     * Set the parent game object.
     *
     * @param parent Parent game object.
     */
    public abstract void setParent(AbstractGameObject parent);

    /**
     * Get all children.
     *
     * @return Game object children.
     */
    public abstract List<AbstractGameObject> getChildren();

    /**
     * Check whether this game object has any children.
     *
     * @return True if this game object has children, false if not.
     */
    public abstract boolean hasChildren();

    /**
     * Get the number of children inside this game object.
     *
     * @param recursive True to also count the children of this game object's children (recursively), false to just
     *                  count the children of this object.
     *
     * @return Number of children.
     */
    public abstract int getChildCount(boolean recursive);

    /**
     * Add a child to this game object.
     *
     * @param gameObject Game object to add.
     */
    public abstract void addChild(AbstractGameObject gameObject);

    /**
     * Get a child of this game object by it's index.
     *
     * @param i Child index.
     *
     * @return The child game object.
     */
    public abstract AbstractGameObject getChild(int i);

    /**
     * Remove a child game object from this game object before the next update.
     * To destroy the child, call {@see AbstractGameObject.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param gameObject Game object to remove.
     *
     * @return True if any game object will be removed, false if not.
     */
    public abstract boolean removeChild(AbstractGameObject gameObject);

    /**
     * Remove a child game object from this game object before the next update.
     * To destroy the child, call {@see AbstractGameObject.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param i Index of the child game object to remove.
     *
     * @return True if any game object will be removed, false if not.
     */
    public abstract AbstractGameObject removeChild(int i);

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
     * Add a component to this game object.
     *
     * @param component Component to add.
     */
    public abstract void addComponent(AbstractComponent component);

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
     * Remove a component from the game object before the next update.
     * To destroy the component, call {@see AbstractComponent.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param component Component to remove.
     *
     * @return True if any component will be removed, false if not.
     */
    public abstract boolean removeComponent(AbstractComponent component);

    /**
     * Remove a component from the game object before the next update.
     * To destroy the component, call {@see AbstractComponent.destroy()} instead since this method doesn't imply
     * destruction. This method will be called automatically after destruction.
     *
     * @param i Index of the component to remove.
     *
     * @return True if any component will be removed, false if not.
     */
    public abstract AbstractComponent removeComponent(int i);

    /**
     * Create the game object.
     * This will be called when the game object is added to the scene.
     */
    public abstract void create();

    /**
     * Start the game object.
     * This will be called when the scene this game object is in is started.
     */
    public abstract void start();

    /**
     * Update the game object.
     * This will be called once each frame before drawing the game object.
     */
    public abstract void update();

    /**
     * Destroy the game object.
     * This will remove the game object from the parent game object or scene automatically before the next update.
     * All references created by the VoxelTex engine to this game object will be cleared.
     * The game object may not be used anymore after it has been destroyed.
     * All components inside this scene will be destroyed accordingly.
     */
    public abstract void destroy();

    /**
     * Draw the game object.
     * This will be called when drawing the game object.
     */
    public abstract void onDraw();

    /**
     * Draw the game object overlay.
     * This will be called when drawing the overlay of this game object.
     */
    public abstract void onDrawOverlay();

    /**
     * On enable of this game object.
     * This will be called when the game object is enabled.
     * The game object will be enabled automatically after starting, unless specified otherwise.
     */
    public abstract void onEnable();

    /**
     * On disable of this game object.
     * This will be called when the game object is disabled.
     * The game object will be disabled automatically before destruction, unless it was disabled already.
     */
    public abstract void onDisable();

    @Override
    public String toString() {
        return "GameObject[" + getName() + "]";
    }
}
