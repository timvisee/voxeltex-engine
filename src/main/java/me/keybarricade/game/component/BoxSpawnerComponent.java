package me.keybarricade.game.component;

import me.keybarricade.game.prefab.BoxPrefab;
import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.global.Time;
import org.joml.Vector3f;

public class BoxSpawnerComponent extends BaseComponent {

    /**
     * Constructor.
     */
    public BoxSpawnerComponent() { }

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

            // Spawn a box
            getScene().addGameObject(new BoxPrefab(new Vector3f(x, 0.5f, z), true, 0.0f, 10.0f));
        }
    }
}
