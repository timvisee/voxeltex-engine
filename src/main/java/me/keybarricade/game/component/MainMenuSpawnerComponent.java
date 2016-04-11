package me.keybarricade.game.component;

import com.timvisee.voxeltex.component.BaseComponent;
import com.timvisee.voxeltex.global.Time;
import me.keybarricade.game.LockType;
import me.keybarricade.game.component.animator.ObjectDecayAnimatorComponent;
import me.keybarricade.game.component.animator.ObjectSpawnAnimatorComponent;
import me.keybarricade.game.prefab.BoxPrefab;
import me.keybarricade.game.prefab.LampPrefab;
import org.joml.Vector3f;

public class MainMenuSpawnerComponent extends BaseComponent {

    /**
     * Constructor.
     */
    public MainMenuSpawnerComponent() { }

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() {
        // Spawn cubes randomly
        if(Math.random() < Time.deltaTimeFloat * 10.0f) {
            // Determine the position
            float x = (int) (-25f + (float) Math.random() * 50);
            float z = (int) (-25f + (float) Math.random() * 50);

            // Determine what to spawn
            if(Math.random() < 0.95f)
                // Spawn a box
                getScene().addGameObject(new BoxPrefab(new Vector3f(x + 0.5f, 0.5f, z + 0.5f), true, 0.0f, 10.0f));

            else {
                // Create a lamp
                LampPrefab lamp = new LampPrefab(
                                "LampPrefab",
                                LockType.fromDataValue((int) ((Math.random() * LockType.values().length) - 1)).getColorCopy()
                );

                // Set the position
                lamp.getTransform().setPosition(x + 0.5f, 0.01f, z + 0.5f);

                // Add a spawn and decay animator
                lamp.addComponent(new ObjectSpawnAnimatorComponent(0f));
                lamp.addComponent(new ObjectDecayAnimatorComponent(10f));

                // Add the lamp
                getScene().addGameObject(lamp);
            }
        }
    }
}
