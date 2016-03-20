package me.keybarricade.gameobject;

import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.prefab.primitive.QuadPrefab;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import org.joml.Vector2f;

public class SandSurfacePrefab extends QuadPrefab {

    /**
     * Constructor.
     */
    public SandSurfacePrefab() {
        // Construct the parent with the proper size
        super("SandSurface", new Vector2f(50.0f, 50.0f));

        // Load the sand texture
        Texture sandTexture = Texture.fromImage(Image.loadFromEngineAssets("images/sand.png"));

        // Create a sand surface material
        Material sandMaterial = new Material(sandTexture);
        sandMaterial.getTiling().set(5.0f);

        // Set the quad material to sand
        setMaterial(sandMaterial);
    }
}
