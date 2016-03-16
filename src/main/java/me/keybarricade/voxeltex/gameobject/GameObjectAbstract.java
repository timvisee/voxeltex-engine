package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.math.vector.*;

import org.joml.Quaternionf;

import java.util.List;

public abstract class GameObjectAbstract {

    /**
     * Get all children.
     *
     * @return Game object children.
     */
    public abstract List<GameObjectAbstract> getChildren();

    /**
     * Get the parent game object.
     *
     * @return Parent game object.
     */
    public abstract GameObjectAbstract getParent();

    /**
     * Set the parent game object.
     *
     * @param parent Parent game object.
     */
    protected abstract void setParent(GameObjectAbstract parent);

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
    public abstract void addChild(GameObjectAbstract gameObject);

    /**
     * Get a child of this game object by it's index.
     *
     * @param i Child index.
     *
     * @return The child game object.
     */
    public abstract GameObjectAbstract getChild(int i);

    /**
     * Remove a child from this game object.
     *
     * @param gameObject Game object to remove.
     *
     * @return True if any game object was removed, false if not.
     */
    public abstract boolean removeChild(GameObjectAbstract gameObject);

    /**
     * Remove a child from this game object.
     *
     * @param i Index of the child to remove.
     *
     * @return The removed game object or null if no game object was removed.
     */
    public abstract GameObjectAbstract removeChild(int i);

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
    public abstract Vector3f getPositionWorld();

    /**
     * Set the game object position.
     *
     * @param position Game object position.
     */
    public abstract void setPosition(Vector3f position);

    /**
     * Get the game object rotation.
     *
     * @return Game object rotation.
     */
    public abstract Quaternionf getRotation();

    /**
     * Set the game object rotation.
     *
     * @param rotation Game object rotation.
     */
    public abstract void setRotation(Quaternionf rotation);

    /**
     * Update the game object.
     * This will be called once each render.
     */
    public abstract void update();
}
