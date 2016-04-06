package me.keybarricade;

import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import org.joml.Vector3f;

public class BoxPrefab extends CubePrefab {

    /**
     * Constructor.
     */
    public BoxPrefab(Vector3f position, Material boxMaterial) {
        // Construct the parent
        super("BoxPrefab", Vector3fFactory.one());

        // Set the box material
        setMaterial(boxMaterial);

        // Set the position
        getTransform().setPosition(position);

        // Add the animator components
        addComponent(new BoxSpawnAnimatorComponent());
        addComponent(new BoxDecayAnimatorComponent(10.0f));
    }
}
