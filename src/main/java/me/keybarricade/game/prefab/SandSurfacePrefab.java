package me.keybarricade.game.prefab;

import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.prefab.primitive.QuadPrefab;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import org.joml.Vector2f;

public class SandSurfacePrefab extends QuadPrefab {

    /**
     * Constructor.
     */
    public SandSurfacePrefab() {
        this(50.0f);
    }

    /**
     * Constructor.
     *
     * @param size Sand surface size.
     */
    public SandSurfacePrefab(float size) {
        this(new Vector2f(size));
    }

    /**
     * Constructor.
     *
     * @param size Sand surface size.
     */
    public SandSurfacePrefab(Vector2f size) {
        // Construct the parent with the proper size
        super("SandSurface", size);

        // Load the sand texture
        Texture sandTexture = Texture.fromImage(Image.loadFromEngineAssets("images/sand.png"));

        // Create a sand surface material
        Material sandMaterial = new Material(sandTexture);
        sandMaterial.getTiling().set(size.x / 5.0f);

        // Set the quad material to sand
        setMaterial(sandMaterial);

        // Add a kinematic rigidbody for collision
        addComponent(new RigidbodyComponent(true));
    }
}
