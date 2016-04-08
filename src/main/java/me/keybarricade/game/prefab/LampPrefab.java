package me.keybarricade.game.prefab;

import me.keybarricade.game.asset.GameResourceBundle;
import me.keybarricade.voxeltex.component.light.LightSourceComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.light.Light;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.prefab.primitive.QuadPrefab;
import me.keybarricade.voxeltex.util.Color;
import org.joml.Vector2f;

public class LampPrefab extends QuadPrefab {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "LampPrefab";

    /**
     * Color.
     */
    private Color color;

    /**
     * Constructor.
     *
     * @param color Lamp color.
     */
    public LampPrefab(Color color) {
        this(GAME_OBJECT_NAME, color);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     * @param color Lamp color.
     */
    public LampPrefab(String name, Color color) {
        // Construct the parent with the proper size
        super(name, new Vector2f(0.3f));

        // Set the color
        this.color = color;

        // Generate the padlock material
        Material lockMaterial = new Material(GameResourceBundle.getInstance().TEXTURE_LAMP);

        // Set the material
        setMaterial(lockMaterial);

        // Create a child game object that holds the light
        GameObject padlockLightObject = new GameObject("LampLight");
        padlockLightObject.getTransform().getPosition().y = 0.5f;
        padlockLightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, getColor().toVector3f(), 0.3f));
        addChild(padlockLightObject);
    }

    /**
     * Get the color.
     *
     * @return Lamp color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set the color.
     *
     * @param color Lamp color.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
