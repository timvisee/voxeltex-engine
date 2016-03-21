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
