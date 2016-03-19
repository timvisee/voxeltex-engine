package me.keybarricade.voxeltex.gameobject;

import me.keybarricade.voxeltex.component.AbstractComponent;
import me.keybarricade.voxeltex.component.drawable.DrawableComponentInterface;
import me.keybarricade.voxeltex.global.MainCamera;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glLoadMatrixf;
import static org.lwjgl.opengl.GL11.glPopMatrix;

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
     * Float buffer for the rendering matrix.
     */
    private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

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
        // Add the component
        this.components.add(component);

        // Set the component owner
        component.setOwner(this);

        // Start the component
        component.start();
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
            if(componentType.isAssignableFrom(component.getClass()))
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
    public void start() { }

    @Override
    public void update() {
        // Update the transform
        this.transform.update();

        // Update all components and then the children
        for(AbstractComponent component : this.components)
            component.update();
        for(AbstractGameObject child : this.children)
            child.update();
    }

    @Override
    public void draw() {
        // TODO: Only configure this if this object contains any drawable object!

        // Create a view matrix base based on the camera position
        Matrix4f viewMatrix = MainCamera.createRelativeCameraMatrix();

        // Apply the object's world transformation to the matrix
        getTransform().applyWorldTransform(viewMatrix);

        // Load the matrix to the GPU
        glLoadMatrixf(viewMatrix.get(fb));

        // Draw all drawable components and all children
        for(AbstractComponent component : this.components)
            // Draw the component if it's drawable
            if(component instanceof DrawableComponentInterface)
                ((DrawableComponentInterface) component).draw();

        // Pop the OpenGL matrix
        glPopMatrix();

        // Draw all children
        for(AbstractGameObject child : this.children)
            child.draw();
    }
}
