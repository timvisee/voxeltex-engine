package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.component.AbstractComponent;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class GameObject extends AbstractGameObject {

    /**
     * The parent of this game object.
     */
    private AbstractGameObject parent = null;

    /**
     * The children of this game object.
     */
    private List<AbstractGameObject> children = new ArrayList<>();

    /**
     * The components on this game object.
     */
    private List<AbstractComponent> components = new ArrayList<>();

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
    public AbstractGameObject getParent() {
        return this.parent;
    }

    @Override
    protected void setParent(AbstractGameObject parent) {
        this.parent = parent;
    }

    @Override
    public List<AbstractGameObject> getChildren() {
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
        for(AbstractGameObject gameObject : this.children)
            count += gameObject.getChildCount(true);

        // Return the number of recursive children
        return count;
    }

    @Override
    public void addChild(AbstractGameObject gameObject) {
        // Set the parent
        gameObject.setParent(this);

        // Add the game object to the children
        this.children.add(gameObject);
    }

    @Override
    public AbstractGameObject getChild(int i) {
        // TODO: Make sure we're in bound?

        // Get the child by it's index
        return this.children.get(i);
    }

    @Override
    public boolean removeChild(AbstractGameObject gameObject) {
        // Remove any game object
        if(!this.children.remove(gameObject))
            return false;

        // Reset the parent
        gameObject.setParent(null);

        // Return the result
        return true;
    }

    @Override
    public AbstractGameObject removeChild(int i) {
        // Get the child that will be removed
        AbstractGameObject child;

        // Remove the child by it's index, and make sure any child was removed
        if((child = this.children.remove(i)) == null)
            return null;

        // Reset the parent
        child.setParent(null);

        // Return the child
        return child;
    }

    @Override
    public List<AbstractComponent> getComponents() {
        return this.components;
    }

    @Override
    public int getComponentCount() {
        return this.components.size();
    }

    @Override
    public void addComponent(AbstractComponent component) {
        this.components.add(component);
    }

    @Override
    public AbstractComponent getComponent(int i) {
        return this.components.get(i);
    }

    @Override
    public boolean removeComponent(AbstractComponent component) {
        // Remove any component
        if(!this.components.remove(component))
            return false;

        // Reset the owner
        component.setOwner(null);

        // Return the result
        return true;
    }

    @Override
    public AbstractComponent removeComponent(int i) {
        // Get the component that will be removed
        AbstractComponent component;

        // Remove the component by it's index, and make sure any component was removed
        if((component = this.components.remove(i)) == null)
            return null;

        // Reset the owner
        component.setOwner(null);

        // Return the component
        return component;
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Vector3f getWorldPosition() {
        // TODO: Do rotation calculations!

        // Calculate and return the world position
        return getParentWorldPosition().add(getPosition(), Vector3fFactory.zero());
    }

    @Override
    public Vector3f getParentWorldPosition() {
        // Return the parent position if set
        if(this.getParent() != null)
            return getParent().getWorldPosition();

        // Return zero
        return Vector3fFactory.zero();
    }

    @Override
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    @Override
    public void setWorldPosition(Vector3f position) {
        // Calculate and set the local position
        setPosition(position.sub(getParentWorldPosition(), Vector3fFactory.zero()));
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
