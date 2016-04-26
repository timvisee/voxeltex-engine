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

package com.timvisee.voxeltex.module.texture;

import java.util.ArrayList;
import java.util.List;

public class ImageTracker {

    /**
     * List of images managed by the engine.
     */
    private static List<Image> images = new ArrayList<>();

    /**
     * Add a new image to the tracker.
     *
     * @param image Image to add.
     */
    public static void trackImage(Image image) {
        images.add(image);
    }

    /**
     * Get the list of images.
     *
     * @return List of images.
     */
    public static List<Image> getImages() {
        return images;
    }

    /**
     * Get the number of managed images.
     *
     * @return Image count.
     */
    public static int getImageCount() {
        return images.size();
    }

    /**
     * Remove the given image from the tracker.
     *
     * @param image Image to remove.
     *
     * @return True if a image was removed from the tracker, false othewrise.
     */
    public static boolean untrackImage(Image image) {
        return images.remove(image);
    }

    /**
     * Remove the image from the tracker at the position of the given index.
     *
     * @param i Image index.
     *
     * @return Removed image.
     */
    public static Image untrackImage(int i) {
        return images.remove(i);
    }

    /**
     * Dispose all tracked images and remove them from the tracker.
     */
    public static void disposeAll() {
        // Loop through all images and dispose them
        // Loop through all textures and dispose them
        for(int i = getImageCount() - 1; i >= 0; i--)
            images.get(i).dispose();

        // Remove all images (should have been removed already)
        images.clear();
    }
}
