package me.keybarricade.voxeltex.gameobject;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements GameObjectInterface {

    /**
     * The children of this game object.
     */
    private List<GameObjectInterface> children = new ArrayList<>();

    /**
     * Game object position.
     */
    private Vector3f position = new Vector3f(0, 0, 0);

    /**
     * Game object rotation.
     */
    private Quaternionf rotation = new Quaternionf();

    /**
     * Constructor.
     */
    public GameObject() { }

    /**
     * Constructor.
     *
     * @param position Game object position.
     * @param rotation Game object rotation.
     */
    public GameObject(Vector3f position, Quaternionf rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Get all children.
     *
     * @return Children.
     */
    public List<GameObjectInterface> getChildren() {
        return this.children;
    }

    /**
     * Get the number of children inside this game object.
     *
     * @param recursive True to also count the children of this game object's children (recursively), false to just
     *                  count the children of this object.
     *
     * @return Number of children.
     */
    public int getChildCount(boolean recursive) {
        // Count the number of children if not recursive
        if(!recursive)
            return this.children.size();

        // Count the number of recursive children
        int count = 0;

        // Loop through all the children, and count
        for(GameObjectInterface gameObject : this.children)
            count += gameObject.getChildCount(true);

        // Return the number of recursive children
        return count;
    }

    /**
     * Add a child to this game object.
     *
     * @param gameObject Game object to add.
     */
    public void addChild(GameObjectInterface gameObject) {
        this.children.add(gameObject);
    }

    /**
     * Get a child of this game object by it's index.
     *
     * @param i Child index.
     *
     * @return The child game object.
     */
    public GameObjectInterface getChild(int i) {
        // TODO: Make sure we're in bound?

        // Get the child by it's index
        return this.children.get(i);
    }

    /**
     * Remove a child from this game object.
     *
     * @param gameObject Game object to remove.
     *
     * @return True if any game object was removed, false if not.
     */
    public boolean removeChild(GameObjectInterface gameObject) {
        return this.children.remove(gameObject);
    }

    /**
     * Remove a child from this game object.
     *
     * @param i Index of the child to remove.
     *
     * @return True if any game object was removed, false if not.
     */
    public boolean removeChild(int i) {
        return this.children.remove(i) != null;
    }

    /**
     * Get the game object position.
     *
     * @return Game object position.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Set the game object position.
     *
     * @param position Game object position.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * Get the game object rotation.
     *
     * @return Game object rotation.
     */
    public Quaternionf getRotation() {
        return rotation;
    }

    /**
     * Set the game object rotation.
     *
     * @param rotation Game object rotation.
     */
    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

    /**
     * Update the game object.
     * This will be called once each render.
     */
    public void update() { }
}
