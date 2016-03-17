package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.component.AbstractComponent;
import me.keybarricade.voxeltex.component.drawable.AbstractDrawableComponent;
import me.keybarricade.voxeltex.component.drawable.DrawableComponentInterface;

import java.util.ArrayList;
import java.util.List;

public class GameObject extends AbstractGameObject {

    /**
     * Game object name.
     */
    private String name;

    /**
     * The transform instance of this object.
     */
    private Transform transform = new Transform(this);

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
     * Constructor.
     *
     * @param name Game object name.
     */
    public GameObject(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Transform getTransform() {
        return this.transform;
    }

    @Override
    public AbstractGameObject getParent() {
        return this.parent;
    }

    @Override
    public boolean hasParent() {
        return getParent() != null;
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
    public boolean hasChildren() {
        return getChildCount(false) > 0;
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
    public boolean hasComponents() {
        return getComponentCount() > 0;
    }

    @Override
    public int getComponentCount() {
        return this.components.size();
    }

    @Override
    public void addComponent(AbstractComponent component) {
        // Set the component owner
        component.setOwner(this);

        // Add the component
        this.components.add(component);
    }

    @Override
    public AbstractComponent getComponent(int i) {
        return this.components.get(i);
    }

    @Override
    public <T extends AbstractComponent> T getComponent(Class<T> componentType) {
        // TODO: Improve performance of this!

        // Loop through all components to find an applicable one
        for(AbstractComponent component : this.components)
            if(component.getClass().isAssignableFrom(componentType))
                //noinspection unchecked
                return (T) component;

        // None found, return null
        return null;
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
    public void update() {
        // Update the transform
        this.transform.update();

        // Update all components and then the children
        this.components.forEach(AbstractComponent::update);
        this.children.forEach(AbstractGameObject::update);
    }

    @Override
    public void draw() {
        // Draw all drawable components and all children
        for(AbstractComponent component : this.components) {
            // Make sure the component is drawable
            if(!(component instanceof DrawableComponentInterface))
                continue;

            // Cast the component
            AbstractDrawableComponent drawable = (AbstractDrawableComponent) component;

            // Draw the component
            drawable.drawStart();
            drawable.draw();
            drawable.drawEnd();
        }

        this.children.forEach(AbstractGameObject::draw);
    }
}
