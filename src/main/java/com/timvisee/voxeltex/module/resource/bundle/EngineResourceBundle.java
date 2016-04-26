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

package com.timvisee.voxeltex.module.resource.bundle;

import com.timvisee.voxeltex.module.material.Material;
import com.timvisee.voxeltex.module.texture.Image;
import com.timvisee.voxeltex.module.texture.Texture;

public class EngineResourceBundle implements ResourceBundleInterface {

    /**
     * Instance.
     */
    private static EngineResourceBundle instance = new EngineResourceBundle();

    public Texture TEXTURE_BOX;
    public Material MATERIAL_BOX;
    public Texture TEXTURE_GROUND;
    public Material MATERIAL_GROUND;

    /**
     * Get the engine resource bundle instance.
     *
     * @return Engine resource bundle instance.
     */
    public static EngineResourceBundle getInstance() {
        return EngineResourceBundle.instance;
    }

    @Override
    public void load() {
        // Show status message
        System.out.println("Loading engine resources...");

        // Load box texture and material
        this.TEXTURE_BOX = loadEngineTexture("images/box/box.png");
        this.MATERIAL_BOX = new Material(this.TEXTURE_BOX);

        // Load the ground resources
        this.TEXTURE_GROUND = loadEngineTexture("images/ground.png");
        this.MATERIAL_GROUND = new Material(this.TEXTURE_GROUND);
    }

    @Override
    public void dispose() {
        // Show a status message
        System.out.println("Disposing engine resources...");

        // Dispose all resources
        this.TEXTURE_BOX.dispose();
        this.TEXTURE_GROUND.dispose();
    }

    /**
     * Load a texture from engine resources.
     *
     * @param path Image resource path.
     *
     * @return Texture.
     */
    private Texture loadEngineTexture(String path) {
        return Texture.fromImage(loadEngineImage(path));
    }

    /**
     * Load an image from engine resources.
     *
     * @param path Image resource path.
     *
     * @return Image.
     */
    private Image loadEngineImage(String path) {
        return Image.loadFromEngineAssets(path);
    }
}
