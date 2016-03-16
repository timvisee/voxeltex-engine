package me.keybarricade.voxeltex.gameobject;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public abstract class GameObject {

    // TODO: Add game object children.

    /**
     * Camera position.
     */
    private Vector3f position = new Vector3f(0, 0, 10);

    /**
     * Camera rotation.
     */
    private Quaternionf rotation = new Quaternionf();

    /**
     * Constructor.
     */
    public GameObject() { }

    /**
     * Constructor.
     *
     * @param position Camera position.
     * @param rotation Camera rotation.
     */
    public GameObject(Vector3f position, Quaternionf rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Get the camera position.
     *
     * @return Camera position.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Set the camera position.
     *
     * @param position Camera position.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * Get the camera rotation.
     *
     * @return Camera rotation.
     */
    public Quaternionf getRotation() {
        return rotation;
    }

    /**
     * Set the camera rotation.
     *
     * @param rotation Camera rotation.
     */
    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

    /**
     * Update the game object.
     * This will be called once each render.
     */
    public void update() {

    }
}
