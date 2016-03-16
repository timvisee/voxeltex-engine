package me.keybarricade.voxeltex.component;

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;

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
    public abstract void update();
}
