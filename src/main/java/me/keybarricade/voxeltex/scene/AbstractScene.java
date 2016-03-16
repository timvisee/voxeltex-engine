package me.keybarricade.voxeltex.scene;

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;

import java.util.List;

public abstract class AbstractScene {

    /**
     * Get all game objects in this scene.
     *
     * @return Game objects.
     */
    public abstract List<AbstractGameObject> getGameObjects();

    /**
     * Get the number of game objects in this scene.
     *
     * @param recursive True to count recursively, false to only count the root objects.
     *
     * @return Game object count.
     */
    public abstract int getGameObjectCount(boolean recursive);

    /**
     * Check whether the scene has any game objects.
     *
     * @return True if the scene has any game object, false if not.
     */
    public abstract boolean hasGameObjects();

    /**
     * Get the total number of game objects inside this scene.
     *
     * @return Total game object count.
     */
    public abstract int getTotalGameObjectCount();

    /**
     * Add a game object to the scene.
     *
     * @param gameObject Game object.
     */
    public abstract void addGameObject(AbstractGameObject gameObject);

    /**
     * Get the game object at the given index.
     *
     * @param i Game object index.
     *
     * @return Game object.
     */
    public abstract AbstractGameObject getGameObject(int i);

    /**
     * Remove the given game object from the scene.
     *
     * @return True if any game object was removed, false if not.
     */
    public abstract boolean removeGameObject(AbstractGameObject gameObject);

    /**
     * Remove the game object at the given index.
     *
     * @param i Game object index.
     *
     * @return The game object that was removed, or null if none was removed.
     */
    public abstract AbstractGameObject removeGameObject(int i);
}
