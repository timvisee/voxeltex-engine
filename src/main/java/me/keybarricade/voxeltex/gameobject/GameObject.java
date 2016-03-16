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

    @Override
    public List<GameObjectInterface> getChildren() {
        return this.children;
    }

    @Override
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

    @Override
    public void addChild(GameObjectInterface gameObject) {
        this.children.add(gameObject);
    }

    @Override
    public GameObjectInterface getChild(int i) {
        // TODO: Make sure we're in bound?

        // Get the child by it's index
        return this.children.get(i);
    }

    @Override
    public boolean removeChild(GameObjectInterface gameObject) {
        return this.children.remove(gameObject);
    }

    @Override
    public boolean removeChild(int i) {
        return this.children.remove(i) != null;
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    @Override
    public Quaternionf getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

    @Override
    public void update() { }
}
