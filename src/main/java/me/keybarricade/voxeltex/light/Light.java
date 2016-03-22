package me.keybarricade.voxeltex.light;

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Light {

    /**
     * Directional light type.
     */
    public static final int LIGHT_TYPE_DIRECTIONAL = 1;

    /**
     * Point light type.
     */
    public static final int LIGHT_TYPE_POINT = 2;

    /**
     * Spot light type.
     */
    public static final int LIGHT_TYPE_SPOT = 3;

    /**
     * Light type.
     */
    private int type;

    /**
     * Light position in the world space.
     */
    private Vector3f position = Vector3fFactory.identity();

    /**
     * Light rotation in the world space.
     */
    private Vector3f rotation = Vector3fFactory.identity();

    /**
     * Light color intensity.
     */
    private Vector3f color = Vector3fFactory.identity();

    /**
     * The brightness of the light.
     */
    private float brightness;

    /**
     * Constructor.
     *
     * @param type Light type.
     * @param gameObject Game object at the position of the light source.
     * @param color Light color intensity.
     * @param brightness Light brightness.
     */
    public Light(int type, AbstractGameObject gameObject, Vector3f color, float brightness) {
        this.type = type;
        updatePosition(gameObject);
        this.color.set(color);
        this.brightness = brightness;
    }

    /**
     * Constructor.
     *
     * @param type Light type.
     * @param position Light position in world space.
     * @param rotation Light rotation in world space.
     * @param color Light color intensity.
     * @param brightness Light brightness.
     */
    public Light(int type, Vector3f position, Quaternionf rotation, Vector3f color, float brightness) {
        this.type = type;
        this.position.set(position);
        setRotation(rotation);
        this.color.set(color);
        this.brightness = brightness;
    }

    /**
     * Constructor.
     *
     * @param type Light type.
     * @param position Light position in world space.
     * @param rotation Light rotation in world space.
     * @param color Light color intensity.
     * @param brightness Light brightness.
     */
    public Light(int type, Vector3f position, Vector3f rotation, Vector3f color, float brightness) {
        this.type = type;
        this.position.set(position);
        this.rotation.set(rotation);
        this.color.set(color);
        this.brightness = brightness;
    }

    /**
     * Get the light type.
     *
     * @return Light type.
     */
    public int getType() {
        return this.type;
    }

    /**
     * Set the light type.
     *
     * @param type Light type.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Get the light position.
     *
     * @return Light position.
     */
    public Vector3f getPosition() {
        return this.position;
    }

    /**
     * Set the light position.
     *
     * @param position Light position.
     */
    public void setPosition(Vector3f position) {
        this.position.set(position);
    }

    /**
     * Get the light rotation as euler.
     *
     * @return Light rotation.
     */
    public Vector3f getRotation() {
        return this.rotation;
    }

    /**
     * Set the light rotation as euler.
     *
     * @param rotation Light rotation.
     */
    public void setRotation(Quaternionf rotation) {
        this.rotation.set(rotation.getEulerAnglesXYZ(this.rotation));
    }

    /**
     * Set the light rotation as euler.
     *
     * @param rotation Light rotation.
     */
    public void setRotation(Vector3f rotation) {
        this.rotation.set(rotation);
    }

    /**
     * Get the color intensity of the light.
     *
     * @return Color intensity.
     */
    public Vector3f getColor() {
        return this.color;
    }

    /**
     * Set the color intensity of the light.
     *
     * @param color Color intensity.
     */
    public void setColor(Vector3f color) {
        this.color.set(color);
    }

    /**
     * Get the light brightness.
     *
     * @return Light brightness.
     */
    public float getBrightness() {
        return this.brightness;
    }

    /**
     * Set the light brightness.
     *
     * @param brightness Light brightness.
     */
    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    /**
     * Update the position of the light source to match the world space position the given game object.
     *
     * @param lightObject Game object.
     */
    public void updatePosition(AbstractGameObject lightObject) {
        // Update the position based on the world space position of the given object
        updatePosition(
                lightObject.getTransform().getWorldPosition(this.position),
                lightObject.getTransform().getWorldRotation()
        );
    }

    /**
     * Update the position of the light source to the given world space position and rotation.
     *
     * @param position Light source position.
     * @param rotation Light source rotation.
     */
    public void updatePosition(Vector3f position, Quaternionf rotation) {
        updatePosition(position, rotation.getEulerAnglesXYZ(this.rotation));
    }

    /**
     * Update the position of the light source to the given world space position and rotation.
     *
     * @param position Light source position.
     * @param rotation Light source rotation.
     */
    public void updatePosition(Vector3f position, Vector3f rotation) {
        this.position.set(position);
        this.rotation.set(rotation);
    }
}
