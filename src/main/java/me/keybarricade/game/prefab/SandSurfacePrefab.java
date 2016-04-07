package me.keybarricade.game.prefab;

import me.keybarricade.game.asset.GameResourceBundle;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.prefab.primitive.QuadPrefab;
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

        // Create a sand surface material
        System.out.println("Generating surface material...");
        Material sandMaterial = new Material(GameResourceBundle.getInstance().TEXTURE_SAND);
        sandMaterial.getTiling().set(size.x / 2.0f);

        // Set the quad material to sand
        setMaterial(sandMaterial);

        // Add a kinematic rigidbody for collision
        addComponent(new RigidbodyComponent(true));
    }
}
