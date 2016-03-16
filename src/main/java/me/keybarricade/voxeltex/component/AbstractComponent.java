package me.keybarricade.voxeltex.component;

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;

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
     * Update the component.
     *
     * Called once each frame before drawing.
     */
    public abstract void update();

    /**
     * Draw the component.
     *
     * Called once each frame after updating.
     */
    public abstract void draw();
}
