package me.keybarricade.game.asset;

import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
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
    public Mesh MESH_SPHERE;
    public Image IMAGE_KEY;

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

        // Load the sphere mesh
        this.MESH_SPHERE = new Mesh(ObjModelLoader.loadModelFromEngineAssets("models/sphere.obj"));

        // Load the key image
        this.IMAGE_KEY = Image.loadFromEngineAssets("images/key.png");
    }

    @Override
    public void dispose() {
        // Dispose the box texture
        this.TEXTURE_BOX.dispose();
    }
}
