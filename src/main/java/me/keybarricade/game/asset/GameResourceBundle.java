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
    public Texture TEXTURE_BOX0_DARK;
    public Material MATERIAL_BOX0_DARK;
    public Texture TEXTURE_BOX1_DARK;
    public Material MATERIAL_BOX1_DARK;
    public Texture TEXTURE_BOX2_DARK;
    public Material MATERIAL_BOX2_DARK;
    public Texture TEXTURE_BOX3_DARK;
    public Material MATERIAL_BOX3_DARK;
    public Texture TEXTURE_BOX4_DARK;
    public Material MATERIAL_BOX4_DARK;
    public Texture TEXTURE_BOX5_DARK;
    public Material MATERIAL_BOX5_DARK;
    public Texture TEXTURE_BOX6_DARK;
    public Material MATERIAL_BOX6_DARK;
    public Texture TEXTURE_BOX7_DARK;
    public Material MATERIAL_BOX7_DARK;
    public Texture TEXTURE_BOX8_DARK;
    public Material MATERIAL_BOX8_DARK;
    public Texture TEXTURE_BOX9_DARK;
    public Material MATERIAL_BOX9_DARK;
    public Texture TEXTURE_BOX10_DARK;
    public Material MATERIAL_BOX10_DARK;
    public Mesh MESH_SPHERE;
    public Image IMAGE_KEY;
    public Mesh MESH_KEY;
    public Mesh MESH_PADLOCK;
    public Texture TEXTURE_FINISH;
    public Material MATERIAL_FINISH;
    public Texture TEXTURE_GROUND;
    public Material MATERIAL_GROUND;

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
        this.TEXTURE_BOX0 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box0.png"));
        this.MATERIAL_BOX0 = new Material(this.TEXTURE_BOX0);
        this.TEXTURE_BOX1 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box1.png"));
        this.MATERIAL_BOX1 = new Material(this.TEXTURE_BOX1);
        this.TEXTURE_BOX2 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box2.png"));
        this.MATERIAL_BOX2 = new Material(this.TEXTURE_BOX2);
        this.TEXTURE_BOX3 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box3.png"));
        this.MATERIAL_BOX3 = new Material(this.TEXTURE_BOX3);
        this.TEXTURE_BOX4 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box4.png"));
        this.MATERIAL_BOX4 = new Material(this.TEXTURE_BOX4);
        this.TEXTURE_BOX5 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box5.png"));
        this.MATERIAL_BOX5 = new Material(this.TEXTURE_BOX5);
        this.TEXTURE_BOX6 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box6.png"));
        this.MATERIAL_BOX6 = new Material(this.TEXTURE_BOX6);
        this.TEXTURE_BOX7 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box7.png"));
        this.MATERIAL_BOX7 = new Material(this.TEXTURE_BOX7);
        this.TEXTURE_BOX8 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box8.png"));
        this.MATERIAL_BOX8 = new Material(this.TEXTURE_BOX8);
        this.TEXTURE_BOX9 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box9.png"));
        this.MATERIAL_BOX9 = new Material(this.TEXTURE_BOX9);
        this.TEXTURE_BOX10 = Texture.fromImage(Image.loadFromEngineAssets("images/box/box10.png"));
        this.MATERIAL_BOX10 = new Material(this.TEXTURE_BOX10);
        this.TEXTURE_BOX0_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box0_dark.png"));
        this.MATERIAL_BOX0_DARK = new Material(this.TEXTURE_BOX0_DARK);
        this.TEXTURE_BOX1_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box1_dark.png"));
        this.MATERIAL_BOX1_DARK = new Material(this.TEXTURE_BOX1_DARK);
        this.TEXTURE_BOX2_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box2_dark.png"));
        this.MATERIAL_BOX2_DARK = new Material(this.TEXTURE_BOX2_DARK);
        this.TEXTURE_BOX3_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box3_dark.png"));
        this.MATERIAL_BOX3_DARK = new Material(this.TEXTURE_BOX3_DARK);
        this.TEXTURE_BOX4_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box4_dark.png"));
        this.MATERIAL_BOX4_DARK = new Material(this.TEXTURE_BOX4_DARK);
        this.TEXTURE_BOX5_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box5_dark.png"));
        this.MATERIAL_BOX5_DARK = new Material(this.TEXTURE_BOX5_DARK);
        this.TEXTURE_BOX6_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box6_dark.png"));
        this.MATERIAL_BOX6_DARK = new Material(this.TEXTURE_BOX6_DARK);
        this.TEXTURE_BOX7_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box7_dark.png"));
        this.MATERIAL_BOX7_DARK = new Material(this.TEXTURE_BOX7_DARK);
        this.TEXTURE_BOX8_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box8_dark.png"));
        this.MATERIAL_BOX8_DARK = new Material(this.TEXTURE_BOX8_DARK);
        this.TEXTURE_BOX9_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box9_dark.png"));
        this.MATERIAL_BOX9_DARK = new Material(this.TEXTURE_BOX9_DARK);
        this.TEXTURE_BOX10_DARK = Texture.fromImage(Image.loadFromEngineAssets("images/box/box10_dark.png"));
        this.MATERIAL_BOX10_DARK = new Material(this.TEXTURE_BOX10_DARK);

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

        // Load the ground resources
        this.TEXTURE_GROUND = Texture.fromImage(Image.loadFromEngineAssets("images/ground.png"));
        this.MATERIAL_GROUND = new Material(this.TEXTURE_GROUND);
    }

    @Override
    public void dispose() {
        this.TEXTURE_BOX0.dispose();
        this.TEXTURE_BOX1.dispose();
        this.TEXTURE_BOX2.dispose();
        this.TEXTURE_BOX3.dispose();
        this.TEXTURE_BOX4.dispose();
        this.TEXTURE_BOX5.dispose();
        this.TEXTURE_BOX6.dispose();
        this.TEXTURE_BOX7.dispose();
        this.TEXTURE_BOX8.dispose();
        this.TEXTURE_BOX9.dispose();
        this.TEXTURE_BOX10.dispose();
        this.TEXTURE_BOX0_DARK.dispose();
        this.TEXTURE_BOX1_DARK.dispose();
        this.TEXTURE_BOX2_DARK.dispose();
        this.TEXTURE_BOX3_DARK.dispose();
        this.TEXTURE_BOX4_DARK.dispose();
        this.TEXTURE_BOX5_DARK.dispose();
        this.TEXTURE_BOX6_DARK.dispose();
        this.TEXTURE_BOX7_DARK.dispose();
        this.TEXTURE_BOX8_DARK.dispose();
        this.TEXTURE_BOX9_DARK.dispose();
        this.TEXTURE_BOX10_DARK.dispose();
        this.IMAGE_KEY.dispose();
        this.TEXTURE_FINISH.dispose();
        this.TEXTURE_GROUND.dispose();
    }
}
