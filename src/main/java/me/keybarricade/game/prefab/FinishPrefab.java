package me.keybarricade.game.prefab;

import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.prefab.primitive.QuadPrefab;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;

public class FinishPrefab extends QuadPrefab {

    /**
     * Game object name.
     */
    private static final String GAME_OBJECT_NAME = "FinishPrefab";

    /**
     * Constructor.
     */
    public FinishPrefab() {
        this(GAME_OBJECT_NAME);
    }

    /**
     * Constructor.
     *
     * @param name Game object name.
     */
    public FinishPrefab(String name) {
        // Construct the parent with the proper size
        super(name);

        // Load the finish material
        Texture finishTexture = Texture.fromImage(Image.loadFromEngineAssets("images/finish.png"));
        Material finishMaterial = new Material(finishTexture);

        setMaterial(finishMaterial);
    }
}
