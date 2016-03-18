package me.keybarricade.voxeltex.prefab.camera;

import me.keybarricade.voxeltex.component.camera.FpsCameraComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;

public class FpsCameraPrefab extends GameObject {

    /**
     * The default name of the camera object.
     */
    private static final String DEFAULT_NAME = "FpsCameraPrefab";

    /**
     * Constructor.
     */
    public FpsCameraPrefab() {
        super(DEFAULT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public FpsCameraPrefab(String name) {
        super(name);
    }

    @Override
    public void start() {
        // Call the super
        super.start();

        // Add the FPS camera component
        addComponent(new FpsCameraComponent());
    }
}
