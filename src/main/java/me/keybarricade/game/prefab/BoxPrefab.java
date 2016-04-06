package me.keybarricade.game.prefab;

import me.keybarricade.game.component.animator.BoxDecayAnimatorComponent;
import me.keybarricade.game.component.animator.BoxSpawnAnimatorComponent;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import org.joml.Vector3f;

public class BoxPrefab extends CubePrefab {

    /**
     * Constructor.
     */
    public BoxPrefab(Vector3f position, boolean dummy, float decayDelay, Material boxMaterial) {
        // Construct the parent
        super("BoxPrefab", Vector3fFactory.one());

        // Set the box material
        setMaterial(boxMaterial);

        // Set the position
        getTransform().setPosition(position);

        // Add the animator components
        addComponent(new BoxSpawnAnimatorComponent());

        // Add a decay animation
        if(decayDelay >= 0.0f)
            addComponent(new BoxDecayAnimatorComponent(decayDelay));

        // Add a rigidbody if this isn't a dummy box
        if(!dummy)
            addComponent(new RigidbodyComponent(true));
    }
}
