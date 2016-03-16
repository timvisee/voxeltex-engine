package me.keybarricade.voxeltex.component;

import me.keybarricade.voxeltex.gameobject.GameObjectAbstract;

public abstract class BaseComponent extends ComponentAbstract {

    /**
     * The game object owner/parent of this component.
     */
    private GameObjectAbstract owner;

    @Override
    public GameObjectAbstract getOwner() {
        return this.owner;
    }

    @Override
    protected void setOwner(GameObjectAbstract gameObject) {
        this.owner = gameObject;
    }

    @Override
    public abstract void update();
}
