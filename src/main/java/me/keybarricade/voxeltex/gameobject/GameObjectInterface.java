package me.keybarricade.voxeltex.gameobject;

import java.util.List;

public interface GameObjectInterface {

    /**
     * Get all children.
     *
     * @return Game object children.
     */
    List<GameObjectInterface> getChildren();

    /**
     * Get the number of children inside this game object.
     *
     * @param recursive True to also count the children of this game object's children (recursively), false to just
     *                  count the children of this object.
     *
     * @return Number of children.
     */
    int getChildCount(boolean recursive);

    /**
     * Add a child to this game object.
     *
     * @param gameObject Game object to add.
     */
    void addChild(GameObjectInterface gameObject);

    /**
     * Get a child of this game object by it's index.
     *
     * @param i Child index.
     *
     * @return The child game object.
     */
    GameObjectInterface getChild(int i);

    /**
     * Remove a child from this game object.
     *
     * @param gameObject Game object to remove.
     *
     * @return True if any game object was removed, false if not.
     */
    boolean removeChild(GameObjectInterface gameObject);

    /**
     * Remove a child from this game object.
     *
     * @param i Index of the child to remove.
     *
     * @return True if any game object was removed, false if not.
     */
    boolean removeChild(int i);

    /**
     * Update the game object.
     * This will be called once each render.
     */
    void update();
}
