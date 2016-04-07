package me.keybarricade.game.prefab;

import me.keybarricade.game.asset.GameResourceBundle;
import me.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import me.keybarricade.game.component.animator.ObjectSpawnAnimatorComponent;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.math.vector.Vector3fFactory;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import org.joml.Vector3f;

public class BoxPrefab extends CubePrefab {

    /**
     * Constructor.
     */
    public BoxPrefab(Vector3f position, boolean dummy, float spawnDelay, float decayDelay) {
        // Construct the parent
        super("BoxPrefab", Vector3fFactory.one());

        // Set the box material
        setMaterial(GameResourceBundle.getInstance().MATERIAL_BOX);

        // Set the position
        getTransform().setPosition(position);

        // Add the animator components
        addComponent(new ObjectSpawnAnimatorComponent(spawnDelay, !dummy ? new RigidbodyComponent(true) : null));

        // Add a decay animation
        if(decayDelay >= 0.0f)
            addComponent(new ObjectDecayAnimatorComponent(decayDelay));
    }
}
