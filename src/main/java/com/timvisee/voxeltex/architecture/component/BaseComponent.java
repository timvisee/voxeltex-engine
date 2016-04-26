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

import com.timvisee.voxeltex.architecture.gameobject.AbstractGameObject;
import com.timvisee.voxeltex.module.transform.Transform;

import java.util.List;

public abstract class BaseComponent extends AbstractComponent {

    /**
     * The game object owner/parent of this component.
     */
    private AbstractGameObject owner;

    @Override
    public void start() {
        // Enable the component if it's enabled state is yet undefined
        if(isEnabledUndefined())
            setEnabled(true);
    }

    @Override
    public void destroy() {
        // Disable the component
        setEnabled(false);

        // Ensure we've an owner assigned
        if(getOwner() == null)
            return;

        // Queue the component to be removed from it's owner
        getOwner().removeComponent(this);

        // Force the component to finalize
        try {
            //noinspection FinalizeCalledExplicitly
            finalize();
        } catch(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void onEnable() { }

    @Override
    public void onDisable() { }

    @Override
    public AbstractGameObject getOwner() {
        return this.owner;
    }

    @Override
    public void setOwner(AbstractGameObject gameObject) {
        this.owner = gameObject;
    }

    @Override
    public void addOwnerChild(AbstractGameObject gameObject) {
        // Make sure we've an owner
        if(getOwner() == null)
            throw new RuntimeException("Unable to add " + gameObject + " child to " + this + " owner, owner undefined.");

        // Add the child to the owner
        getOwner().addChild(gameObject);
    }

    @Override
    public boolean removeOwnerChild(AbstractGameObject gameObject) {
        // Make sure we've an owner
        if(getOwner() == null)
            throw new RuntimeException("Unable to remove " + gameObject + " child from " + this + " owner, owner undefined.");

        // Remove the child from the owner
        return getOwner().removeChild(gameObject);
    }

    @Override
    public AbstractGameObject removeOwnerChild(int i) {
        // Make sure we've an owner
        if(getOwner() == null)
            throw new RuntimeException("Unable to remove child from " + this + " owner, owner undefined.");

        // Remove the child from the owner
        return getOwner().removeChild(i);
    }

    @Override
    public void addComponent(AbstractComponent component) {
        // Make sure we've an owner
        if(getOwner() == null)
            throw new RuntimeException("Unable to add " + component + " to " + this + " owner, owner undefined.");

        // Add the component to the owner
        getOwner().addComponent(component);
    }

    @Override
    public boolean removeComponent(AbstractComponent component) {
        // Make sure we've an owner
        if(getOwner() == null)
            throw new RuntimeException("Unable to remove " + component + " from " + this + " owner, owner undefined.");

        // Remove the component from the owner
        return getOwner().removeComponent(component);
    }

    @Override
    public AbstractComponent removeComponent(int i) {
        // Make sure we've an owner
        if(getOwner() == null)
            throw new RuntimeException("Unable to remove component from " + this + " owner, owner undefined.");

        // Remove the component from the owner
        return getOwner().removeComponent(i);
    }

    @Override
    public Transform getTransform() {
        return this.owner.getTransform();
    }

    @Override
    public List<AbstractComponent> getComponents() {
        return getOwner().getComponents();
    }

    @Override
    public boolean hasComponents() {
        return getOwner().hasComponents();
    }

    @Override
    public int getComponentCount() {
        return getOwner().getComponentCount();
    }

    @Override
    public AbstractComponent getComponent(int i) {
        return getOwner().getComponent(i);
    }

    @Override
    public <T extends AbstractComponent> T getComponent(Class<T> componentType) {
        return getOwner().getComponent(componentType);
    }
}
