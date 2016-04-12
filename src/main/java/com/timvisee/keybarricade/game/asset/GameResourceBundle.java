/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.keybarricade.game.asset;

import com.timvisee.keybarricade.game.level.LevelManager;
import com.timvisee.voxeltex.material.Material;
import com.timvisee.voxeltex.mesh.Mesh;
import com.timvisee.voxeltex.model.loader.ObjModelLoader;
import com.timvisee.voxeltex.resource.bundle.ResourceBundleInterface;
import com.timvisee.voxeltex.texture.Image;
import com.timvisee.voxeltex.texture.Texture;

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
    public Texture TEXTURE_BOX11;
    public Material MATERIAL_BOX11;
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
    public Texture TEXTURE_BOX11_DARK;
    public Material MATERIAL_BOX11_DARK;
    public Texture TEXTURE_LAMP;
    public Material MATERIAL_LAMP;
    public Mesh MESH_SPHERE;
    public Image IMAGE_KEY;
    public Mesh MESH_KEY;
    public Mesh MESH_PADLOCK;
    public Texture TEXTURE_FINISH;
    public Material MATERIAL_FINISH;
    public Texture TEXTURE_GROUND;
    public Material MATERIAL_GROUND;
    public LevelManager LEVEL_MANAGER;

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
        this.TEXTURE_BOX0 = loadGameTexture("images/box/box0.png");
        this.MATERIAL_BOX0 = new Material(this.TEXTURE_BOX0);
        this.TEXTURE_BOX1 = loadGameTexture("images/box/box1.png");
        this.MATERIAL_BOX1 = new Material(this.TEXTURE_BOX1);
        this.TEXTURE_BOX2 = loadGameTexture("images/box/box2.png");
        this.MATERIAL_BOX2 = new Material(this.TEXTURE_BOX2);
        this.TEXTURE_BOX3 = loadGameTexture("images/box/box3.png");
        this.MATERIAL_BOX3 = new Material(this.TEXTURE_BOX3);
        this.TEXTURE_BOX4 = loadGameTexture("images/box/box4.png");
        this.MATERIAL_BOX4 = new Material(this.TEXTURE_BOX4);
        this.TEXTURE_BOX5 = loadGameTexture("images/box/box5.png");
        this.MATERIAL_BOX5 = new Material(this.TEXTURE_BOX5);
        this.TEXTURE_BOX6 = loadGameTexture("images/box/box6.png");
        this.MATERIAL_BOX6 = new Material(this.TEXTURE_BOX6);
        this.TEXTURE_BOX7 = loadGameTexture("images/box/box7.png");
        this.MATERIAL_BOX7 = new Material(this.TEXTURE_BOX7);
        this.TEXTURE_BOX8 = loadGameTexture("images/box/box8.png");
        this.MATERIAL_BOX8 = new Material(this.TEXTURE_BOX8);
        this.TEXTURE_BOX9 = loadGameTexture("images/box/box9.png");
        this.MATERIAL_BOX9 = new Material(this.TEXTURE_BOX9);
        this.TEXTURE_BOX10 = loadGameTexture("images/box/box10.png");
        this.MATERIAL_BOX10 = new Material(this.TEXTURE_BOX10);
        this.TEXTURE_BOX11 = loadGameTexture("images/box/box11.png");
        this.MATERIAL_BOX11 = new Material(this.TEXTURE_BOX11);
        this.TEXTURE_BOX0_DARK = loadGameTexture("images/box/box0_dark.png");
        this.MATERIAL_BOX0_DARK = new Material(this.TEXTURE_BOX0_DARK);
        this.TEXTURE_BOX1_DARK = loadGameTexture("images/box/box1_dark.png");
        this.MATERIAL_BOX1_DARK = new Material(this.TEXTURE_BOX1_DARK);
        this.TEXTURE_BOX2_DARK = loadGameTexture("images/box/box2_dark.png");
        this.MATERIAL_BOX2_DARK = new Material(this.TEXTURE_BOX2_DARK);
        this.TEXTURE_BOX3_DARK = loadGameTexture("images/box/box3_dark.png");
        this.MATERIAL_BOX3_DARK = new Material(this.TEXTURE_BOX3_DARK);
        this.TEXTURE_BOX4_DARK = loadGameTexture("images/box/box4_dark.png");
        this.MATERIAL_BOX4_DARK = new Material(this.TEXTURE_BOX4_DARK);
        this.TEXTURE_BOX5_DARK = loadGameTexture("images/box/box5_dark.png");
        this.MATERIAL_BOX5_DARK = new Material(this.TEXTURE_BOX5_DARK);
        this.TEXTURE_BOX6_DARK = loadGameTexture("images/box/box6_dark.png");
        this.MATERIAL_BOX6_DARK = new Material(this.TEXTURE_BOX6_DARK);
        this.TEXTURE_BOX7_DARK = loadGameTexture("images/box/box7_dark.png");
        this.MATERIAL_BOX7_DARK = new Material(this.TEXTURE_BOX7_DARK);
        this.TEXTURE_BOX8_DARK = loadGameTexture("images/box/box8_dark.png");
        this.MATERIAL_BOX8_DARK = new Material(this.TEXTURE_BOX8_DARK);
        this.TEXTURE_BOX9_DARK = loadGameTexture("images/box/box9_dark.png");
        this.MATERIAL_BOX9_DARK = new Material(this.TEXTURE_BOX9_DARK);
        this.TEXTURE_BOX10_DARK = loadGameTexture("images/box/box10_dark.png");
        this.MATERIAL_BOX10_DARK = new Material(this.TEXTURE_BOX10_DARK);
        this.TEXTURE_BOX11_DARK = loadGameTexture("images/box/box11_dark.png");
        this.MATERIAL_BOX11_DARK = new Material(this.TEXTURE_BOX11_DARK);

        // Load the lamp texture and material
        this.TEXTURE_LAMP = loadGameTexture("images/lamp.png");
        this.MATERIAL_LAMP = new Material(this.TEXTURE_LAMP);

        // Load the sphere mesh
        this.MESH_SPHERE = new Mesh(ObjModelLoader.loadModelFromEngineAssets("models/sphere.obj"));

        // Load the key resources
        this.IMAGE_KEY = loadGameImage("images/key.png");
        this.MESH_KEY = new Mesh(ObjModelLoader.loadModelFromInputStream(
                GameAssetLoader.getInstance().loadResourceStream("models/key.obj")
        ));

        // Load the padlock resources
        this.MESH_PADLOCK = new Mesh(ObjModelLoader.loadModelFromInputStream(
                GameAssetLoader.getInstance().loadResourceStream("models/padlock.obj")
        ));

        // Load the finish resources
        this.TEXTURE_FINISH = loadGameTexture("images/finish.png");
        this.MATERIAL_FINISH = new Material(this.TEXTURE_FINISH);

        // Load the ground resources
        this.TEXTURE_GROUND = loadGameTexture("images/ground.png");
        this.MATERIAL_GROUND = new Material(this.TEXTURE_GROUND);

        // Load the level manager and the level data
        this.LEVEL_MANAGER = new LevelManager();
        this.LEVEL_MANAGER.load();
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
        this.TEXTURE_BOX11.dispose();
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
        this.TEXTURE_BOX11_DARK.dispose();
        this.TEXTURE_LAMP.dispose();
        this.IMAGE_KEY.dispose();
        this.TEXTURE_FINISH.dispose();
        this.TEXTURE_GROUND.dispose();
        this.LEVEL_MANAGER.clear();
    }

    /**
     * Load a texture from game resources.
     *
     * @param path Image resource path.
     *
     * @return Texture.
     */
    private Texture loadGameTexture(String path) {
        return Texture.fromImage(loadGameImage(path));
    }

    /**
     * Load an image from game resources.
     *
     * @param path Image resource path.
     *
     * @return Image.
     */
    private Image loadGameImage(String path) {
        return Image.loadFromByteBuffer(GameAssetLoader.getInstance().loadResourceByteBuffer(path));
    }
}
