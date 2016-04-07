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
    public Mesh MESH_KEY;
    public Mesh MESH_PADLOCK;
    public Texture TEXTURE_FINISH;
    public Material MATERIAL_FINISH;
    public Texture TEXTURE_SAND;
    public Material MATERIAL_SAND;

    /**
     * Get the game resource bundle instance.
     *
     * @return Game resource bundle instance.
     */
    public static GameResourceBundle getInstance() {
        return GameResourceBundle.instance;
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

        // Load the key resources
        this.IMAGE_KEY = Image.loadFromEngineAssets("images/key.png");
        this.MESH_KEY = new Mesh(ObjModelLoader.loadModelFromInputStream(
                GameAssetLoader.getInstance().loadResourceStream("models/key.obj")
        ));

        // Load the padlock resources
        this.MESH_PADLOCK = new Mesh(ObjModelLoader.loadModelFromInputStream(
                GameAssetLoader.getInstance().loadResourceStream("models/padlock.obj")
        ));

        // Load the finish resources
        this.TEXTURE_FINISH = Texture.fromImage(Image.loadFromEngineAssets("images/finish.png"));
        this.MATERIAL_FINISH = new Material(this.TEXTURE_FINISH);

        // Load the sand resources
        this.TEXTURE_SAND = Texture.fromImage(Image.loadFromEngineAssets("images/ground.png"));
        this.MATERIAL_SAND = new Material(this.TEXTURE_SAND);
    }

    @Override
    public void dispose() {
        // Dispose the box texture
        this.TEXTURE_BOX.dispose();
    }
}
