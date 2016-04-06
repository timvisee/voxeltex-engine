package me.keybarricade;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.global.Time;
import me.keybarricade.voxeltex.material.Material;
import org.joml.Vector3f;

public class BoxSpawnerComponent extends BaseComponent {

    /**
     * Box material.
     */
    private Material boxMaterial;

    /**
     * Constructor.
     *
     * @param boxMaterial Box material.
     */
    public BoxSpawnerComponent(Material boxMaterial) {
        this.boxMaterial = boxMaterial;
    }

    @Override
    public void create() { }

    @Override
    public void start() { }

    @Override
    public void update() {
        // Spawn cubes randomly
        if(Math.random() < (Time.timeFloat / 150.0f)) {
            // Determine the position
            float x = (int) (-25f + (float) Math.random() * 50);
            float z = (int) (-25f + (float) Math.random() * 50);

            // Spawn a box
            getScene().addGameObject(new BoxPrefab(new Vector3f(x, 0.5f, z), boxMaterial));
        }
    }

    @Override
    public void destroy() { }
}
