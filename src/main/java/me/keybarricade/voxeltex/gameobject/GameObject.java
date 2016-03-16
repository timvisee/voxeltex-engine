package me.keybarricade.voxeltex.gameobject;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject extends GameObjectAbstract {

    /**
     * The parent of this game object.
     */
    private GameObjectAbstract parent = null;

    /**
     * The children of this game object.
     */
    private List<GameObjectAbstract> children = new ArrayList<>();

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
    public GameObjectAbstract getParent() {
        return this.parent;
    }

    @Override
    protected void setParent(GameObjectAbstract parent) {
        this.parent = parent;
    }

    @Override
    public List<GameObjectAbstract> getChildren() {
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
        for(GameObjectAbstract gameObject : this.children)
            count += gameObject.getChildCount(true);

        // Return the number of recursive children
        return count;
    }

    @Override
    public void addChild(GameObjectAbstract gameObject) {
        // Set the parent
        gameObject.setParent(gameObject);

        // Add the game object to the children
        this.children.add(gameObject);
    }

    @Override
    public GameObjectAbstract getChild(int i) {
        // TODO: Make sure we're in bound?

        // Get the child by it's index
        return this.children.get(i);
    }

    @Override
    public boolean removeChild(GameObjectAbstract gameObject) {
        // Remove any game object
        if(!this.children.remove(gameObject))
            return false;

        // Reset the parent
        gameObject.setParent(null);

        // Return the result
        return true;
    }

    @Override
    public GameObjectAbstract removeChild(int i) {
        // Get the child that will be removed
        GameObjectAbstract child = null;

        // Remove the child by it's index, and make sure any child was removed
        if((child = this.children.remove(i)) == null)
            return null;

        // Reset the parent
        child.setParent(null);

        // Return the child
        return child;
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
