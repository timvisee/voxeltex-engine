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

    public List<GameObjectInterface> getChildren() {
        return this.children;
    }

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

    public void addChild(GameObjectInterface gameObject) {
        this.children.add(gameObject);
    }

    public GameObjectInterface getChild(int i) {
        // TODO: Make sure we're in bound?

        // Get the child by it's index
        return this.children.get(i);
    }

    public boolean removeChild(GameObjectInterface gameObject) {
        return this.children.remove(gameObject);
    }

    public boolean removeChild(int i) {
        return this.children.remove(i) != null;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

    public void update() { }
}
