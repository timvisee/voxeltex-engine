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

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.gameobject.Transform;

import java.util.List;

public abstract class BaseComponent extends AbstractComponent {

    /**
     * The game object owner/parent of this component.
     */
    private AbstractGameObject owner;

    @Override
    public void destroy() {
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
    public AbstractGameObject getOwner() {
        return this.owner;
    }

    @Override
    public void setOwner(AbstractGameObject gameObject) {
        this.owner = gameObject;
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
