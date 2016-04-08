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

    public Texture TEXTURE_BOX0;
    public Material MATERIAL_BOX0;
    public Texture TEXTURE_BOX1;
    public Material MATERIAL_BOX1;
    public Texture TEXTURE_BOX2;
    public Material MATERIAL_BOX2;
    public Texture TEXTURE_BOX3;
    public Material MATERIAL_BOX3;
    public Texture TEXTURE_BOX4;
    public Material MATERIAL_BOX4;
    public Texture TEXTURE_BOX5;
    public Material MATERIAL_BOX5;
    public Texture TEXTURE_BOX6;
    public Material MATERIAL_BOX6;
    public Texture TEXTURE_BOX7;
    public Material MATERIAL_BOX7;
    public Texture TEXTURE_BOX8;
    public Material MATERIAL_BOX8;
    public Texture TEXTURE_BOX9;
    public Material MATERIAL_BOX9;
    public Texture TEXTURE_BOX10;
    public Material MATERIAL_BOX10;
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
        this.TEXTURE_BOX0 = Texture.fromImage(Image.loadFromEngineAssets("images/box0.png"));
        this.MATERIAL_BOX0 = new Material(this.TEXTURE_BOX0);
        this.TEXTURE_BOX1 = Texture.fromImage(Image.loadFromEngineAssets("images/box1.png"));
        this.MATERIAL_BOX1 = new Material(this.TEXTURE_BOX1);
        this.TEXTURE_BOX2 = Texture.fromImage(Image.loadFromEngineAssets("images/box2.png"));
        this.MATERIAL_BOX2 = new Material(this.TEXTURE_BOX2);
        this.TEXTURE_BOX3 = Texture.fromImage(Image.loadFromEngineAssets("images/box3.png"));
        this.MATERIAL_BOX3 = new Material(this.TEXTURE_BOX3);
        this.TEXTURE_BOX4 = Texture.fromImage(Image.loadFromEngineAssets("images/box4.png"));
        this.MATERIAL_BOX4 = new Material(this.TEXTURE_BOX4);
        this.TEXTURE_BOX5 = Texture.fromImage(Image.loadFromEngineAssets("images/box5.png"));
        this.MATERIAL_BOX5 = new Material(this.TEXTURE_BOX5);
        this.TEXTURE_BOX6 = Texture.fromImage(Image.loadFromEngineAssets("images/box6.png"));
        this.MATERIAL_BOX6 = new Material(this.TEXTURE_BOX6);
        this.TEXTURE_BOX7 = Texture.fromImage(Image.loadFromEngineAssets("images/box7.png"));
        this.MATERIAL_BOX7 = new Material(this.TEXTURE_BOX7);
        this.TEXTURE_BOX8 = Texture.fromImage(Image.loadFromEngineAssets("images/box8.png"));
        this.MATERIAL_BOX8 = new Material(this.TEXTURE_BOX8);
        this.TEXTURE_BOX9 = Texture.fromImage(Image.loadFromEngineAssets("images/box9.png"));
        this.MATERIAL_BOX9 = new Material(this.TEXTURE_BOX9);
        this.TEXTURE_BOX10 = Texture.fromImage(Image.loadFromEngineAssets("images/box10.png"));
        this.MATERIAL_BOX10 = new Material(this.TEXTURE_BOX10);

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
        // TODO: Dispose all textures!
    }
}
