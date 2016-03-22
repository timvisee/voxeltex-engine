package me.keybarricade.voxeltex.component.light;

import me.keybarricade.voxeltex.light.Light;
import org.joml.Vector3f;

public class LightSourceComponent extends AbstractLightSourceComponent {

    /**
     * Light instance.
     */
    private Light light;

    /**
     * Light type buffer. Used on creation if set.
     */
    private int lightTypeBuffer = -1;

    /**
     * Light color buffer. Used on creation if set.
     */
    private Vector3f lightColorBuffer = null;

    /**
     * Light brightness buffer. Used on creation if set.
     */
    private float lightBrightnessBuffer = -1f;

    /**
     * Constructor.
     */
    public LightSourceComponent() { }

    /**
     * Constructor.
     *
     * @param lightType Light type.
     */
    public LightSourceComponent(int lightType) {
        this.lightTypeBuffer = lightType;
    }

    /**
     * Constructor.
     *
     * @param lightBrightness Light brightness.
     */
    public LightSourceComponent(float lightBrightness) {
        this.lightBrightnessBuffer = lightBrightness;
    }

    /**
     * Constructor.
     *
     * @param lightColor Light color intensity.
     */
    public LightSourceComponent(Vector3f lightColor) {
        this.lightColorBuffer = lightColor;
    }

    /**
     * Constructor.
     *
     * @param lightType Light type.
     * @param lightColor Light color intensity.
     */
    public LightSourceComponent(int lightType, Vector3f lightColor) {
        this.lightTypeBuffer = lightType;
        this.lightColorBuffer = lightColor;
    }

    /**
     * Constructor.
     *
     * @param lightType Light type.
     * @param lightBrightness Light brightness.
     */
    public LightSourceComponent(int lightType, float lightBrightness) {
        this.lightTypeBuffer = lightType;
        this.lightBrightnessBuffer = lightBrightness;
    }

    /**
     * Constructor.
     *
     * @param lightType Light type.
     * @param lightColor Light color intensity.
     * @param lightBrightness Light brightness.
     */
    public LightSourceComponent(int lightType, Vector3f lightColor, float lightBrightness) {
        this.lightTypeBuffer = lightType;
        this.lightColorBuffer = lightColor;
        this.lightBrightnessBuffer = lightBrightness;
    }

    @Override
    public void create() {
        // Create a light source instance in the light manager, use the buffered defaults if set
        this.light = getScene().getLightManager().createLight(
                this.lightTypeBuffer >= 0 ? this.lightTypeBuffer : Light.LIGHT_TYPE_POINT,
                getOwner(),
                this.lightColorBuffer != null ? this.lightColorBuffer : new Vector3f(1.0f, 0.9f, 0.9f),
                this.lightBrightnessBuffer >= 0 ? this.lightBrightnessBuffer : 1f
        );
    }

    @Override
    public void update() {
        // Update the position of the light
        this.light.updatePosition(getOwner());

        // TODO: Destroy the light object when we're done!
    }

    @Override
    public Light getLight() {
        return light;
    }

    @Override
    public int getType() {
        return this.light.getType();
    }

    @Override
    public void setType(int type) {
        this.light.setType(type);
    }

    @Override
    public Vector3f getColor() {
        return this.light.getColor();
    }

    @Override
    public void setColor(Vector3f color) {
        this.light.setColor(color);
    }

    @Override
    public float getBrightness() {
        return this.light.getBrightness();
    }

    @Override
    public void setBrightness(float brightness) {
        this.light.setBrightness(brightness);
    }
}
