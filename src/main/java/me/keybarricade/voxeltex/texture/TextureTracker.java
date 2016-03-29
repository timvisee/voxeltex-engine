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

package me.keybarricade.voxeltex.texture;

import java.util.ArrayList;
import java.util.List;

public class TextureTracker {

    /**
     * List of textures managed by the engine.
     */
    private static List<Texture> textures = new ArrayList<>();

    /**
     * Add a new texture to the tracker.
     *
     * @param texture Texture to add.
     */
    public static void trackTexture(Texture texture) {
        textures.add(texture);
    }

    /**
     * Get the list of textures.
     *
     * @return List of textures.
     */
    public static List<Texture> getTextures() {
        return textures;
    }

    /**
     * Get the number of managed textures.
     *
     * @return Texture count.
     */
    public static int getTextureCount() {
        return textures.size();
    }

    /**
     * Remove the given texture from the tracker.
     *
     * @param texture Texture to remove.
     *
     * @return True if a texture was removed from the tracker, false othewrise.
     */
    public static boolean untrackTexture(Texture texture) {
        return textures.remove(texture);
    }

    /**
     * Remove the texture from the tracker at the position of the given index.
     *
     * @param i Texture index.
     *
     * @return Removed texture.
     */
    public static Texture untrackTexture(int i) {
        return textures.remove(i);
    }

    /**
     * Dispose all tracked textures and remove them from the tracker.
     */
    public static void disposeAll() {
        // Loop through all textures and dispose them
        for(int i = getTextureCount() - 1; i >= 0; i--)
            textures.get(i).dispose();

        // Remove all textures (should have been removed already)
        textures.clear();
    }
}
