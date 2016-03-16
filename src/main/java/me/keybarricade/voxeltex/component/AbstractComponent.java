package me.keybarricade.voxeltex.component;

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.gameobject.Transform;

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
     * Get the transformation of the owner object.
     *
     * @return Owner object transformation.
     */
    public abstract Transform getTransform();

    /**
     * Update the component.
     *
     * Called once each frame before drawing.
     */
    public abstract void update();
}
