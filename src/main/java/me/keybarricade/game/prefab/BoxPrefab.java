package me.keybarricade.game.prefab;

import me.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import me.keybarricade.game.component.animator.ObjectSpawnAnimatorComponent;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import org.joml.Vector3f;

public class BoxPrefab extends CubePrefab {

    /**
     * Constructor.
     */
    public BoxPrefab(Vector3f position, boolean dummy, float spawnDelay, float decayDelay, Material boxMaterial) {
        // Construct the parent
        super("BoxPrefab", Vector3fFactory.one());

        // Set the box material
        setMaterial(boxMaterial);

        // Set the position
        getTransform().setPosition(position);

        // Add the animator components
        addComponent(new ObjectSpawnAnimatorComponent(spawnDelay, !dummy ? new RigidbodyComponent(true) : null));

        // Add a decay animation
        if(decayDelay >= 0.0f)
            addComponent(new ObjectDecayAnimatorComponent(decayDelay));
    }
}
