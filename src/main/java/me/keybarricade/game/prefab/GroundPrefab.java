package me.keybarricade.game.prefab;

import com.timvisee.voxeltex.component.rigidbody.RigidbodyComponent;
import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.prefab.primitive.QuadPrefab;
import me.keybarricade.game.asset.GameResourceBundle;
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
        System.out.println("Generating " + this + " surface material...");
        Material groundMaterial = new Material(GameResourceBundle.getInstance().TEXTURE_GROUND);
        groundMaterial.getTiling().set(size.x / 8.0f);

        // Set the quad material to the ground
        setMaterial(groundMaterial);

        // Add a kinematic rigidbody for collision
        addComponent(new RigidbodyComponent(true));
    }
}
