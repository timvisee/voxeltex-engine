package me.keybarricade.voxeltex.light;

import me.keybarricade.voxeltex.gameobject.AbstractGameObject;
import me.keybarricade.voxeltex.shader.Shader;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class LightManager {

    /**
     * Light sources available in the current scene.
     */
    private List<Light> lights = new ArrayList<>();

    /**
     * Number of buffered lights.
     */
    private int bufferedLightCount = -1;

    /**
     * Types of the buffered lights.
     */
    private IntBuffer lightTypeBuffer;

    /**
     * Positions of the buffered lights.
     */
    private FloatBuffer lightPositionBuffer;

    /**
     * Rotation of the buffered lights.
     */
    private FloatBuffer lightRotationBuffer;

    /**
     * Color of the buffered lights.
     */
    private FloatBuffer lightColorBuffer;

    /**
     * Constructor.
     */
    public LightManager() { }

    /**
     * Constructor.
     *
     * @param type Light type.
     * @param gameObject Game object at the position of the light source.
     * @param color Light color intensity.
     * @param brightness Light brightness.
     */
    public Light createLight(int type, AbstractGameObject gameObject, Vector3f color, float brightness) {
        return addLight(new Light(type, gameObject, color, brightness));
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
    public Light createLight(int type, Vector3f position, Quaternionf rotation, Vector3f color, float brightness) {
        return addLight(new Light(type, position, rotation, color, brightness));
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
    public Light createLight(int type, Vector3f position, Vector3f rotation, Vector3f color, float brightness) {
        return addLight(new Light(type, position, rotation, color, brightness));
    }

    /**
     * Add a new light to the manager.
     *
     * @param light Light.
     */
    public Light addLight(Light light) {
        // Add the light
        this.lights.add(light);

        // Return the instance
        return light;
    }

    /**
     * Get the list of lights.
     *
     * @return List of lights.
     */
    public List<Light> getLights() {
        return this.lights;
    }

    /**
     * Get the number of lights in the manager/
     *
     * @return Light count.
     */
    public int getLightCount() {
        return this.lights.size();
    }

    /**
     * Set the list of lights.
     *
     * @param lights List of lights.
     */
    private void setLights(List<Light> lights) {
        this.lights = lights;
    }

    /**
     * Buffer all current light data so it can be be send to shaders.
     */
    public void buffer() {
        // Compare the current number of lights to the buffered count, to check whether we should recreate the buffers
        if(getLightCount() != this.bufferedLightCount) {
            // Set the buffered count
            this.bufferedLightCount = getLightCount();

            // Recreate the buffers
            this.lightTypeBuffer = BufferUtils.createIntBuffer(this.bufferedLightCount);
            this.lightPositionBuffer = BufferUtils.createFloatBuffer(this.bufferedLightCount * 3);
            this.lightRotationBuffer = BufferUtils.createFloatBuffer(this.bufferedLightCount * 3);
            this.lightColorBuffer = BufferUtils.createFloatBuffer(this.bufferedLightCount * 4);
        }

        // Clear the buffers
        this.lightTypeBuffer.clear();
        this.lightPositionBuffer.clear();
        this.lightRotationBuffer.clear();
        this.lightColorBuffer.clear();

        // Add the lights to the buffers
        for(int i = 0; i < this.bufferedLightCount; i++) {
            this.lightTypeBuffer.put(this.lights.get(i).getType());

            this.lightPositionBuffer.put(this.lights.get(i).getPosition().x);
            this.lightPositionBuffer.put(this.lights.get(i).getPosition().y);
            this.lightPositionBuffer.put(this.lights.get(i).getPosition().z);

            this.lightRotationBuffer.put(this.lights.get(i).getRotation().x);
            this.lightRotationBuffer.put(this.lights.get(i).getRotation().y);
            this.lightRotationBuffer.put(this.lights.get(i).getRotation().z);

            this.lightColorBuffer.put(this.lights.get(i).getColor().x);
            this.lightColorBuffer.put(this.lights.get(i).getColor().y);
            this.lightColorBuffer.put(this.lights.get(i).getColor().z);
            this.lightColorBuffer.put(this.lights.get(i).getBrightness());
        }

        // Flip all buffers
        this.lightTypeBuffer.flip();
        this.lightPositionBuffer.flip();
        this.lightRotationBuffer.flip();
        this.lightColorBuffer.flip();
    }

    /**
     * Send the currently buffered data to the shader.
     *
     * @param shader The shader to send the light data to.
     */
    public void sendToShader(Shader shader) {
        // Send the number of lights
        shader.setUniform1i("lightCount", this.bufferedLightCount);

        // Send the data
        shader.setUniform1iv("lightType", this.lightTypeBuffer);
        shader.setUniform3fv("lightPosition", this.lightPositionBuffer);
        shader.setUniform3fv("lightRotation", this.lightRotationBuffer);
        shader.setUniform4fv("lightColor", this.lightColorBuffer);
    }
}
