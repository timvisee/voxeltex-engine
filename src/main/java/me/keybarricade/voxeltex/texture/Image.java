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

import me.keybarricade.voxeltex.resource.engine.EngineAssetLoader;
import me.keybarricade.voxeltex.util.BufferUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Image {

    /**
     * Number of components for RGB images.
     */
    public static final int COMPONENTS_RGB = 3;

    /**
     * Number of components for RGBA images.
     */
    public static final int COMPONENTS_RGBA = 4;

    /**
     * Byte buffer containing the image data.
     */
    private final ByteBuffer image;

    /**
     * Image width.
     */
    private final int width;

    /**
     * Image height.
     */
    private final int height;

    /**
     * Number of components on this image.
     */
    private final int components;

    /**
     * Constructor.
     *
     * @param image Image as byte buffer.
     * @param width Width of the image.
     * @param height Height of the image.
     * @param components Components on this image.
     */
    public Image(ByteBuffer image, int width, int height, int components) {
        // Set the image fields
        this.image = image;
        this.width = width;
        this.height = height;
        this.components = components;

        // Add the image to the image manager
        ImageTracker.trackImage(this);
    }

    /**
     * Load an image from the given path.
     *
     * @param path Image path.
     *
     * @return Image.
     */
    public static Image loadFromEngineAssets(String path) {
        // Create a byte buffer for the image to load
        ByteBuffer imageBuffer;

        // Load the image file into the image buffer
        imageBuffer = EngineAssetLoader.getInstance().loadResourceByteBuffer(path);

        // Create some integer buffers for STB interaction
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);

        // Load the image into memory using STB
        ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, c, 0);

        // Create the image instance and return it
        return new Image(image, w.get(0), h.get(0), c.get(0));
    }

    /**
     * Load an image from the given path.
     *
     * @param path Image path.
     *
     * @return Image.
     */
    public static Image loadFromPath(String path) {
        // Create a byte buffer for the image to load
        ByteBuffer imageBuffer;

        // Load the image file into the image buffer
        try {
            imageBuffer = BufferUtil.fileToByteBuffer(path);

        } catch(IOException e) {
            // TODO: Use a default image!
            System.out.println("Could not find the image at the path '" + path + "'");
            throw new RuntimeException(e);
        }

        // Create some integer buffers for STB interaction
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);

        // Load the image into memory using STB
        ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, c, 0);

        // Create the image instance and return it
        return new Image(image, w.get(0), h.get(0), c.get(0));
    }

    /**
     * Get the image as byte buffer.
     *
     * @return Image.
     */
    public ByteBuffer getImage() {
        return image;
    }

    /**
     * Get the width of the image.
     *
     * @return Image width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the image.
     *
     * @return Image height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the components of an image.
     *
     * @return Image components.
     */
    public int getComponents() {
        return components;
    }

    /**
     * Dispose the image, and free the memory.
     */
    public void dispose() {
        // Free the image memory through STB
        STBImage.stbi_image_free(image);

        // Remove the image from the image manager
        ImageTracker.untrackImage(this);
    }
}
