package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.component.AbstractComponent;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public abstract class AbstractGameObject {

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
    protected abstract void setParent(AbstractGameObject parent);

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
     * Remove a child from this game object.
     *
     * @param gameObject Game object to remove.
     *
     * @return True if any game object was removed, false if not.
     */
    public abstract boolean removeChild(AbstractGameObject gameObject);

    /**
     * Remove a child from this game object.
     *
     * @param i Index of the child to remove.
     *
     * @return The removed game object or null if no game object was removed.
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
     * Remove a component from the game object.
     *
     * @param component Component to remove.
     *
     * @return True if any component was removed, false if not.
     */
    public abstract boolean removeComponent(AbstractComponent component);

    /**
     * Remove a component at the given index.
     *
     * @param i Component index.
     *
     * @return The component that was removed, or null.
     */
    public abstract AbstractComponent removeComponent(int i);

    /**
     * Get the game object position.
     *
     * @return Game object position.
     */
    public abstract Vector3f getPosition();

    /**
     * Get the game object position in the world.
     *
     * @return Game object position.
     */
    public abstract Vector3f getWorldPosition();

    /**
     * Get the position of the parent game object.
     * If the object doesn't have a parent, a zero vector will be returned.
     *
     * @return Parent game object position.
     */
    public abstract Vector3f getParentWorldPosition();

    /**
     * Set the game object position.
     *
     * @param position Game object position.
     */
    public abstract void setPosition(Vector3f position);

    /**
     * Set the game object position in the world.
     *
     * @param position Game object world position.
     */
    public abstract void setWorldPosition(Vector3f position);

    /**
     * Get the game object rotation.
     *
     * @return Game object rotation.
     */
    public abstract Quaternionf getRotation();

    /**
     * Get the game object rotation in the world.
     *
     * @return Game object rotation.
     */
    // TODO: public abstract Quaternionf getWorldRotation();

    /**
     * Get the rotation of the parent game object.
     * If the object doesn't have a parent, a zero vector will be returned.
     *
     * @return Parent game object rotation.
     */
    // TODO: public abstract Quaternionf getParentWorldRotation();

    /**
     * Set the game object rotation.
     *
     * @param rotation Game object rotation.
     */
    public abstract void setRotation(Quaternionf rotation);

    /**
     * Set the game object rotation in the world.
     *
     * @param rotation Game object world rotation.
     */
    // TODO: public abstract void setWorldRotation(Quaternionf rotation);

    /**
     * Update the game object.
     * This will be called once each render.
     */
    public abstract void update();
}
