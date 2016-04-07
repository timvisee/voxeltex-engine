package me.keybarricade.game.asset;

import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.resource.bundle.ResourceBundleInterface;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;

public class GameResourceBundle implements ResourceBundleInterface {

    /**
     * Instance.
     */
    private static GameResourceBundle instance = new GameResourceBundle();

    public Texture TEXTURE_BOX;
    public Material MATERIAL_BOX;

    /**
     * Get the game resource bundle instance.
     *
     * @return Game resource bundle instance.
     */
    public static GameResourceBundle getInstance() {
        return instance;
    }

    @Override
    public void load() {
        // Show status message
        System.out.println("Loading game resources...");

        // Load box texture and material
        this.TEXTURE_BOX = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
        this.MATERIAL_BOX = new Material(this.TEXTURE_BOX);
    }

    @Override
    public void dispose() {
        // Dispose the box texture
        this.TEXTURE_BOX.dispose();
    }
}
