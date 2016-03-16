package me.keybarricade.voxeltex.component;

import me.keybarricade.voxeltex.gameobject.GameObjectAbstract;

public abstract class ComponentAbstract {

    /**
     * Get the game object owner/parent of this component.
     *
     * @return Owner.
     */
    public abstract GameObjectAbstract getOwner();

    /**
     * Set the game object owner/parent of this component.
     *
     * @param gameObject Owner.
     */
    protected abstract void setOwner(GameObjectAbstract gameObject);

    /**
     * Update the component.
     *
     * Called once each frame.
     */
    public abstract void update();
}
