package me.keybarricade.voxeltex.component;

import me.keybarricade.voxeltex.VoxelTexEngine;
import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.gameobject.Transform;
import me.keybarricade.voxeltex.scene.AbstractScene;

import java.util.List;

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
     * Start the component.
     *
     * Called when the component is created on the game object.
     */
    public abstract void start();

    /**
     * Update the component.
     *
     * Called once each frame before drawing.
     */
    public abstract void update();
}
