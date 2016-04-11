package me.keybarricade.game.prefab;

import me.keybarricade.game.asset.GameResourceBundle;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.prefab.primitive.QuadPrefab;
import org.joml.Vector2f;

public class GroundPrefab extends QuadPrefab {

    /**
     * Constructor.
     */
    public GroundPrefab() {
        this(50.0f);
    }

    /**
     * Constructor.
     *
     * @param size Ground size.
     */
    public GroundPrefab(float size) {
        this(new Vector2f(size));
    }

    /**
     * Constructor.
     *
     * @param size Ground size.
     */
    public GroundPrefab(Vector2f size) {
        // Construct the parent with the proper size
        super("GroundPrefab", size);

        // Create a ground surface material
        System.out.println("Generating surface material...");
        Material groundMaterial = new Material(GameResourceBundle.getInstance().TEXTURE_GROUND);
        groundMaterial.getTiling().set(size.x / 8.0f);

        // Set the quad material to the ground
        setMaterial(groundMaterial);

        // Add a kinematic rigidbody for collision
        addComponent(new RigidbodyComponent(true));
    }
}
